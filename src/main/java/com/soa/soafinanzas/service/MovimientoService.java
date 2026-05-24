package com.soa.soafinanzas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soa.soafinanzas.domain.model.Caja;
import com.soa.soafinanzas.domain.model.MovimientoFinanciero;
import com.soa.soafinanzas.domain.repository.MovimientoFinancieroRepository;
import com.soa.soafinanzas.dto.request.MovimientoRequest;
import com.soa.soafinanzas.dto.response.MovimientoResponse;
import com.soa.soafinanzas.exception.BusinessException;
import com.soa.soafinanzas.mapper.MovimientoMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovimientoService {
    
    private final MovimientoFinancieroRepository movimientoRepository;
    private final CajaService cajaService;
    private final MovimientoMapper movimientoMapper;
    
    @Transactional
    public MovimientoResponse registrarMovimiento(MovimientoRequest request) {
        // Usar el método que retorna la entidad Caja
        Caja cajaActual = cajaService.getCajaActualEntity();
        
        if (request.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("El monto debe ser mayor a cero");
        }
        
        validarCategoriaPorTipo(request.getTipo(), request.getCategoria());
        validarMetodoPago(request.getMetodoPago());
        
        MovimientoFinanciero movimiento = movimientoMapper.toEntity(request);
        movimiento.setCajaId(cajaActual.getId());
        
        MovimientoFinanciero savedMovimiento = movimientoRepository.save(movimiento);
        log.info("Movimiento registrado: {} - {}", request.getTipo(), request.getMonto());
        
        return movimientoMapper.toResponse(savedMovimiento);
    }
    
    @Transactional
    public MovimientoResponse registrarIngreso(MovimientoRequest request) {
        request.setTipo("INGRESO");
        return registrarMovimiento(request);
    }
    
    @Transactional
    public MovimientoResponse registrarEgreso(MovimientoRequest request) {
        request.setTipo("EGRESO");
        return registrarMovimiento(request);
    }
    
    public List<MovimientoResponse> getMovimientosByCaja(UUID cajaId) {
        return movimientoRepository.findByCajaId(cajaId)
                .stream()
                .map(movimientoMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<MovimientoResponse> getMovimientosByFecha(LocalDate fecha) {
        return movimientoRepository.findByFecha(fecha)
                .stream()
                .map(movimientoMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    private void validarCategoriaPorTipo(String tipo, String categoria) {
        if ("INGRESO".equals(tipo) && !"venta".equals(categoria)) {
            throw new BusinessException("Los ingresos solo pueden tener categoría 'venta'");
        }
        
        if ("EGRESO".equals(tipo)) {
            List<String> categoriasValidas = List.of("compra_proveedor", "gasto_operativo", "servicio");
            if (!categoriasValidas.contains(categoria)) {
                throw new BusinessException("Categoría de egreso no válida");
            }
        }
    }
    
    private void validarMetodoPago(String metodoPago) {
        List<String> metodosValidos = List.of("EFECTIVO", "TARJETA", "YAPE", "PLIN", "QR", "TRANSFERENCIA");
        if (!metodosValidos.contains(metodoPago)) {
            throw new BusinessException("Método de pago no válido");
        }
    }
}