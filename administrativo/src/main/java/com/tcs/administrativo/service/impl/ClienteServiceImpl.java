package com.tcs.administrativo.service.impl;

import com.tcs.administrativo.model.dto.ClienteDto;
import com.tcs.administrativo.model.dto.PersonaDto;
import com.tcs.administrativo.model.entity.Cliente;
import com.tcs.administrativo.model.entity.Persona;
import com.tcs.administrativo.model.mapper.ClienteMapper;
import com.tcs.administrativo.model.mapper.PersonaMapper;
import com.tcs.administrativo.repository.ClienteRepository;
import com.tcs.administrativo.repository.PersonaRepository;
import com.tcs.administrativo.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PersonaMapper personaMapper;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto getClienteById(UUID clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        return clienteMapper.toDto(cliente);
    }

    @Override
    public ClienteDto getClienteByIdentificacion(String identificacion) {
        Optional<Cliente> cliente = clienteRepository.findByPersona_Identificacion(identificacion);
        if (cliente.isEmpty()){
            throw new EntityNotFoundException("Cliente no encontrado por identificacion: " + identificacion);
        }
        return clienteMapper.toDto(cliente.get());
    }

    @Override
    @Transactional
    public ClienteDto createCliente(ClienteDto clienteDto) {
        Persona persona = personaMapper.toEntity(clienteDto.getPersona());
        Optional<Persona> personaExistente = personaRepository.findByIdentificacion(persona.getIdentificacion());
        if(personaExistente.isPresent()){
            throw new RuntimeException("Persona con identificacion: "+ persona.getIdentificacion() +" ya existe");
        }
        Optional<Cliente> clienteExistente = clienteRepository.findByPersona_Identificacion(persona.getIdentificacion());
        if(clienteExistente.isPresent()){
            throw new RuntimeException("Cliente relacionado a persona con identificacion: "+ persona.getIdentificacion() +" ya existe");
        }
        persona = personaRepository.save(persona);
        Cliente cliente = clienteMapper.toEntity(clienteDto);
        cliente.setPersona(persona);

        String passwordRaw = cliente.getContrasena();
        String passwordHash = passwordEncoder.encode(passwordRaw);
        cliente.setContrasena(passwordHash);

        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public ClienteDto updateCliente(UUID clienteId, ClienteDto clienteDto) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        Persona personaAux = cliente.getPersona();
        Optional<Cliente> clienteExistente = clienteRepository.findByPersona_Identificacion(clienteDto.getPersona().getIdentificacion());
        if (clienteExistente.isPresent()){
            boolean selfEdition = clienteExistente.get().getClienteId().equals(clienteId);
            if(!selfEdition){
                throw new RuntimeException("Ya existe un cliente con identificacion: " + personaAux.getIdentificacion());
            }
        }

        Persona persona = cliente.getPersona();
        PersonaDto personaDto = clienteDto.getPersona();
        persona.setNombre(personaDto.getNombre());
        persona.setGenero(personaDto.getGenero());
        persona.setEdad(personaDto.getEdad());
        persona.setIdentificacion(personaDto.getIdentificacion());
        persona.setDireccion(personaDto.getDireccion());
        persona.setTelefono(personaDto.getTelefono());
        personaRepository.save(persona);

        // SIEMPRE DEBE HABER UN METODO SEPARADO PARA CAMBIO DE CONTRASEÑAS
        // cliente.setContrasena(clienteDto.getContrasena());
        cliente.setEstado(clienteDto.getEstado());
        cliente = clienteRepository.save(cliente);

        return clienteMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public void deleteCliente(UUID clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clienteRepository.delete(cliente);
    }
}
