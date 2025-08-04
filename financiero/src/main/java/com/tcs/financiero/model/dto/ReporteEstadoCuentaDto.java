package com.tcs.financiero.model.dto;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class ReporteEstadoCuentaDto {
    private UUID clienteId;
    private List<CuentaReporte> cuentas;

    @Data
    public static class CuentaReporte {
        private String numeroCuenta;
        private String tipoCuenta;
        private List<MovimientoDto> movimientos;
    }
}
