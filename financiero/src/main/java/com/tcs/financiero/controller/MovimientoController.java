package com.tcs.financiero.controller;

import com.tcs.financiero.model.dto.ApiResponseDto;
import com.tcs.financiero.model.dto.MovimientoDto;
import com.tcs.financiero.model.dto.ReporteEstadoCuentaDto;
import com.tcs.financiero.service.MovimientoService;
import com.tcs.financiero.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<MovimientoDto>> createMovimiento(@RequestBody MovimientoDto movimientoDto) {
        try {
            MovimientoDto created = movimientoService.createMovimiento(movimientoDto);
            return new ResponseEntity<>(ApiResponseUtil.success("Movimiento registrado", created), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            if ("Saldo no disponible".equals(e.getMessage())) {
                return new ResponseEntity<>(ApiResponseUtil.error("Saldo no disponible"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(ApiResponseUtil.error("Error al registrar el movimiento"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponseDto<List<MovimientoDto>>> getAllMovimientos() {
        try {
            List<MovimientoDto> movimientos = movimientoService.getAllMovimientos();
            return ResponseEntity.ok(ApiResponseUtil.success("Consulta correcta", movimientos));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al obtener los movimientos"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponseDto<MovimientoDto>> getMovimientoById(@PathVariable("id") UUID movimientoId) {
        try {
            MovimientoDto movimiento = movimientoService.getMovimientoById(movimientoId);
            return ResponseEntity.ok(ApiResponseUtil.success("Consulta correcta", movimiento));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Movimiento no encontrado"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByCuentaId/{cuentaId}")
    public ResponseEntity<ApiResponseDto<List<MovimientoDto>>> getMovimientosByCuentaId(@PathVariable("cuentaId") UUID cuentaId) {
        try {
            List<MovimientoDto> movimientos = movimientoService.getMovimientosByCuentaId(cuentaId);
            return ResponseEntity.ok(ApiResponseUtil.success("Consulta correcta", movimientos));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al obtener movimientos"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponseDto<MovimientoDto>> updateMovimiento(@PathVariable("id") UUID movimientoId,
                                                                          @RequestBody MovimientoDto movimientoDto) {
        try {
            MovimientoDto updated = movimientoService.updateMovimiento(movimientoId, movimientoDto);
            return ResponseEntity.ok(ApiResponseUtil.success("Movimiento actualizado correctamente", updated));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al actualizar el movimiento"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteMovimiento(@PathVariable("id") UUID movimientoId) {
        try {
            movimientoService.deleteMovimiento(movimientoId);
            return ResponseEntity.ok(ApiResponseUtil.success("Movimiento eliminado correctamente", null));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al eliminar el movimiento"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estadoCuenta")
    public ResponseEntity<ApiResponseDto<ReporteEstadoCuentaDto>> reporteEstadoCuenta(
            @RequestParam("clienteId") UUID clienteId,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        try {
            ReporteEstadoCuentaDto reporte = movimientoService.generarEstadoCuenta(clienteId, fechaInicio, fechaFin);
            return ResponseEntity.ok(ApiResponseUtil.success("Reporte generado", reporte));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponseUtil.error("Error al generar el reporte"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
