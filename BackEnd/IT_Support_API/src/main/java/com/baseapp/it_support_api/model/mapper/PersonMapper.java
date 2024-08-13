package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.DTO.EquipmentDTO;
import com.baseapp.it_support_api.model.DTO.PersonDTO;
import com.baseapp.it_support_api.model.Entity.Equipment;
import com.baseapp.it_support_api.model.Entity.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonDTO personDTO);
    PersonDTO toDTO(Person person);
    List<PersonDTO> toDTOList(List<Person> personList);
    List<Person> toEntityList(List<PersonDTO> personDTOS);
}
