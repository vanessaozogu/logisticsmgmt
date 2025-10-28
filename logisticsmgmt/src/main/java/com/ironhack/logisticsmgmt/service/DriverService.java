package com.ironhack.logisticsmgmt.service;

import com.ironhack.logisticsmgmt.model.Driver;
import com.ironhack.logisticsmgmt.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Long id, Driver driverDetails) {
        Driver driver = getDriverById(id);
        driver.setName(driverDetails.getName());
        driver.setDriverId(driverDetails.getDriverId());
        driver.setCompany(driverDetails.getCompany());
        return driverRepository.save(driver);
    }
    public void deleteDriverById(Long id) {
        driverRepository.deleteById(id);
    }
}
