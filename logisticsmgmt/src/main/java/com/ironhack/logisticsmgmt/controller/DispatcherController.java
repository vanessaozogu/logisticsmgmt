package com.ironhack.logisticsmgmt.controller;

import com.ironhack.logisticsmgmt.model.Dispatcher;
import com.ironhack.logisticsmgmt.service.DispatcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispatchers")
public class DispatcherController {
    private final DispatcherService dispatcherService;

    public DispatcherController(DispatcherService dispatcherService) {
        this.dispatcherService = dispatcherService;
    }

    @GetMapping
    public List<Dispatcher> getAllDispatchers() {
        return dispatcherService.getAllDispatchers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispatcher> getDispatcherById(@PathVariable Long id) {
        return ResponseEntity.ok(dispatcherService.getDispatcherById(id));
    }

    @PostMapping
    public ResponseEntity<Dispatcher> createDispatcher(@RequestBody Dispatcher dispatcher) {
        return ResponseEntity.ok(dispatcherService.createDispatcher(dispatcher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispatcher> updateDispatcher(@PathVariable Long id, @RequestBody Dispatcher dispatcher) {
        return ResponseEntity.ok(dispatcherService.updateDispatcher(id, dispatcher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispatcher(@PathVariable Long id) {
        dispatcherService.deleteDispatcher(id);
        return ResponseEntity.noContent().build();
    }
}
