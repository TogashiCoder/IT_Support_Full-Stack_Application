package com.baseapp.it_support_api.model.DTO;

import com.baseapp.it_support_api.model.Enum.TicketStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

//EXTRA
@Data
public class TicketWithDetailsDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate creationDate;

    private String description;
    private TicketStatus status;
    private UserDTO user;
    private FaultDTO fault;
    private EquipmentDTO equipment;
    private TechnicianDTO technician;
}
