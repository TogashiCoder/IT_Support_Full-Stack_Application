package com.baseapp.it_support_api.model.DTO;

import com.baseapp.it_support_api.model.Enum.FaultStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FaultDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String description;
    private LocalDate signalDate;
    private FaultStatus status;
    private Long equipmentId;
}