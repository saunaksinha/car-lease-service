package com.assignment.car.lease.service;

import com.assignment.car.lease.bean.LeaseRequest;

import java.math.BigDecimal;

public interface CarLeaseService {

    public BigDecimal calcLeaseRate(LeaseRequest leaseRequest);

}
