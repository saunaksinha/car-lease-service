package com.assignment.car.lease.service.impl;

import com.assignment.car.lease.bean.LeaseRequest;
import com.assignment.car.lease.service.CarLeaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Service implementation layer for input validation & to create the final response JSON
 *
 * @author Saunak
 */
@Service
@Slf4j
public class CarLeaseServiceImpl implements CarLeaseService {

  /**
   * @param leaseRequest
   * @return
   */
  @Override
  public BigDecimal calcLeaseRate(LeaseRequest leaseRequest) {
        return null;
    }
}
