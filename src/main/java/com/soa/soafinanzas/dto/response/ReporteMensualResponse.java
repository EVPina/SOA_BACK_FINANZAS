package com.soa.soafinanzas.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class ReporteMensualResponse {
    @Schema(description = "Año del reporte", example = "2023")
    private int año;
    @Schema(description = "Mes del reporte", example = "10")
    private int mes;
    @Schema(description = "Total de ventas", example = "1000.00")
    private BigDecimal totalVentas;
    @Schema(description = "Total de gastos", example = "500.00")
    private BigDecimal totalGastos;
    @Schema(description = "Utilidad", example = "500.00")
    private BigDecimal utilidad;
    @Schema(description = "Lista de reportes diarios del mes")
    private List<ReporteDiarioResponse> reportesDiarios;
}