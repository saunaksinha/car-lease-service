package com.assignment.car.lease.controller;

import com.assignment.car.lease.entity.Car;
import com.assignment.car.lease.repository.ICarRepo;
import com.assignment.car.lease.service.CarLeaseService;
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

import java.util.List;
import java.util.Optional;

/**
 * Controller to maintain basic car attributes
 *
 * @author Saunak
 */
@Slf4j
@RequestMapping("/api/car-lease/")
@RestController
@PropertySource("classpath:application.yml")
public class CarController {

  @Autowired CarLeaseService carLeaseService;

  @Autowired ICarRepo carRepo;

  /**
   * To create a car
   * @param car
   * @return
   */
  @PostMapping(
      value = "/cars",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Car> createCar(@RequestBody Car car) {
    try {
      return new ResponseEntity<>(carRepo.save(car), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * To get all cars
   * @return
   */
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  @GetMapping(value = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Car>> getAllCars() {
    List<Car> list = carRepo.findAll();

    if (list.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  /**
   * To get a particular car
   * @param carId
   * @return
   */
  @GetMapping(value = "/cars/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Car> getCar(@PathVariable Long carId) {
    Optional<Car> car = carRepo.findById(carId);
    if (car.isPresent()) {
      return new ResponseEntity<>(car.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * To update an existing car
   * @param car
   * @return
   */
  @PutMapping(
      value = "/cars/{carId}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Car> updateCar(@RequestBody Car car) {
    try {
      return new ResponseEntity<>(carRepo.save(car), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * To delete a car
   * @param carId
   * @return
   */
  @DeleteMapping(
      value = "/cars/{carId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Car> deleteCustomer(@PathVariable Long carId) {
    try {
      Optional<Car> car = carRepo.findById(carId);
      car.ifPresent(c -> carRepo.delete(c));
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
