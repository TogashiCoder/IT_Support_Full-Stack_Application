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
public class Equipment {
    @Id
    @GeneratedValue
    private Long id;
    private String imageUrl;
    private String equipmentName;
    private LocalDate purchaseDate;
    private String assetValue;
    private String serialNumber;
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
