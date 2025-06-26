package com.technologyos.auth.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technologyos.auth.dtos.status.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@ToString
@Table(name = "users")
public class User implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false)
   private Long userId;

   @Column(unique = true, nullable = false)
   private String username;

   @Column(unique = true, nullable = false)
   private String email;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private String password;

   @ManyToOne
   @JoinColumn(name = "role_id")
   private Role role;

   @Column(name = "status_id", nullable = false)
   private Long statusId;

   @ManyToOne
   @JoinColumn(name = "status_id", insertable = false, updatable = false)
   private Status status;

   @CreationTimestamp
   @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime createdAt;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      if(role == null) return null;

      if(role.getPermissions() == null) return null;

      List<SimpleGrantedAuthority> authorities = role.getPermissions().stream()
         .map(permission -> permission.getOperation().getName())
         .map(SimpleGrantedAuthority::new)
         .collect(Collectors.toList());

      authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.getName()));
      return authorities;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return statusId.equals(StatusEnum.ENABLED.getCode());
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User that = (User) o;
      return Objects.equals(userId, that.userId);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(userId);
   }
}
