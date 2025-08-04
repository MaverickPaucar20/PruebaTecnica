package com.tcs.financiero.service.impl;

import com.tcs.financiero.model.dto.MovimientoDto;
import com.tcs.financiero.model.dto.ReporteEstadoCuentaDto;
import com.tcs.financiero.model.entity.Cuenta;
import com.tcs.financiero.model.entity.Movimiento;
import com.tcs.financiero.model.mapper.MovimientoMapper;
import com.tcs.financiero.repository.CuentaRepository;
import com.tcs.financiero.repository.MovimientoRepository;
import com.tcs.financiero.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientoMapper movimientoMapper;

    @Override
    public MovimientoDto getMovimientoById(UUID movimientoId) {
        Movimiento movimiento = movimientoRepository.findById(movimientoId)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        return movimientoMapper.toDto(movimiento);
    }

    @Override
    public List<MovimientoDto> getAllMovimientos() {
        return movimientoRepository.findAll().stream()
                .map(movimientoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoDto updateMovimiento(UUID movimientoId, MovimientoDto movimientoDto) {
        Movimiento movimiento = movimientoRepository.findById(movimientoId)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movimiento.setCuentaId(movimientoDto.getCuentaId());
        movimiento.setFecha(movimientoDto.getFecha());
        movimiento.setTipoMovimiento(movimientoDto.getTipoMovimiento());
        movimiento.setValor(movimientoDto.getValor());
        movimiento.setSaldo(movimientoDto.getSaldo());
        movimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.toDto(movimiento);
    }

    @Override
    public void deleteMovimiento(UUID movimientoId) {
        Movimiento movimiento = movimientoRepository.findById(movimientoId)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movimientoRepository.delete(movimiento);
    }


    @Override
    public MovimientoDto createMovimiento(MovimientoDto movimientoDto) {
        Cuenta cuenta = cuentaRepository.findById(movimientoDto.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        double nuevoSaldo = cuenta.getSaldoInicial() + movimientoDto.getValor();
        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);
        Movimiento movimiento = movimientoMapper.toEntity(movimientoDto);
        movimiento.setSaldo(nuevoSaldo);
        movimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.toDto(movimiento);
    }

    @Override
    public List<MovimientoDto> getMovimientosByCuentaId(UUID cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId).stream()
                .map(movimientoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReporteEstadoCuentaDto generarEstadoCuenta(UUID clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        ReporteEstadoCuentaDto reporte = new ReporteEstadoCuentaDto();
        reporte.setClienteId(clienteId);
        reporte.setCuentas(cuentas.stream().map(cuenta -> {
            ReporteEstadoCuentaDto.CuentaReporte cuentaReporte = new ReporteEstadoCuentaDto.CuentaReporte();
            cuentaReporte.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaReporte.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaReporte.setMovimientos(
                    movimientoRepository.findByCuentaIdAndFechaBetween(cuenta.getCuentaId(), fechaInicio, fechaFin)
                            .stream().map(movimientoMapper::toDto).collect(Collectors.toList())
            );
            return cuentaReporte;
        }).collect(Collectors.toList()));
        return reporte;
    }
}
