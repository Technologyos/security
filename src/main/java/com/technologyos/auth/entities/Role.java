package com.technologyos.auth.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false)
   private Long roleId;

   @Column(nullable = false)
   private String name;

   @Column(name = "status_id", nullable = false)
   private Long statusId;

   @OneToOne(fetch = FetchType.EAGER)
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
}
