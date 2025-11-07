package com.ironhack.logisticsmgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "transport_companies")
public class TransportCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "company")
    @JsonIgnoreProperties("company")
    private List<Dispatcher> dispatchers;

    @OneToMany(mappedBy = "company")
    @JsonIgnoreProperties("company")
    private List<Driver> drivers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dispatcher> getDispatchers() {
        return dispatchers;
    }

    public void setDispatchers(List<Dispatcher> dispatchers) {
        this.dispatchers = dispatchers;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
