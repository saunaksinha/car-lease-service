package com.assignment.car.lease.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class UserInfoResponse {
  private Long id;
  private String username;
  private String email;
  private List<String> roles;
}
