package com.soa.soafinanzas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CajaResponse {

    @Schema(description = "ID de la caja", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Fecha de la caja", example = "2023-10-01")
    private LocalDate fecha;
    @Schema(description = "ID del usuario que realiza la apertura de caja", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID usuarioAperturaId;
    @Schema(description = "ID del usuario que realiza el cierre de caja", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID usuarioCierreId;
    @Schema(description = "Monto inicial de la caja", example = "1000.00")
    private BigDecimal montoInicial;
    @Schema(description = "Monto final esperado de la caja", example = "1500.00")
    private BigDecimal montoFinalEsperado;
    @Schema(description = "Monto final real de la caja", example = "1500.00")
    private BigDecimal montoFinalReal;
    @Schema(description = "Diferencia en la caja", example = "0.00")
    private BigDecimal diferencia;
    @Schema(description = "Estado de la caja", example = "ABIERTA")
    private String estado;
    @Schema(description = "Fecha y hora de apertura de la caja", example = "2023-10-01T08:00:00")
    private LocalDateTime aperturaEn;
    @Schema(description = "Fecha y hora de cierre de la caja", example = "2023-10-01T18:00:00")
    private LocalDateTime cierreEn;
    @Schema(description = "Fecha y hora de creación de la caja", example = "2023-10-01T08:00:00")
    private LocalDateTime createdAt;
}