package com.tcs.financiero.model.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CuentaDto {
    private UUID cuentaId;
    private UUID clienteId;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
}