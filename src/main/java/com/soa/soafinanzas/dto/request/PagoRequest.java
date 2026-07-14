package com.soa.soafinanzas.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class PagoRequest {

    @Schema(description = "ID del pedido asociado al pago", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "El ID del pedido es obligatorio")
    private UUID pedidoId;

    @Schema(description = "Método de pago", example = "EFECTIVO")
    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    @Schema(description = "Monto del pago", example = "150.75")
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    @Schema(description = "Referencia del pago", example = "REF-001")
    private String referencia;
}