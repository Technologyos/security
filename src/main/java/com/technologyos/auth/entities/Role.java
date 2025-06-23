package com.technologyos.auth.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
@Table(name = "roles")
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false)
   private Long roleId;

   @Column(nullable = false)
   private String name;

   @Column(name = "status_id", nullable = false)
   private Long statusId;

   @ManyToOne
   @JoinColumn(name = "status_id", insertable = false, updatable = false)
   private Status status;

   @Getter
   @JsonIgnore
   @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
   private List<GrantedPermission> permissions;

   @CreationTimestamp
   @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime createdAt;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Role that = (Role) o;
      return Objects.equals(roleId, that.roleId);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(roleId);
   }
}
