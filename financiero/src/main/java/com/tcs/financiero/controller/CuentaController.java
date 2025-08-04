package com.tcs.financiero.controller;

import com.tcs.financiero.model.dto.ApiResponseDto;
import com.tcs.financiero.model.dto.CuentaDto;
import com.tcs.financiero.service.CuentaService;
import com.tcs.financiero.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<CuentaDto>> createCuenta(@RequestBody CuentaDto cuentaDto) {
        try {
            CuentaDto created = cuentaService.createCuenta(cuentaDto);
            return new ResponseEntity<>(ApiResponseUtil.success("Cuenta creada correctamente", created), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al crear la cuenta"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponseDto<List<CuentaDto>>> getAllCuentas() {
        try {
            List<CuentaDto> cuentas = cuentaService.getAllCuentas();
            return ResponseEntity.ok(ApiResponseUtil.success("Consulta correcta", cuentas));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al obtener las cuentas"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponseDto<CuentaDto>> getCuentaById(@PathVariable("id") UUID cuentaId) {
        try {
            CuentaDto cuenta = cuentaService.getCuentaById(cuentaId);
            return ResponseEntity.ok(ApiResponseUtil.success("Consulta correcta", cuenta));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Cuenta no encontrada"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByClienteId/{clienteId}")
    public ResponseEntity<ApiResponseDto<List<CuentaDto>>> getCuentasByClienteId(@PathVariable("clienteId") UUID clienteId) {
        try {
            List<CuentaDto> cuentas = cuentaService.getAllCuentas().stream()
                    .filter(c -> c.getClienteId().equals(clienteId))
                    .toList();
            return ResponseEntity.ok(ApiResponseUtil.success("Consulta correcta", cuentas));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al obtener las cuentas del cliente"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponseDto<CuentaDto>> updateCuenta(@PathVariable("id") UUID cuentaId,
                                                                  @RequestBody CuentaDto cuentaDto) {
        try {
            CuentaDto updated = cuentaService.updateCuenta(cuentaId, cuentaDto);
            return ResponseEntity.ok(ApiResponseUtil.success("Cuenta actualizada correctamente", updated));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al actualizar la cuenta"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteCuenta(@PathVariable("id") UUID cuentaId) {
        try {
            cuentaService.deleteCuenta(cuentaId);
            return ResponseEntity.ok(ApiResponseUtil.success("Cuenta eliminada correctamente", null));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al eliminar la cuenta"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
