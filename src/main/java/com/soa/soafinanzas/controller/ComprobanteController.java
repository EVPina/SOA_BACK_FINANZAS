package com.soa.soafinanzas.controller;

import com.soa.soafinanzas.dto.request.ComprobanteRequest;
import com.soa.soafinanzas.dto.response.ComprobanteResponse;
import com.soa.soafinanzas.service.ComprobanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comprobantes")
@RequiredArgsConstructor
@Tag(name = "Comprobantes", description = "API para gestión de comprobantes")
public class ComprobanteController {

    private final ComprobanteService comprobanteService;

    @PostMapping
    @Operation(summary = "Generar un nuevo comprobante")
    public ResponseEntity<ComprobanteResponse> generarComprobante(@Valid @RequestBody ComprobanteRequest request) {
        ComprobanteResponse response = comprobanteService.generarComprobante(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener comprobante por ID")
    public ResponseEntity<ComprobanteResponse> obtenerComprobante(@PathVariable UUID id) {
        return ResponseEntity.ok(comprobanteService.obtenerComprobante(id));
    }
}