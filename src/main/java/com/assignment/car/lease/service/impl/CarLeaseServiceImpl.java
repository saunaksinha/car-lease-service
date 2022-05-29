package com.assignment.car.lease.service.impl;

import com.assignment.car.lease.bean.PensionResultDetails;
import com.assignment.car.lease.bean.Customer;
import com.assignment.car.lease.bean.PensionRuleSet;
import com.assignment.car.lease.service.CarLeaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

/**
 * Service implementation layer for input validation & to create the final response JSON
 *
 * @author Saunak
 */
@Service
@Slf4j
public class CarLeaseServiceImpl implements CarLeaseService {

    @Autowired
    PensionRuleSet pensionRuleSet;

    /**
     * This method does the initial validation for the request payload that came in as part of the input to the service
     *
     * @param customer
     * @return customer
     */
    public PensionResultDetails calculateAowAge(Customer customer){
        PensionResultDetails pensionResultDetails =
                    PensionResultDetails.builder()
                            .build();
        return pensionResultDetails;
    }

    private String getAgeOnAowDate(LocalDate dateOfBirth, LocalDate awoDateCustomer) {
        Period period = Period.between(dateOfBirth, awoDateCustomer);
        return period.getYears() + " years, " + period.getMonths() + " months and " + period.getDays() + " days.";
    }

}
