package com.soa.soafinanzas.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CierreCajaRequest {
    
    @NotNull(message = "El ID del usuario es requerido")
    private UUID usuarioId;
    
    @NotNull(message = "El monto final real es requerido")
    @DecimalMin(value = "0.00", message = "El monto final real no puede ser negativo")
    private BigDecimal montoFinalReal;
}