package com.baseapp.it_support_api.model.DTO;

import com.baseapp.it_support_api.model.Entity.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;
@Data
public class TechnicianDTO  extends PersonDTO{
    @JsonIgnore
    private List<Ticket> tickets;


}
