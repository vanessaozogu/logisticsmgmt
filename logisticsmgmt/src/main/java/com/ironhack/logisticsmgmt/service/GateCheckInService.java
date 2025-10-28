package com.ironhack.logisticsmgmt.service;

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

    public GateCheckInService(GateCheckInRepository gateCheckInRepository, RouteRepository routeRepository) {
        this.gateCheckInRepository = gateCheckInRepository;
        this.routeRepository = routeRepository;
    }

    public GateCheckIn checkInDriver(Long routeId, Long controllerId, String gateName) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        LocalDateTime now = LocalDateTime.now();
        boolean onTime = now.isBefore(route.getExpectedArrival().plusMinutes(10));

        GateCheckIn checkIn = new GateCheckIn();
        checkIn.setRoute(route);
        checkIn.setGate(gateName);
        checkIn.setArrivalTime(now);
        checkIn.setOnTime(onTime);

        return gateCheckInRepository.save(checkIn);
    }
}
