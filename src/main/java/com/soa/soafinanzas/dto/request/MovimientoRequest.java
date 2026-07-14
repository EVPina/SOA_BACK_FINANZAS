package com.soa.soafinanzas.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoRequest {
    
    @Schema(description = "ID del usuario que realiza el movimiento", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "El ID del usuario es requerido")
    @NotBlank(message = "El tipo es requerido")
    private String tipo;
    
    @Schema(description = "Concepto del movimiento", example = "Pago de servicios")
    @NotNull(message = "El concepto es requerido")
    @NotBlank(message = "El concepto es requerido")
    @Size(max = 255, message = "El concepto no puede exceder 255 caracteres")
    private String concepto;
    
    @Schema(description = "Categoría del movimiento", example = "Servicios")
    @NotNull(message = "La categoría es requerida")
    @NotBlank(message = "La categoría es requerida")
    private String categoria;
    
    @Schema(description = "Método de pago del movimiento", example = "EFECTIVO")
    @NotNull(message = "El método de pago es requerido")
    @NotBlank(message = "El método de pago es requerido")
    private String metodoPago;
    
    @Schema(description = "Monto del movimiento", example = "150.75")
    @NotNull(message = "El monto es requerido")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;
    
    @Schema(description = "ID de la referencia del movimiento", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID referenciaId;
}