package com.technologyos.auth.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtToken {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false)
   private Long tokenId;

   @Column(length = 2048)
   private String token;

   @Column(nullable = false)
   private Date expiration;

   @Column(nullable = false)
   private boolean isValid;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      JwtToken that = (JwtToken) o;
      return Objects.equals(tokenId, that.tokenId);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(tokenId);
   }
}
