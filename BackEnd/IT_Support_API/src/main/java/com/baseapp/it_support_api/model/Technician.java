package com.baseapp.it_support_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Entity
@DiscriminatorValue("TECHNICIAN")
public class Technician extends Person {
    @OneToMany(mappedBy = "technician",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
