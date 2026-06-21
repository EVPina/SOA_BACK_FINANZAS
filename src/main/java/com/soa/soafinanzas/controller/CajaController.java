package com.soa.soafinanzas.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<CajaResponse> abrirCaja(@Valid @RequestBody AperturaCajaRequest request) {
        CajaResponse response = cajaService.abrirCaja(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/cierre/{cajaId}")
    @Operation(summary = "Cerrar una caja")
    public ResponseEntity<CajaResponse> cerrarCaja(
            @PathVariable UUID cajaId,
            @Valid @RequestBody CierreCajaRequest request) {
        CajaResponse response = cajaService.cerrarCaja(cajaId, request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/actual")
    @Operation(summary = "Obtener caja actual abierta")
    public ResponseEntity<CajaResponse> getCajaActual() {
        CajaResponse response = cajaService.getCajaActual();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{cajaId}/saldo")
    @Operation(summary = "Obtener saldo actual de una caja")
    public ResponseEntity<SaldoResponse> getSaldoActual(@PathVariable UUID cajaId) {
        SaldoResponse response = cajaService.getSaldoActual(cajaId);
        return ResponseEntity.ok(response);
    }
}