package com.assignment.car.lease.bean;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class LoginRequest {
  @NotBlank private String username;

  @NotBlank private String password;
}
