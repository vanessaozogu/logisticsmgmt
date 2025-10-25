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

    @OneToOne
    private Driver driver;

    private String stageLocation;
}
