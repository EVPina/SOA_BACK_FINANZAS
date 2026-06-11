package com.soa.soafinanzas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.soa.soafinanzas.domain.model.MovimientoFinanciero;
import com.soa.soafinanzas.domain.repository.MovimientoFinancieroRepository;
import com.soa.soafinanzas.domain.repository.ReporteDiarioRepository;
import com.soa.soafinanzas.dto.response.ProductoRankingResponse;
import com.soa.soafinanzas.dto.response.ReporteDiarioResponse;
import com.soa.soafinanzas.dto.response.ReporteMensualResponse;
import com.soa.soafinanzas.dto.response.ReporteResponse;
import com.soa.soafinanzas.exception.ResourceNotFoundException;
import com.soa.soafinanzas.mapper.ReporteMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteService {
    
    private final ReporteDiarioRepository reporteRepository;
    private final ReporteMapper reporteMapper;
     private final MovimientoFinancieroRepository movimientoRepository;

     public ReporteResponse getReporteByFecha(LocalDate fecha) {
        return reporteRepository.findByFecha(fecha)
                .map(reporteMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("No hay reporte para la fecha: " + fecha));
    }
    
    public List<ReporteResponse> getReportesByRangoFechas(LocalDate startDate, LocalDate endDate) {
        return reporteRepository.findByFechaBetween(startDate, endDate)
                .stream()
                .map(reporteMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<ReporteResponse> getUltimos7Reportes() {
        return reporteRepository.findLast7DaysReports()
                .stream()
                .map(reporteMapper::toResponse)
                .collect(Collectors.toList());
    }

     // ✅ Obtener reporte diario
    public ReporteDiarioResponse obtenerReporteDiario(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);
        
        List<MovimientoFinanciero> movimientos = movimientoRepository.findByFecha(fecha);
        
        BigDecimal totalIngresos = movimientos.stream()
                .filter(m -> "INGRESO".equals(m.getTipo()))
                .map(MovimientoFinanciero::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalEgresos = movimientos.stream()
                .filter(m -> "EGRESO".equals(m.getTipo()))
                .map(MovimientoFinanciero::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal ventasEfectivo = movimientos.stream()
                .filter(m -> "INGRESO".equals(m.getTipo()) && "EFECTIVO".equals(m.getMetodoPago()))
                .map(MovimientoFinanciero::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal ventasTarjeta = movimientos.stream()
                .filter(m -> "INGRESO".equals(m.getTipo()) && "TARJETA".equals(m.getMetodoPago()))
                .map(MovimientoFinanciero::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal ventasYapePlin = movimientos.stream()
                .filter(m -> "INGRESO".equals(m.getTipo()) && ("YAPE".equals(m.getMetodoPago()) || "PLIN".equals(m.getMetodoPago())))
                .map(MovimientoFinanciero::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal ventasQr = movimientos.stream()
                .filter(m -> "INGRESO".equals(m.getTipo()) && "QR".equals(m.getMetodoPago()))
                .map(MovimientoFinanciero::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return ReporteDiarioResponse.builder()
                .fecha(fecha)
                .totalIngresos(totalIngresos)
                .totalEgresos(totalEgresos)
                .flujoNeto(totalIngresos.subtract(totalEgresos))
                .ventasEfectivo(ventasEfectivo)
                .ventasTarjeta(ventasTarjeta)
                .ventasYapePlin(ventasYapePlin)
                .ventasQr(ventasQr)
                .totalFacturas(0)  // Pendiente implementar
                .totalBoletas(0)   // Pendiente implementar
                .build();
    }

    // ✅ Obtener reporte mensual
    public ReporteMensualResponse obtenerReporteMensual(int año, int mes) {
        LocalDate inicio = LocalDate.of(año, mes, 1);
        LocalDate fin = inicio.withDayOfMonth(inicio.lengthOfMonth());
        
        List<ReporteDiarioResponse> reportesDiarios = new java.util.ArrayList<>();
        
        for (LocalDate fecha = inicio; !fecha.isAfter(fin); fecha = fecha.plusDays(1)) {
            reportesDiarios.add(obtenerReporteDiario(fecha));
        }
        
        BigDecimal totalVentas = reportesDiarios.stream()
                .map(ReporteDiarioResponse::getTotalIngresos)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalGastos = reportesDiarios.stream()
                .map(ReporteDiarioResponse::getTotalEgresos)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return ReporteMensualResponse.builder()
                .año(año)
                .mes(mes)
                .totalVentas(totalVentas)
                .totalGastos(totalGastos)
                .utilidad(totalVentas.subtract(totalGastos))
                .reportesDiarios(reportesDiarios)
                .build();
    }

    // ✅ Obtener producto más vendido (simplificado - pendiente integrar con ventas)
    public ProductoRankingResponse obtenerProductoMasVendido(LocalDate fechaInicio, LocalDate fechaFin) {
        // Nota: Esto requiere integrar con sb-ventas
        // Por ahora, retorna un placeholder
        return ProductoRankingResponse.builder()
                .productoId(null)
                .nombreProducto("Pendiente de implementar")
                .cantidadVendida(0L)
                .totalVentas(BigDecimal.ZERO)
                .build();
    }
}