package com.assignment.car.lease.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Entity object for Car
 *
 * @author Saunak
 */
@Entity
@Table(name = "CAR", schema = "LEASE")
@Data
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CAR_ID")
  private Long id;

  @Column(name = "MAKE")
  private String make;

  @Column(name = "MODEL")
  private String model;

  @Column(name = "VERSION")
  private String version;

  @Column(name = "NUMBER_OF_DOORS")
  private Integer numberOfDoors;

  @Column(name = "CO2_EMISSION")
  private String co2Emission;

  @Column(name = "GROSS_PRICE")
  private BigDecimal grossPrice;

  @Column(name = "NET_PRICE")
  private BigDecimal netPrice;
}
