package com.tcs.financiero.service;

import com.tcs.financiero.model.dto.CuentaDto;

import java.util.List;
import java.util.UUID;

public interface CuentaService {
    CuentaDto createCuenta(CuentaDto cuentaDto);
    CuentaDto getCuentaById(UUID cuentaId);
    List<CuentaDto> getAllCuentas();
    CuentaDto updateCuenta(UUID cuentaId, CuentaDto cuentaDto);
    void deleteCuenta(UUID cuentaId);
}
