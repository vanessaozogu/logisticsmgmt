package com.ironhack.logisticsmgmt.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "transport_companies")
public class TransportCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private List<Dispatchers> dispatchers;
    private List<Driver> drivers;

}
