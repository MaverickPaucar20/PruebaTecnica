package com.tcs.administrativo.service;

import com.tcs.administrativo.model.dto.PersonaDto;
import com.tcs.administrativo.model.entity.Persona;

import java.util.List;
import java.util.UUID;

public interface PersonaService {
    PersonaDto getPersona(UUID personaId);
    List<PersonaDto> getAllPersonas();
}
