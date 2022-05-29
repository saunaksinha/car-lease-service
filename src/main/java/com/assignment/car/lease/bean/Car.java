package com.assignment.car.lease.bean;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

/**
 * Data object for the request payload
 * @author Saunak
 *
 */
@Value
@Builder
@Jacksonized
public class Car {
	
	//Field for customer date of birth
	private String make;

	private String model;

	private String version;

	private String numberOfDoors;

	private String co2Emission;

	private String grossPrice;

	private String netPrice;
	
}
