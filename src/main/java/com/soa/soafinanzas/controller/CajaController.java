package com.soa.soafinanzas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soa.soafinanzas.dto.request.AperturaCajaRequest;
import com.soa.soafinanzas.dto.request.CierreCajaRequest;
import com.soa.soafinanzas.dto.response.CajaResponse;
import com.soa.soafinanzas.dto.response.SaldoResponse;
import com.soa.soafinanzas.service.CajaService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cajas")
@RequiredArgsConstructor
@Tag(name = "Cajas", description = "API para gestión de cajas")
public class CajaController {
    
    private final CajaService cajaService;
    
    @PostMapping("/apertura")
    @Operation(summary = "Abrir una nueva caja") 
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Caja abierta exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontró la caja"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<CajaResponse> abrirCaja(@Valid @RequestBody AperturaCajaRequest request) {
        CajaResponse response = cajaService.abrirCaja(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/cierre/{cajaId}")
    @Operation(summary = "Cerrar una caja")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Caja cerrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontró la caja"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<CajaResponse> cerrarCaja(
            @PathVariable UUID cajaId,
            @Valid @RequestBody CierreCajaRequest request) {
        CajaResponse response = cajaService.cerrarCaja(cajaId, request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/actual")
    @Operation(summary = "Obtener caja actual abierta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Caja actual encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontró la caja actual"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<CajaResponse> getCajaActual() {
        CajaResponse response = cajaService.getCajaActual();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{cajaId}/saldo")
    @Operation(summary = "Obtener saldo actual de una caja")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saldo encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontró la caja"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<SaldoResponse> getSaldoActual(@PathVariable UUID cajaId) {
        SaldoResponse response = cajaService.getSaldoActual(cajaId);
        return ResponseEntity.ok(response);
    }
}