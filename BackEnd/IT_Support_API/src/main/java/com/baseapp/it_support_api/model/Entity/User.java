package com.baseapp.it_support_api.model.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("USER")
public class User extends Person{
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}
