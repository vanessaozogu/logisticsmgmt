package com.ironhack.logisticsmgmt.service;

import com.ironhack.logisticsmgmt.model.Driver;
import com.ironhack.logisticsmgmt.model.TransportCompany;
import com.ironhack.logisticsmgmt.repository.DriverRepository;
import com.ironhack.logisticsmgmt.repository.TransportCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;
    private final TransportCompanyRepository transportCompanyRepository;

    public DriverService(DriverRepository driverRepository, TransportCompanyRepository transportCompanyRepository) {
        this.driverRepository = driverRepository;
        this.transportCompanyRepository = transportCompanyRepository;
    }

    public Driver createDriver(Driver driver) {
        // Get the actual company from the DB
        TransportCompany company = transportCompanyRepository.findById(driver.getCompany().getId())
                .orElseThrow(() -> new RuntimeException("Company not found with id " + driver.getCompany().getId()));

        // Set it on the driver
        driver.setCompany(company);

        // Save driver
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver with id " + id + " not found"));
    }

    public Driver updateDriver(Long id, Driver driverDetails) {
        Driver driver = getDriverById(id);
        driver.setName(driverDetails.getName());
        driver.setDriverId(driverDetails.getDriverId());
        driver.setCompany(driverDetails.getCompany());
        return driverRepository.save(driver);
    }
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
