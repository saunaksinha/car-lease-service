package com.assignment.car.lease.service;

import com.assignment.car.lease.bean.PensionResultDetails;
import com.assignment.car.lease.bean.Customer;

public interface CarLeaseService {

    public PensionResultDetails calculateAowAge(Customer customer);

}
