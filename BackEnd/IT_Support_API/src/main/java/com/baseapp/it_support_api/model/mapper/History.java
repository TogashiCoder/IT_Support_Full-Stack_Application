package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.Equipment;
import com.baseapp.it_support_api.model.Fault;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class History {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "equipment_id",nullable = false)
    private Equipment equipment;
    @ManyToOne
    @JoinColumn(name = "fault_id",nullable = false)
    private Fault fault;



}
