package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.DTO.TicketDTO;
import com.baseapp.it_support_api.model.Entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {


    @Mapping(source = "user.id", target = "userId")
   // @Mapping(source = "technician.id", target = "technicianId")
    @Mapping(source = "fault.id", target = "faultId")
    @Mapping(source = "equipment.id", target = "equipmentId")
    TicketDTO toDTO(Ticket ticket);

    @Mapping(source = "userId", target = "user.id")
   // @Mapping(source = "technicianId", target = "technician.id")
    @Mapping(source = "faultId", target = "fault.id")
    @Mapping(source = "equipmentId", target = "equipment.id")
    Ticket toEntity(TicketDTO ticketDTO);

    List<TicketDTO> toDTOList(List<Ticket> tickets);

    List<Ticket> toEntityList(List<TicketDTO> ticketDTOS);
}
