package com.soa.soafinanzas.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ReporteDiarioResponse {
    private LocalDate fecha;
    private BigDecimal totalIngresos;
    private BigDecimal totalEgresos;
    private BigDecimal flujoNeto;
    private BigDecimal ventasEfectivo;
    private BigDecimal ventasTarjeta;
    private BigDecimal ventasYapePlin;
    private BigDecimal ventasQr;
    private Integer totalFacturas;
    private Integer totalBoletas;
}