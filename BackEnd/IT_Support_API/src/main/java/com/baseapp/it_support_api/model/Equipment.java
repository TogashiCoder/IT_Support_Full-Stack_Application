package com.baseapp.it_support_api.model;

import com.baseapp.it_support_api.model.mapper.History;
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
//    @ManyToOne
//    @JoinColumn(name = "user_id",nullable = false)
//    private User user;
//    @OneToMany(mappedBy = "equipment",cascade = CascadeType.ALL)
//    private List<Fault> faults;
    @OneToMany(mappedBy = "equipment")
    private List<History> histories;
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
