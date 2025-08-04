package com.tcs.administrativo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona", schema = "core")
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "persona_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID personaId;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 1)
    private String genero;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false, length = 50)
    private String identificacion;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false, length = 30)
    private String telefono;
}
