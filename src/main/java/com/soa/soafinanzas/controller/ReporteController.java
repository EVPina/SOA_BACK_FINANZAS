package com.soa.soafinanzas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soa.soafinanzas.dto.response.ProductoRankingResponse;
import com.soa.soafinanzas.dto.response.ReporteDiarioResponse;
import com.soa.soafinanzas.dto.response.ReporteMensualResponse;
import com.soa.soafinanzas.dto.response.ReporteResponse;
import com.soa.soafinanzas.service.ReporteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/reportes")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "API para consulta de reportes diarios")
public class ReporteController {
    
    private final ReporteService reporteService;
    
    @GetMapping("/{fecha}")
    @Operation(summary = "Obtener reporte por fecha específica")
    public ResponseEntity<ReporteResponse> getReporteByFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        ReporteResponse response = reporteService.getReporteByFecha(fecha);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/rango")
    @Operation(summary = "Obtener reportes por rango de fechas")
    public ResponseEntity<List<ReporteResponse>> getReportesByRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ReporteResponse> reportes = reporteService.getReportesByRangoFechas(startDate, endDate);
        return ResponseEntity.ok(reportes);
    }
    
    @GetMapping("/ultimos-7")
    @Operation(summary = "Obtener últimos 7 reportes")
    public ResponseEntity<List<ReporteResponse>> getUltimos7Reportes() {
        List<ReporteResponse> reportes = reporteService.getUltimos7Reportes();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/ventas/diario")
    public ResponseEntity<ReporteDiarioResponse> reporteVentasDiario(@RequestParam LocalDate fecha) {
        return ResponseEntity.ok(reporteService.obtenerReporteDiario(fecha));
    }
    
    @GetMapping("/ventas/mensual")
    public ResponseEntity<ReporteMensualResponse> reporteVentasMensual(@RequestParam int año, @RequestParam int mes) {
        return ResponseEntity.ok(reporteService.obtenerReporteMensual(año, mes));
    }
    
    @GetMapping("/producto-mas-vendido")
    public ResponseEntity<ProductoRankingResponse> productoMasVendido(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {
        return ResponseEntity.ok(reporteService.obtenerProductoMasVendido(fechaInicio, fechaFin));
    }
}