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
public class LeaseResponse {
	
	//Field for lease Rate.
	private BigDecimal leaseRate;
}
