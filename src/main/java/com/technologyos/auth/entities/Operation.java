package com.technologyos.auth.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
@Table(name = "operations")
public class Operation {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false)
   private Long operationId;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private String path;

   @Column(nullable = false)
   private String httpMethod;

   @Column(nullable = false)
   private boolean permitAll;

   @Column(name = "module_id", nullable = false)
   private Long moduleId;

   @ManyToOne
   @JoinColumn(name = "module_id", insertable = false, updatable = false)
   private Module module;

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
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Operation that = (Operation) o;
      return Objects.equals(operationId, that.operationId);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(operationId);
   }
}
