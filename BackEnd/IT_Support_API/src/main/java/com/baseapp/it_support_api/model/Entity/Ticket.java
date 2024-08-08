package com.baseapp.it_support_api.model.Entity;

import com.baseapp.it_support_api.model.Enum.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate creationDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "technician_id",nullable = true)
    private Technician technician;
    @ManyToOne
    @JoinColumn(name = "fault_id",nullable = true)
    private Fault fault;
    @ManyToOne
    @JoinColumn(name = "equipment_id",nullable = true)
    private Equipment equipment;
}