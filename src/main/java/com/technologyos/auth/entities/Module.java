package com.technologyos.auth.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Module {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false)
   private Long moduleId;

   @Column(nullable = false)
   private String name;

   @Column(name = "base_path", nullable = false)
   private String basePath;

   @Column(name = "status_id", nullable = false)
   private Long statusId;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "status_id", insertable = false, updatable = false)
   private Status status;

   @CreationTimestamp
   @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime createdAt;
}
