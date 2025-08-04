package com.tcs.administrativo.service.impl;

import com.tcs.administrativo.model.dto.PersonaDto;
import com.tcs.administrativo.model.entity.Persona;
import com.tcs.administrativo.model.mapper.PersonaMapper;
import com.tcs.administrativo.repository.PersonaRepository;
import com.tcs.administrativo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public List<PersonaDto> getAllPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return personas.stream()
                .map(personaMapper::toDto)
                .toList();
    }

    @Override
    public PersonaDto getPersona(UUID personaId) {
        return null;
    }
}
