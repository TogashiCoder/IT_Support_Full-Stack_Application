package com.baseapp.it_support_api.model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@Entity
@DiscriminatorValue("TECHNICIAN")
public class Technician extends Person {
    @OneToMany(mappedBy = "technician",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
