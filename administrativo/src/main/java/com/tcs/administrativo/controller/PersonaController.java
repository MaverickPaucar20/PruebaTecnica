package com.tcs.administrativo.controller;

import com.tcs.administrativo.model.dto.ApiResponseDto;
import com.tcs.administrativo.model.dto.PersonaDto;
import com.tcs.administrativo.model.entity.Persona;
import com.tcs.administrativo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<PersonaDto>>> getAllPersonas(){
        try{
            List<PersonaDto> listPersonas = personaService.getAllPersonas();
            ApiResponseDto<List<PersonaDto>> response = new ApiResponseDto<>(
                    "1",
                    "Consulta API Correcta",
                    listPersonas
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            ApiResponseDto<List<PersonaDto>> response = new ApiResponseDto<>(
                    "0",
                    "Error al obtener todas las personas",
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
