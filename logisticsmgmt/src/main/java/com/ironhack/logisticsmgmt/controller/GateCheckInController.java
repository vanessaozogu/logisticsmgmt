package com.ironhack.logisticsmgmt.controller;


import com.ironhack.logisticsmgmt.enums.Gate;
import com.ironhack.logisticsmgmt.model.GateCheckIn;
import com.ironhack.logisticsmgmt.service.GateCheckInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkins")
public class GateCheckInController {

    private final GateCheckInService gateCheckInService;

    public GateCheckInController(GateCheckInService gateCheckInService) {
        this.gateCheckInService = gateCheckInService;
    }

    public ResponseEntity<GateCheckIn> createCheckIn(
            @RequestParam Long routeId,
            @RequestParam Long controllerId,
            @RequestParam Gate gate) {
        GateCheckIn checkIn = gateCheckInService.checkInDriver(routeId, controllerId, gate);
        return ResponseEntity.ok(checkIn);
    }
}
