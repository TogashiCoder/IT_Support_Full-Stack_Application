package com.baseapp.it_support_api.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @OneToMany(mappedBy = "equipment",cascade = CascadeType.ALL)
    private List<Fault> faults;
}
