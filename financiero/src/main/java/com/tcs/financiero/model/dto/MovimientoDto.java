package com.tcs.financiero.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MovimientoDto {
    private UUID movimientoId;
    private UUID cuentaId;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
}
