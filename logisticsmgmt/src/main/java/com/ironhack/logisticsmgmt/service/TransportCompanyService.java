package com.ironhack.logisticsmgmt.service;

import com.ironhack.logisticsmgmt.model.TransportCompany;
import com.ironhack.logisticsmgmt.repository.TransportCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportCompanyService {
    public final TransportCompanyRepository transportCompanyRepository;

    public TransportCompanyService(TransportCompanyRepository transportCompanyRepository) {
        this.transportCompanyRepository = transportCompanyRepository;
    }

    public List<TransportCompany> getAllTransportCompanies() {
        return transportCompanyRepository.findAll();
    }

    public TransportCompany getTransportCompanyById(Long id) {
        return transportCompanyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport Company not found with ID: " + id));
    }
    public TransportCompany createTransportCompany(TransportCompany company) {
        return transportCompanyRepository.save(company);
    }

    public TransportCompany updateTransportCompany(Long id, TransportCompany updatedCompany) {
        TransportCompany existingCompany = getTransportCompanyById(id);
        existingCompany.setName(updatedCompany.getName());
        return transportCompanyRepository.save(existingCompany);
    }

    public void deleteTransportCompany(Long id) {
        transportCompanyRepository.deleteById(id);
    }

}
