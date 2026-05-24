package com.soa.soafinanzas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.soa.soafinanzas.domain.repository.ReporteDiarioRepository;
import com.soa.soafinanzas.dto.response.ReporteResponse;
import com.soa.soafinanzas.exception.ResourceNotFoundException;
import com.soa.soafinanzas.mapper.ReporteMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteService {
    
    private final ReporteDiarioRepository reporteRepository;
    private final ReporteMapper reporteMapper;
    
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
}