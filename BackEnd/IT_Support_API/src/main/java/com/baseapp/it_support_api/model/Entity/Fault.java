package com.baseapp.it_support_api.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @OneToMany(mappedBy = "fault",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
