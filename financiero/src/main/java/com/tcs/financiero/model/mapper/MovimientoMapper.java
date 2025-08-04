package com.tcs.financiero.model.mapper;

import com.tcs.financiero.model.entity.Movimiento;
import com.tcs.financiero.model.dto.MovimientoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    MovimientoDto toDto(Movimiento movimiento);
    Movimiento toEntity(MovimientoDto movimientoDto);
}