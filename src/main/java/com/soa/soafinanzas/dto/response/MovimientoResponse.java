package com.soa.soafinanzas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoResponse {
    private UUID id;
    private UUID cajaId;
    private String tipo;
    private String concepto;
    private String categoria;
    private String metodoPago;
    private BigDecimal monto;
    private UUID referenciaId;
    private LocalDateTime createdAt;
}