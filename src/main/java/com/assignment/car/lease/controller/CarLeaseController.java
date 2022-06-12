package com.assignment.car.lease.controller;

import com.assignment.car.lease.bean.LeaseRequest;
import com.assignment.car.lease.bean.LeaseResponse;
import com.assignment.car.lease.service.CarLeaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Controller to calculate lease rate for car lease
 *
 * @author Saunak
 */
@Slf4j
@RequestMapping("/api/car-lease/")
@RestController
@PropertySource("classpath:application.yml")
public class CarLeaseController {

    @Autowired
    CarLeaseService carLeaseService;

    @PostMapping(value = "/calc-lease-rate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> calcLeaseRate(@RequestBody LeaseRequest leaseRequest) {
        log.info("param1 -> {}", (leaseRequest.getMileage()
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.FLOOR)
                .divide(leaseRequest.getNetPrice(), 2, RoundingMode.FLOOR)));

        log.info("param2 -> {}", leaseRequest.getInterestRate()
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.FLOOR)
                .multiply(leaseRequest.getNetPrice())
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.FLOOR));

        BigDecimal leaseRate =
                (leaseRequest.getMileage()
                        .divide(BigDecimal.valueOf(12), 2, RoundingMode.FLOOR)
                        .divide(leaseRequest.getNetPrice(), 2, RoundingMode.FLOOR))
                        .add((leaseRequest.getInterestRate()
                                .divide(BigDecimal.valueOf(100), 2, RoundingMode.FLOOR)
                                .multiply(leaseRequest.getNetPrice()))
                                .divide(BigDecimal.valueOf(12), 2, RoundingMode.FLOOR));
        LeaseResponse leaseResponse = LeaseResponse.builder().leaseRate(leaseRate).build();

        return new ResponseEntity<>(leaseResponse, HttpStatus.OK);
    }

}

