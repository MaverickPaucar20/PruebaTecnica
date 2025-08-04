package com.tcs.administrativo.service;

import com.tcs.administrativo.model.dto.ClienteDto;
import com.tcs.administrativo.model.entity.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    List<ClienteDto> getAllClientes();
    ClienteDto getClienteById(UUID clienteId);
    ClienteDto getClienteByIdentificacion(String identificacion);
    ClienteDto createCliente(ClienteDto clienteDto);
    ClienteDto updateCliente(UUID clienteId, ClienteDto clienteDto);
    void deleteCliente(UUID clienteId);
}
