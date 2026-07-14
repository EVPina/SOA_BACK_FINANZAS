package com.soa.soafinanzas.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
public class CierreCajaRequest {
    
    @Schema(description = "ID del usuario que realiza el cierre de caja", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "El ID del usuario es requerido")
    private UUID usuarioId;
    
    @Schema(description = "Monto final real de la caja", example = "1500.00")
    @NotNull(message = "El monto final real es requerido")
    @DecimalMin(value = "0.00", message = "El monto final real no puede ser negativo")
    private BigDecimal montoFinalReal;
}