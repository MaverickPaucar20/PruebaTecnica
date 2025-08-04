package com.tcs.financiero.repository;

import com.tcs.financiero.model.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, UUID> {

    List<Cuenta> findByClienteId(UUID clienteId);

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

}
