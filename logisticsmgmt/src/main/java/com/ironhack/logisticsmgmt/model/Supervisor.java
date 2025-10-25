package com.ironhack.logisticsmgmt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "supervisors")
@Entity
public class Supervisor extends Employee{
}
