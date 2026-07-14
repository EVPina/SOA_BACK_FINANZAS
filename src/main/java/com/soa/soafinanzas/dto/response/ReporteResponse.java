package com.soa.soafinanzas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponse {
    @Schema(description = "ID del reporte", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Fecha del reporte", example = "2023-10-01")
    private LocalDate fecha;
    @Schema(description = "Total de ingresos", example = "1000.00")
    private BigDecimal totalIngresos;
    @Schema(description = "Total de egresos", example = "500.00")
    private BigDecimal totalEgresos;
    @Schema(description = "Flujo neto", example = "500.00") 
    private BigDecimal flujoNeto;
    @Schema(description = "Desglose de ventas", example = "Desglose de ventas")
    private DesgloseVentas desgloseVentas;
    @Schema(description = "Documentos", example = "Documentos")
    private Documentos documentos;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DesgloseVentas {
        @Schema(description = "Ventas en efectivo", example = "1000.00")
        private BigDecimal efectivo;
        @Schema(description = "Ventas con tarjeta", example = "1000.00")
        private BigDecimal tarjeta;
        @Schema(description = "Ventas con Yape/Plin", example = "1000.00")
        private BigDecimal yapePlin;
        @Schema(description = "Ventas con QR", example = "1000.00")
        private BigDecimal qr;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Documentos {
        @Schema(description = "Total de facturas", example = "10")
        private Integer facturas;
        @Schema(description = "Total de boletas", example = "10")
        private Integer boletas;
    }
}