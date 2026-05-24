package com.soa.soafinanzas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soa.soafinanzas.domain.model.Caja;
import com.soa.soafinanzas.domain.repository.CajaRepository;
import com.soa.soafinanzas.domain.repository.MovimientoFinancieroRepository;
import com.soa.soafinanzas.dto.request.AperturaCajaRequest;
import com.soa.soafinanzas.dto.request.CierreCajaRequest;
import com.soa.soafinanzas.dto.response.CajaResponse;
import com.soa.soafinanzas.dto.response.SaldoResponse;
import com.soa.soafinanzas.exception.BusinessException;
import com.soa.soafinanzas.exception.ResourceNotFoundException;
import com.soa.soafinanzas.mapper.CajaMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CajaService {
    
    private final CajaRepository cajaRepository;
    private final MovimientoFinancieroRepository movimientoRepository;
    private final CajaMapper cajaMapper;
    
    @Transactional
    public CajaResponse abrirCaja(AperturaCajaRequest request) {
        if (cajaRepository.existsByEstado("ABIERTA")) {
            throw new BusinessException("Ya existe una caja abierta");
        }
        
        if (request.getMontoInicial().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("El monto inicial no puede ser negativo");
        }
        
        Caja caja = cajaMapper.toEntity(request);
        caja.setUsuarioAperturaId(request.getUsuarioId());
        caja.setMontoInicial(request.getMontoInicial());
        caja.setEstado("ABIERTA");
        caja.setAperturaEn(LocalDateTime.now());
        
        Caja savedCaja = cajaRepository.save(caja);
        log.info("Caja abierta: {}", savedCaja.getId());
        
        return cajaMapper.toResponse(savedCaja);
    }
    
    @Transactional
    public CajaResponse cerrarCaja(UUID cajaId, CierreCajaRequest request) {
        Caja caja = cajaRepository.findById(cajaId)
                .orElseThrow(() -> new ResourceNotFoundException("Caja no encontrada"));
        
        if (!"ABIERTA".equals(caja.getEstado())) {
            throw new BusinessException("La caja ya está cerrada");
        }
        
        BigDecimal totalIngresos = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "INGRESO");
        BigDecimal totalEgresos = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "EGRESO");
        BigDecimal montoFinalEsperado = caja.getMontoInicial().add(totalIngresos).subtract(totalEgresos);
        BigDecimal diferencia = request.getMontoFinalReal().subtract(montoFinalEsperado);
        
        caja.setUsuarioCierreId(request.getUsuarioId());
        caja.setMontoFinalEsperado(montoFinalEsperado);
        caja.setMontoFinalReal(request.getMontoFinalReal());
        caja.setDiferencia(diferencia);
        caja.setEstado("CERRADA");
        caja.setCierreEn(LocalDateTime.now());
        
        Caja closedCaja = cajaRepository.save(caja);
        log.info("Caja cerrada: {}", cajaId);
        
        return cajaMapper.toResponse(closedCaja);
    }
    
    // Método que retorna la entidad Caja (para uso interno)
    public Caja getCajaActualEntity() {
        return cajaRepository.findCajaAbierta()
                .orElseThrow(() -> new ResourceNotFoundException("No hay caja abierta"));
    }
    
    // Método que retorna el DTO CajaResponse (para la API)
    public CajaResponse getCajaActual() {
        Caja caja = getCajaActualEntity();
        return cajaMapper.toResponse(caja);
    }
    
    public SaldoResponse getSaldoActual(UUID cajaId) {
        Caja caja = cajaRepository.findById(cajaId)
                .orElseThrow(() -> new ResourceNotFoundException("Caja no encontrada"));
        
        BigDecimal totalIngresos = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "INGRESO");
        BigDecimal totalEgresos = movimientoRepository.sumMontoByCajaIdAndTipo(cajaId, "EGRESO");
        BigDecimal saldoActual = caja.getMontoInicial().add(totalIngresos).subtract(totalEgresos);
        
        return SaldoResponse.builder()
                .cajaId(cajaId)
                .saldoActual(saldoActual)
                .montoInicial(caja.getMontoInicial())
                .totalIngresos(totalIngresos)
                .totalEgresos(totalEgresos)
                .build();
    }
}