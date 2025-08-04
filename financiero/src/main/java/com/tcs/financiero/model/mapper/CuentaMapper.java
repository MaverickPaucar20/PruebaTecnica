package com.tcs.financiero.model.mapper;

import com.tcs.financiero.model.entity.Cuenta;
import com.tcs.financiero.model.dto.CuentaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    CuentaDto toDto(Cuenta cuenta);
    Cuenta toEntity(CuentaDto cuentaDto);
}