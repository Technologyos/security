package com.technologyos.auth.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
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

   @ManyToOne
   @JoinColumn(name = "module_id")
   private Module module;

   @CreationTimestamp
   @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime createdAt;
}
