package com.ironhack.logisticsmgmt.config;

import com.ironhack.logisticsmgmt.enums.Gate;
import com.ironhack.logisticsmgmt.model.*;
import com.ironhack.logisticsmgmt.repository.*;
import com.ironhack.logisticsmgmt.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LogisticsDataLoader implements CommandLineRunner {

    private final TransportCompanyRepository transportCompanyRepository;
    private final DriverRepository driverRepository;
    private final DispatcherRepository dispatcherRepository;
    private final StageRepository stageRepository;
    private final RouteRepository routeRepository;
    private final EmployeeRepository employeeRepository;
    private final GateCheckInRepository gateCheckInRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🚛 Loading logistics data...");

        // ---- 1️⃣ Create Transport Companies ----
        TransportCompany swift = new TransportCompany();
        swift.setName("Swift Logistics Ltd");
        transportCompanyRepository.save(swift);

        TransportCompany adeoye = new TransportCompany();
        adeoye.setName("Adeoye Haulage");
        transportCompanyRepository.save(adeoye);

        // ---- 2️⃣ Create Stages ----
        Stage A01 = new Stage("A01", "A01");
        Stage B43 = new Stage("B43", "B43");
        Stage D01 = new Stage("D01", "D01");
        Stage C22 = new Stage("C22", "C22");
        stageRepository.saveAll(List.of(A01, B43, D01, C22));

        // ---- 3️⃣ Create Drivers ----
        Driver driver1 = new Driver("DA-001", "Michael Drive", swift);
        Driver driver2 = new Driver("DA-002", "David Ride", adeoye);
        Driver driver3 = new Driver("DA-003", "Francis Speed", adeoye);
        System.out.println("Saving driver: " + driver1.getName() + " - " + driver1.getDriverId());
        driverRepository.saveAll(List.of(driver1, driver2, driver3));

        // ---- 4️⃣ Create Dispatchers ----
        Dispatcher dispatcher1 = new Dispatcher();
        dispatcher1.setName("Stanley Hudson");
        dispatcher1.setCompany(swift);

        Dispatcher dispatcher2 = new Dispatcher();
        dispatcher2.setName("Jim Halpert");
        dispatcher2.setCompany(adeoye);

        dispatcherRepository.saveAll(List.of(dispatcher1, dispatcher2));

        // ---- 5️⃣ Create Employees ----
        Supervisor supervisor = new Supervisor();
        supervisor.setName("Toby Anderson");
        supervisor.setEmail("toby@amz.com");
        supervisor.setRole(Role.SUPERVISOR);

        Controller controller = new Controller();
        controller.setName("Harry Potter");
        controller.setEmail("harry@amz.com");
        controller.setRole(Role.CONTROLLER);

        employeeRepository.saveAll(List.of(supervisor, controller));

        // ---- 6️⃣ Create Routes ----
        Route route1 = new Route();
        route1.setRouteCode("CA_A101");
        route1.setWave("10:20");
        route1.setExpectedArrival(LocalDateTime.now().plusHours(6));
        route1.setDriver(driver1);
        route1.setStage(A01);

        Route route2 = new Route();
        route2.setRouteCode("CA_A102");
        route2.setWave("10:40");
        route2.setExpectedArrival(LocalDateTime.now().plusHours(12));
        route2.setDriver(driver2);
        route2.setStage(D01);

        routeRepository.saveAll(List.of(route1, route2));

        // ---- 7️⃣ Create Gate Check-ins ----
        GateCheckIn checkIn1 = new GateCheckIn();
        checkIn1.setGate(Gate.NORTH);
        checkIn1.setArrivalTime(LocalDateTime.now());
        checkIn1.setOnTime(true);
        checkIn1.setController(controller);
        checkIn1.setRoute(route1);

        GateCheckIn checkIn2 = new GateCheckIn();
        checkIn2.setGate(Gate.SOUTH);
        checkIn2.setArrivalTime(LocalDateTime.now().plusHours(2));
        checkIn2.setOnTime(false);
        checkIn2.setController(controller);
        checkIn2.setRoute(route2);

        gateCheckInRepository.saveAll(List.of(checkIn1, checkIn2));

        System.out.println("Saving driver: " + driver1.getName() + " - " + driver1.getDriverId());
        driverRepository.saveAll(List.of(driver1, driver2, driver3));


        System.out.println("✅ Logistics sample data loaded successfully!");
    }
}
