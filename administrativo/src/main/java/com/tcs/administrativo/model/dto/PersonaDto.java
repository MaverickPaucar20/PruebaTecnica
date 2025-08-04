package com.tcs.administrativo.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PersonaDto {
    private UUID personaId;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
