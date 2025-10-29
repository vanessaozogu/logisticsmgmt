package com.ironhack.logisticsmgmt.service;

import com.ironhack.logisticsmgmt.model.Dispatcher;
import com.ironhack.logisticsmgmt.repository.DispatcherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispatcherService {
    private final DispatcherRepository dispatcherRepository;

    public DispatcherService(DispatcherRepository dispatcherRepository) {
        this.dispatcherRepository = dispatcherRepository;
    }

    public List<Dispatcher> getAllDispatchers() {
        return dispatcherRepository.findAll();
    }

    public Dispatcher getDispatcherById(Long id) {
        return dispatcherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatcher not found with ID: " + id));
    }

    public Dispatcher createDispatcher(Dispatcher dispatcher) {
        return dispatcherRepository.save(dispatcher);
    }

    public Dispatcher updateDispatcher(Long id, Dispatcher updatedDispatcher) {
        Dispatcher existingDispatcher = getDispatcherById(id);
        existingDispatcher.setName(updatedDispatcher.getName());
        existingDispatcher.setCompany(updatedDispatcher.getCompany());
        return dispatcherRepository.save(existingDispatcher);
    }

    public void deleteDispatcher(Long id) {
        dispatcherRepository.deleteById(id);
    }
}
