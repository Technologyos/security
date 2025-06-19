package com.technologyos.auth.dtos.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
   ENABLED("ENABLED", 1L),
   DISABLED("DISABLED", 2L),
   PENDING("PENDING", 3L);

   private final String name;
   private final Long code;
}
