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
@SequenceGenerator(name = "status_seq", sequenceName = "status_sequence", allocationSize = 1)
@Table(name = "status")
@Setter
@Getter
@ToString
public class Status {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_seq")
   @Column(nullable = false)
   private Long statusId;

   @Column(nullable = false, length = 50)
   private String name;

   @CreationTimestamp
   @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime createdAt;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Status that = (Status) o;
      return Objects.equals(statusId, that.statusId);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(statusId);
   }
}
