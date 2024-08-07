package com.baseapp.it_support_api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Person {
}
