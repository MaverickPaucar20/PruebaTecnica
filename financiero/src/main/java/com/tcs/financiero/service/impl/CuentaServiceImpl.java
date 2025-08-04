package com.tcs.financiero.service.impl;

import com.tcs.financiero.model.dto.CuentaDto;
import com.tcs.financiero.model.entity.Cuenta;
import com.tcs.financiero.model.mapper.CuentaMapper;
import com.tcs.financiero.repository.CuentaRepository;
import com.tcs.financiero.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Override
    public CuentaDto createCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDto);
        cuenta = cuentaRepository.save(cuenta);
        return cuentaMapper.toDto(cuenta);
    }

    @Override
    public CuentaDto getCuentaById(UUID cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return cuentaMapper.toDto(cuenta);
    }

    @Override
    public List<CuentaDto> getAllCuentas() {
        return cuentaRepository.findAll().stream()
                .map(cuentaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDto updateCuenta(UUID cuentaId, CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        // Actualiza los campos
        cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDto.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta.setClienteId(cuentaDto.getClienteId());
        cuenta = cuentaRepository.save(cuenta);
        return cuentaMapper.toDto(cuenta);
    }

    @Override
    public void deleteCuenta(UUID cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuentaRepository.delete(cuenta);
    }
}
