package com.baseapp.it_support_api.model;

import com.baseapp.it_support_api.model.mapper.History;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDate;
import java.util.List;


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
    @ManyToOne
    @JoinColumn(name = "equipment_id",nullable = false)
    private Equipment equipment;
    @OneToMany(mappedBy = "fault",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "fault")
    private List<History> histories;
}
