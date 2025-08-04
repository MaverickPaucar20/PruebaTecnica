package com.tcs.administrativo.model.mapper;

import com.tcs.administrativo.model.entity.Cliente;
import com.tcs.administrativo.model.dto.ClienteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDto toDto(Cliente cliente);
    Cliente toEntity(ClienteDto clienteDto);
}