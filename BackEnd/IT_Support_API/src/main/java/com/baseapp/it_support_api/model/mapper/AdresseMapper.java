package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.Adresse;
import com.baseapp.it_support_api.model.DTO.AdresseDTO;
import com.baseapp.it_support_api.model.DTO.UserDTO;
import com.baseapp.it_support_api.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdresseMapper {

    Adresse toEntity(AdresseDTO adresseDTO);
    AdresseDTO toDTO(Adresse adresse);
    List<AdresseDTO> toDTOList(List<Adresse> adresses);
    List<Adresse> toEntityList(List<AdresseDTO> adresseDTOS);
}
