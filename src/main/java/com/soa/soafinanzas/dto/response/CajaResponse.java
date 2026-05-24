package com.soa.soafinanzas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CajaResponse {
    private UUID id;
    private LocalDate fecha;
    private UUID usuarioAperturaId;
    private UUID usuarioCierreId;
    private BigDecimal montoInicial;
    private BigDecimal montoFinalEsperado;
    private BigDecimal montoFinalReal;
    private BigDecimal diferencia;
    private String estado;
    private LocalDateTime aperturaEn;
    private LocalDateTime cierreEn;
    private LocalDateTime createdAt;
}