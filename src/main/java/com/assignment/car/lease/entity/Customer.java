package com.assignment.car.lease.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "CUSTOMER", schema = "LEASE")
@ToString
@Data
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CUSTOMER_ID")
  private Long id;

  @Column(name = "NAME")
  @NotEmpty
  private String name;

  @Column(name = "STREET")
  private String street;

  @Column(name = "HOUSE_NUMBER")
  private String houseNumber;

  @Column(name = "ZIP_CODE")
  private String zipCode;

  @Column(name = "PLACE")
  private String place;

  @Column(name = "EMAIL_ADDRESS")
  private String emailAddress;

  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;

  @CreationTimestamp
  @Column(name = "CREATED_AT", nullable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "UPDATED_AT")
  private Date updatedAt;
}
