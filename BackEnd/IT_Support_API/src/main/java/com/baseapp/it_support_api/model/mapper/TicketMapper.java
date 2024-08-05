package com.baseapp.it_support_api.model.mapper;


import com.baseapp.it_support_api.model.DTO.EquipmentDTO;
import com.baseapp.it_support_api.model.DTO.TicketDTO;
import com.baseapp.it_support_api.model.Equipment;
import com.baseapp.it_support_api.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mappings({
            @Mapping(source = "userId",target = "user.id"),
            @Mapping(source = "technicianId",target = "technician.id"),
    })
    Ticket toEntity(TicketDTO DTO);

    @Mappings({
            @Mapping(source = "user.id",target = "userId"),
            @Mapping(source = "technician.id",target = "technicianId"),
    })
    TicketDTO toDTO(Ticket entity);

    List<TicketDTO> toDTOList(List<Ticket> tickets);
    List<Ticket> toEntityList(List<TicketDTO> ticketDTOS);


}
