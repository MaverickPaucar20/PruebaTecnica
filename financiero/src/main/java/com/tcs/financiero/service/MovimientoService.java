package com.tcs.financiero.service;

import com.tcs.financiero.model.dto.MovimientoDto;
import com.tcs.financiero.model.dto.ReporteEstadoCuentaDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MovimientoService {
//    MovimientoDto createMovimiento(MovimientoDto movimientoDto);
    MovimientoDto getMovimientoById(UUID movimientoId);
    List<MovimientoDto> getAllMovimientos();
//    List<MovimientoDto> getMovimientosByCuentaId(UUID cuentaId);
    MovimientoDto updateMovimiento(UUID movimientoId, MovimientoDto movimientoDto);
    void deleteMovimiento(UUID movimientoId);

    MovimientoDto createMovimiento(MovimientoDto movimientoDto);
    List<MovimientoDto> getMovimientosByCuentaId(UUID cuentaId);
    ReporteEstadoCuentaDto generarEstadoCuenta(UUID clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
