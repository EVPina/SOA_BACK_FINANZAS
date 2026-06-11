package com.soa.soafinanzas.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ReporteMensualResponse {
    private int año;
    private int mes;
    private BigDecimal totalVentas;
    private BigDecimal totalGastos;
    private BigDecimal utilidad;
    private List<ReporteDiarioResponse> reportesDiarios;
}