package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.DTO.TicketWithDetailsDTO;
import com.baseapp.it_support_api.model.Entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, FaultMapper.class, EquipmentMapper.class, TechnicianMapper.class})
public interface TicketWithDetailsMapper {

    @Mapping(source = "user", target = "user")
    @Mapping(source = "fault", target = "fault")
    @Mapping(source = "equipment", target = "equipment")
    @Mapping(source = "technician", target = "technician")
    TicketWithDetailsDTO toDTO(Ticket ticket);

    List<TicketWithDetailsDTO> toDTOList(List<Ticket> tickets);
}