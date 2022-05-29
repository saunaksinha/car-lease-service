package com.assignment.car.lease.bean;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

/**
 * Data object for the request payload
 * @author Saunak
 *
 */
@Value
@Builder
@Jacksonized
public class LeaseRequest {
	
	//Field for customer date of birth
	private BigDecimal mileage;

	private int duration;

	private BigDecimal interestRate;

	private BigDecimal netPrice;

}
