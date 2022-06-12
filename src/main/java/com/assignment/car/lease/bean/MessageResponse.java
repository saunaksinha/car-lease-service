package com.assignment.car.lease.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class MessageResponse {
  private String message;
}
