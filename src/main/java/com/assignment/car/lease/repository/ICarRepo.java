package com.assignment.car.lease.repository;

import com.assignment.car.lease.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepo extends JpaRepository<Car, Long> {}
