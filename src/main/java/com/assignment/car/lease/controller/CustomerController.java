package com.assignment.car.lease.controller;

import com.assignment.car.lease.repository.ICustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller to maintain basic customer attributes
 *
 * @author Saunak
 */
@Slf4j
@RequestMapping("/api/car-lease/")
@RestController
@PropertySource("classpath:application.yml")
public class CustomerController {

  @Autowired ICustomerRepo customerRepo;

  /**
   * To create a customer
   *
   * @param customer
   * @return
   */
  @PostMapping(
      value = "/customers",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('MODERATOR')")
  public ResponseEntity<com.assignment.car.lease.entity.Customer> createCustomer(
      @RequestBody @Valid com.assignment.car.lease.entity.Customer customer) {
    try {
      return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * To get all customers
   *
   * @return
   */
  @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<List<com.assignment.car.lease.entity.Customer>> getAllCustomers() {
    List<com.assignment.car.lease.entity.Customer> list = customerRepo.findAll();

    if (list.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  /**
   * To get a particular customer
   *
   * @param customerId
   * @return
   */
  @GetMapping(value = "/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<com.assignment.car.lease.entity.Customer> getCustomer(@PathVariable Long customerId) {
    Optional<com.assignment.car.lease.entity.Customer> customer = customerRepo.findById(customerId);
    if (customer.isPresent()) {
      return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * To update an existing customer
   *
   * @param customer
   * @return
   */
  @PutMapping(
      value = "/customers/{customerId}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('MODERATOR')")
  public ResponseEntity<com.assignment.car.lease.entity.Customer> updateCustomer(
      @RequestBody com.assignment.car.lease.entity.Customer customer) {
    try {
      return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * To delete a customer
   *
   * @param customerId
   * @return
   */
  @DeleteMapping(
      value = "/customers/{customerId}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<com.assignment.car.lease.entity.Customer> deleteCustomer(@PathVariable Long customerId) {
    try {
      Optional<com.assignment.car.lease.entity.Customer> customer =
          customerRepo.findById(customerId);
      customer.ifPresent(c -> customerRepo.delete(c));
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
