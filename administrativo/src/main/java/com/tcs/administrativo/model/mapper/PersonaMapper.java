package com.tcs.administrativo.model.mapper;

import com.tcs.administrativo.model.dto.PersonaDto;
import com.tcs.administrativo.model.entity.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaDto toDto(Persona persona);
    Persona toEntity(PersonaDto personaDto);
}
