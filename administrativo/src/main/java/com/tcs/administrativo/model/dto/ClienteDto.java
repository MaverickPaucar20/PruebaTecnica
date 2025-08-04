package com.tcs.administrativo.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClienteDto {
    private UUID clienteId;
    private String contrasena;
    private Boolean estado;
    private PersonaDto persona;
}
