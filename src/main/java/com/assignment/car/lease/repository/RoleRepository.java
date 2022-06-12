package com.assignment.car.lease.repository;

import java.util.Optional;

import com.assignment.car.lease.entity.ERole;
import com.assignment.car.lease.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
