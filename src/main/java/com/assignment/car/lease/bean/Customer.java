package com.assignment.car.lease.bean;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Data object for Customer
 * @author Saunak
 *
 */
@Value
@Builder
@Jacksonized
public class Customer {
	
	//Field for customer date of birth
	private String name;

	private String street;

	private String houseNumber;

	private String zipCode;

	private String place;

	private String emailAddress;

	private String phoneNumber;
	
}
