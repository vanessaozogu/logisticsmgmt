package com.ironhack.logisticsmgmt.model;

import jakarta.persistence.*;

@Table(name = "dispatchers")
@Entity
public class Dispatcher extends Employee {
    private String dispatcherId;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private TransportCompany company;

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
