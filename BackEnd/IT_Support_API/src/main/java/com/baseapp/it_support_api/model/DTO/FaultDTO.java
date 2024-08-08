package com.baseapp.it_support_api.model.DTO;

import com.baseapp.it_support_api.model.Entity.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FaultDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String description;
    private LocalDate signalDate;
    @JsonIgnore
    private List<Ticket> tickets;
}
