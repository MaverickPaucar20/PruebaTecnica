package com.tcs.financiero.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movimiento", schema = "core")
public class Movimiento {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "movimiento_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID movimientoId;

    @Column(name = "cuenta_id", nullable = false, columnDefinition = "uuid")
    private UUID cuentaId;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(name = "tipo_movimiento", nullable = false, length = 30)
    private String tipoMovimiento;

    @Column(nullable = false, precision = 15, scale = 2)
    private Double valor;

    @Column(nullable = false, precision = 15, scale = 2)
    private Double saldo;
}