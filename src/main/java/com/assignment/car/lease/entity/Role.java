package com.assignment.car.lease.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles", schema = "LEASE")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

}
