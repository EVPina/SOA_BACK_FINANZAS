package com.soa.soafinanzas.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class ReporteDiarioResponse {
    
    @Schema(description = "Fecha del reporte", example = "2023-10-01")
    private LocalDate fecha;
    @Schema(description = "Total de ingresos", example = "1000.00")
    private BigDecimal totalIngresos;
    @Schema(description = "Total de egresos", example = "500.00")
    private BigDecimal totalEgresos;
    @Schema(description = "Flujo neto", example = "500.00")
    private BigDecimal flujoNeto;
    @Schema(description = "Ventas en efectivo", example = "1000.00")
    private BigDecimal ventasEfectivo;
    @Schema(description = "Ventas con tarjeta", example = "1000.00")
    private BigDecimal ventasTarjeta;
    @Schema(description = "Ventas con Yape/Plin", example = "1000.00")
    private BigDecimal ventasYapePlin;
    @Schema(description = "Ventas con QR", example = "1000.00")
    private BigDecimal ventasQr;
    @Schema(description = "Total de facturas", example = "10")
    private Integer totalFacturas;
    @Schema(description = "Total de boletas", example = "10")
    private Integer totalBoletas;
}