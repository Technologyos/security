package com.technologyos.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
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
}
