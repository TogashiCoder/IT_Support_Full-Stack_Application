package com.baseapp.it_support_api.model.mapper;
import com.baseapp.it_support_api.model.DTO.FaultDTO;
import com.baseapp.it_support_api.model.Entity.Fault;
import org.mapstruct.Mapper;



import java.util.List;

@Mapper(componentModel = "spring")
public interface FaultMapper {
    Fault toEntity(FaultDTO faultDTO);
    FaultDTO toDTO(Fault fault);
    List<FaultDTO> toDTOList(List<Fault> faults);
    List<Fault> toEntityList(List<FaultDTO> faultDTOS);
}
