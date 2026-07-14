package com.soa.soafinanzas.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class PagoResponse {
    
    @Schema(description = "ID del pago", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "ID del pedido asociado al pago", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID pedidoId;
    @Schema(description = "Método de pago", example = "EFECTIVO")
    private String metodoPago;
    @Schema(description = "Monto del pago", example = "1000.00")
    private BigDecimal monto;
    @Schema(description = "Referencia del pago", example = "REF001")
    private String referencia;
    @Schema(description = "Estado del pago", example = "PENDIENTE")
    private String estado;
    @Schema(description = "Fecha y hora de creación del pago", example = "2023-10-01T08:00:00")
    private LocalDateTime createdAt;
}