package com.baseapp.it_support_api.model;

import com.baseapp.it_support_api.model.Enum.FaultStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
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
