package com.baseapp.it_support_api.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EquipmentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String imageUrl;
    private String equipmentName;
    private LocalDate purchaseDate;
    private String assetValue;
    private String serialNumber;
    private Long userId;
    @JsonIgnore
    private List<FaultDTO> faults;
}