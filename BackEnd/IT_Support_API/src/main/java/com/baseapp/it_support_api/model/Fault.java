package com.baseapp.it_support_api.model;

import com.baseapp.it_support_api.model.Enum.FaultStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Fault {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private LocalDate SignalDate;
    @Enumerated(EnumType.STRING)
    private FaultStatus status;

    @ManyToOne
    @JoinColumn(name = "equipment_id",nullable = false)
    private Equipment equipment;
}
