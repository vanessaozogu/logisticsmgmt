package com.ironhack.logisticsmgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Table(name = "dispatchers")
@Entity
public class Dispatcher extends Employee {
    private String dispatcherId;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnoreProperties({"drivers", "dispatchers"})
    private TransportCompany company;

    public Dispatcher(String dispatcherId, TransportCompany company) {
        this.dispatcherId = dispatcherId;
        this.company = company;
    }

    public Dispatcher() {

    }

    public String getDispatcherId() {
        return dispatcherId;
    }

    public void setDispatcherId(String dispatcherId) {
        this.dispatcherId = dispatcherId;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public void setCompany(TransportCompany company) {
        this.company = company;
    }
}
