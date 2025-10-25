package com.ironhack.logisticsmgmt.repository;

import com.ironhack.logisticsmgmt.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
