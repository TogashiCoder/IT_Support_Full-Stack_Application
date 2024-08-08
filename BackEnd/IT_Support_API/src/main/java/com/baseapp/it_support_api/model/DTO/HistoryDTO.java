package com.baseapp.it_support_api.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private LocalDate date;
    private Long faultId;
    private Long equipmentId;
}
