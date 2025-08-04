package com.tcs.administrativo.controller;

import com.tcs.administrativo.model.dto.ApiResponseDto;
import com.tcs.administrativo.model.dto.ClienteDto;
import com.tcs.administrativo.service.ClienteService;
import com.tcs.administrativo.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponseDto<List<ClienteDto>>> getAllClientes(){
        try{
            List<ClienteDto> listClientes = clienteService.getAllClientes();
            ApiResponseDto<List<ClienteDto>> response = ApiResponseUtil.success(
                    "Consulta API Correcta",
                    listClientes
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            ApiResponseDto<List<ClienteDto>> response = ApiResponseUtil.error("Error al obtener todos los clientes");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponseDto<ClienteDto>> getClienteById(
            @PathVariable("id") UUID clienteId
    ){
        try{
            ClienteDto cliente = clienteService.getClienteById(clienteId);
            ApiResponseDto<ClienteDto> response = ApiResponseUtil.success(
                    "Consulta API Correcta",
                    cliente
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            ApiResponseDto<ClienteDto> response = ApiResponseUtil.error("Error al obtener cliente por id");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByIdentificacion/{identificacion}")
    public ResponseEntity<ApiResponseDto<ClienteDto>> getClienteByIdentificacion(
            @PathVariable("identificacion") String identificacion
    ){
        try{
            ClienteDto cliente = clienteService.getClienteByIdentificacion(identificacion);
            ApiResponseDto<ClienteDto> response = ApiResponseUtil.success(
                    "Consulta API Correcta",
                    cliente
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            ApiResponseDto<ClienteDto> response = ApiResponseUtil.error("Error al obtener cliente por id");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDto<ClienteDto>> createCliente(@RequestBody ClienteDto clienteDto) {
        try {
            ClienteDto createdCliente = clienteService.createCliente(clienteDto);
            ApiResponseDto<ClienteDto> response = ApiResponseUtil.success(
                    "Cliente creado correctamente",
                    createdCliente
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponseDto<ClienteDto> response = ApiResponseUtil.error("Error al crear el cliente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ClienteDto>> updateCliente(
            @PathVariable("id") UUID clienteId,
            @RequestBody ClienteDto clienteDto
    ) {
        try {
            ClienteDto updatedCliente = clienteService.updateCliente(clienteId, clienteDto);
            ApiResponseDto<ClienteDto> response = ApiResponseUtil.success(
                    "Cliente actualizado correctamente",
                    updatedCliente
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponseDto<ClienteDto> response = ApiResponseUtil.error("Error al actualizar el cliente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteCliente(@PathVariable("id") UUID clienteId) {
        try {
            clienteService.deleteCliente(clienteId);
            ApiResponseDto<Void> response = ApiResponseUtil.success("Cliente eliminado correctamente", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponseDto<Void> response = ApiResponseUtil.error("Error al eliminar el cliente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
