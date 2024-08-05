package com.baseapp.it_support_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Technician extends Person {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "technician",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
