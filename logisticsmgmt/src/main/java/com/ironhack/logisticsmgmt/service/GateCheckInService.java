package com.ironhack.logisticsmgmt.service;

import com.ironhack.logisticsmgmt.enums.Gate;
import com.ironhack.logisticsmgmt.model.Controller;
import com.ironhack.logisticsmgmt.repository.ControllerRepository;
import com.ironhack.logisticsmgmt.model.GateCheckIn;
import com.ironhack.logisticsmgmt.model.Route;
import com.ironhack.logisticsmgmt.repository.GateCheckInRepository;
import com.ironhack.logisticsmgmt.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GateCheckInService {
    private final GateCheckInRepository gateCheckInRepository;
    private final RouteRepository routeRepository;
    private final ControllerRepository controllerRepository;

    public GateCheckInService(GateCheckInRepository gateCheckInRepository, RouteRepository routeRepository, ControllerRepository controllerRepository) {
        this.gateCheckInRepository = gateCheckInRepository;
        this.routeRepository = routeRepository;
        this.controllerRepository = controllerRepository;
    }

    public GateCheckIn checkInDriver(Long routeId, Long controllerId, Gate gate) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Controller controller = controllerRepository.findById(controllerId)
                .orElseThrow(() -> new RuntimeException("Controller not found"));

        LocalDateTime now = LocalDateTime.now();
        boolean onTime = now.isBefore(route.getExpectedArrival().plusMinutes(10));

        GateCheckIn checkIn = new GateCheckIn();
        checkIn.setRoute(route);
        checkIn.setGate(gate);
        checkIn.setArrivalTime(now);
        checkIn.setOnTime(onTime);
        checkIn.setController(controller);

        return gateCheckInRepository.save(checkIn);
    }
}
