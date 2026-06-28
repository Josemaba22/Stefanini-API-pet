package com.stefanini.pruebatecnica.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
  private String transactionId;
  private Instant dateCreated;
  private String status;
  private String name;
}
