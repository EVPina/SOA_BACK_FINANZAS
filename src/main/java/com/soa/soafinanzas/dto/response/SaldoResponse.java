package com.soa.soafinanzas.dto.response;

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
public class SaldoResponse {
    @Schema(description = "ID de la caja", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID cajaId;
    @Schema(description = "Saldo actual", example = "1000.00")
    private BigDecimal saldoActual;
    @Schema(description = "Monto inicial", example = "1000.00")
    private BigDecimal montoInicial;
    @Schema(description = "Total de ingresos", example = "1000.00")
    private BigDecimal totalIngresos;
    @Schema(description = "Total de egresos", example = "500.00")
    private BigDecimal totalEgresos;
}