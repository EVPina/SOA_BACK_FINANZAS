package com.soa.soafinanzas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoResponse {
    
    @Schema(description = "ID del movimiento", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "ID de la caja", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID cajaId;
    @Schema(description = "Tipo de movimiento", example = "INGRESO")
    private String tipo;
    @Schema(description = "Concepto del movimiento", example = "Venta de productos")
    private String concepto;
    @Schema(description = "Categoría del movimiento", example = "Ventas")   
    private String categoria;
    @Schema(description = "Método de pago", example = "EFECTIVO")
    private String metodoPago;
    @Schema(description = "Monto del movimiento", example = "1000.00")
    private BigDecimal monto;
    @Schema(description = "ID de la referencia", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID referenciaId;
    @Schema(description = "Fecha y hora de creación del movimiento", example = "2023-10-01T08:00:00")
    private LocalDateTime createdAt;
}