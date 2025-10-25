package com.ironhack.logisticsmgmt.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String routeCode;
    private String wave;
    private LocalDateTime expectedArrival;

    @ManyToOne
    private Driver driver;

    private String stageLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getWave() {
        return wave;
    }

    public void setWave(String wave) {
        this.wave = wave;
    }

    public LocalDateTime getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(LocalDateTime expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getStageLocation() {
        return stageLocation;
    }

    public void setStageLocation(String stageLocation) {
        this.stageLocation = stageLocation;
    }
}
