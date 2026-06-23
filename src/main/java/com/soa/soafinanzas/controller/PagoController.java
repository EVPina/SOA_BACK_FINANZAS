package com.soa.soafinanzas.controller;

import com.soa.soafinanzas.dto.request.PagoRequest;
import com.soa.soafinanzas.dto.response.PagoResponse;
import com.soa.soafinanzas.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
@Tag(name = "Pagos", description = "API para gestión de pagos")
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    @Operation(summary = "Registrar un nuevo pago")
    public ResponseEntity<PagoResponse> registrarPago(@Valid @RequestBody PagoRequest request) {
        PagoResponse response = pagoService.registrarPago(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pago por ID")
    public ResponseEntity<PagoResponse> obtenerPago(@PathVariable UUID id) {
        return ResponseEntity.ok(pagoService.obtenerPago(id));
    }
}