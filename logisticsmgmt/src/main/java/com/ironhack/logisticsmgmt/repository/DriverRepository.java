package com.ironhack.logisticsmgmt.repository;

import com.ironhack.logisticsmgmt.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
