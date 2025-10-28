package com.ironhack.logisticsmgmt.controller;

import com.ironhack.logisticsmgmt.model.TransportCompany;
import com.ironhack.logisticsmgmt.service.TransportCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class TransportCompanyController {

    public final TransportCompanyService transportCompanyService;
    public TransportCompanyController(TransportCompanyService transportCompanyService) {
        this.transportCompanyService = transportCompanyService;
    }

    @GetMapping
    public List<TransportCompany> getAllCompanies() {
        return transportCompanyService.getAllTransportCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportCompany> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(transportCompanyService.getTransportCompanyById(id));
    }

    @PostMapping
    public ResponseEntity<TransportCompany> createCompany(@RequestBody TransportCompany company) {
        return ResponseEntity.ok(transportCompanyService.createTransportCompany(company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportCompany> updateCompany(@PathVariable Long id, @RequestBody TransportCompany company) {
        return ResponseEntity.ok(transportCompanyService.updateTransportCompany(id, company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        transportCompanyService.deleteTransportCompany(id);
        return ResponseEntity.noContent().build();
    }
}
