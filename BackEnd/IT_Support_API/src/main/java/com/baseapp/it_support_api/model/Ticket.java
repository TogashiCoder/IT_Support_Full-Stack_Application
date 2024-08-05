package com.baseapp.it_support_api.model;

import com.baseapp.it_support_api.model.Enum.TicketStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

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
    @JoinColumn(name = "technician_id",nullable = false)
    private Technician technician;
}
