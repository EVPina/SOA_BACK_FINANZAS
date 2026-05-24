package com.soa.soafinanzas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponse {
    private UUID id;
    private LocalDate fecha;
    private BigDecimal totalIngresos;
    private BigDecimal totalEgresos;
    private BigDecimal flujoNeto;
    private DesgloseVentas desgloseVentas;
    private Documentos documentos;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DesgloseVentas {
        private BigDecimal efectivo;
        private BigDecimal tarjeta;
        private BigDecimal yapePlin;
        private BigDecimal qr;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Documentos {
        private Integer facturas;
        private Integer boletas;
    }
}