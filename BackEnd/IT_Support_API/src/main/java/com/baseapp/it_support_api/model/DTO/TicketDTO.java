package com.baseapp.it_support_api.model.DTO;

import com.baseapp.it_support_api.model.Enum.TicketStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TicketDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate creationDate;
    private String description;
    private TicketStatus status;
    private Long userId;
    private Long faultId;
    private Long equipmentId;
}
