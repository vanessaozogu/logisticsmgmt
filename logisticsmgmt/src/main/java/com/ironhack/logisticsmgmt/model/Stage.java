package com.ironhack.logisticsmgmt.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stageNumber;

    @OneToMany
    private List<Route> routes;
}
