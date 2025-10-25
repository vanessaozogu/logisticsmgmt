package com.ironhack.logisticsmgmt.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String driverId;
    private String name;
    @ManyToOne
    private TransportCompany company;

    private List<Route> routes;

}
