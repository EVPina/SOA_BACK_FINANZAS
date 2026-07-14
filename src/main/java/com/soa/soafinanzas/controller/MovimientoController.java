package com.soa.soafinanzas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.soa.soafinanzas.dto.request.MovimientoRequest;
import com.soa.soafinanzas.dto.response.MovimientoResponse;
import com.soa.soafinanzas.service.MovimientoService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movimientos")
@RequiredArgsConstructor
@Tag(name = "Movimientos", description = "API para gestión de movimientos financieros")
public class MovimientoController {
    
    private final MovimientoService movimientoService;
    
    @PostMapping("/ingreso")
    @Operation(summary = "Registrar un ingreso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ingreso registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MovimientoResponse> registrarIngreso(@Valid @RequestBody MovimientoRequest request) {
        MovimientoResponse response = movimientoService.registrarIngreso(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PostMapping("/egreso")
    @Operation(summary = "Registrar un egreso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Egreso registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MovimientoResponse> registrarEgreso(@Valid @RequestBody MovimientoRequest request) {
        MovimientoResponse response = movimientoService.registrarEgreso(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/caja/{cajaId}")
    @Operation(summary = "Obtener movimientos por caja")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movimientos encontrados exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontró la caja"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<MovimientoResponse>> getMovimientosByCaja(@PathVariable UUID cajaId) {
        List<MovimientoResponse> movimientos = movimientoService.getMovimientosByCaja(cajaId);
        return ResponseEntity.ok(movimientos);
    }
    
    @GetMapping("/fecha/{fecha}")
    @Operation(summary = "Obtener movimientos por fecha")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movimientos encontrados exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontró la fecha"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<MovimientoResponse>> getMovimientosByFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<MovimientoResponse> movimientos = movimientoService.getMovimientosByFecha(fecha);
        return ResponseEntity.ok(movimientos);
    }
}