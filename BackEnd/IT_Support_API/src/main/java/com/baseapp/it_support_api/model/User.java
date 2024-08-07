package com.baseapp.it_support_api.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;



@Getter
@Setter
@Entity
@DiscriminatorValue("USER")
public class User extends Person{
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Equipment> equipments;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}
