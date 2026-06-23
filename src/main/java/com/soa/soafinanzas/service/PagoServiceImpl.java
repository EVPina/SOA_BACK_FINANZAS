package com.soa.soafinanzas.service;

import com.soa.soafinanzas.domain.model.MovimientoFinanciero;
import com.soa.soafinanzas.domain.model.Pago;
import com.soa.soafinanzas.domain.repository.MovimientoFinancieroRepository;
import com.soa.soafinanzas.domain.repository.PagoRepository;
import com.soa.soafinanzas.dto.request.PagoRequest;
import com.soa.soafinanzas.dto.response.PagoResponse;
import com.soa.soafinanzas.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final MovimientoFinancieroRepository movimientoRepository; // ← Inyectar este repositorio
    private final CajaService cajaService;

    @Override
    @Transactional
    public PagoResponse registrarPago(PagoRequest request) {
        log.info("Registrando pago para pedido: {}", request.getPedidoId());

        // Obtener la caja actual abierta
        UUID cajaId;
        try {
            cajaId = cajaService.getCajaActualEntity().getId();
        } catch (Exception e) {
            log.error("No hay caja abierta: {}", e.getMessage());
            throw new RuntimeException("No hay caja abierta para registrar el pago", e);
        }

        // 1. Guardar el PAGO
        Pago pago = Pago.builder()
                .pedidoId(request.getPedidoId())
                .cajaId(cajaId)
                .metodoPago(request.getMetodoPago())
                .monto(request.getMonto())
                .referencia(request.getReferencia())
                .estado("COMPLETADO")
                .build();

        pago = pagoRepository.save(pago);
        log.info("Pago guardado con ID: {}", pago.getId());

        // 2. Guardar el MOVIMIENTO FINANCIERO (INGRESO)
        MovimientoFinanciero movimiento = MovimientoFinanciero.builder()
                .cajaId(cajaId)
                .tipo("INGRESO")
                .concepto("Pago de pedido " + request.getPedidoId())
                .categoria("venta")
                .metodoPago(request.getMetodoPago())
                .monto(request.getMonto())
                .referenciaId(pago.getId()) // Vinculamos el movimiento al ID del pago
                .build();

        movimientoRepository.save(movimiento);
        log.info("Movimiento financiero de ingreso guardado con ID: {}", movimiento.getId());

        // 3. Retornar la respuesta
        return PagoResponse.builder()
                .id(pago.getId())
                .pedidoId(pago.getPedidoId())
                .metodoPago(pago.getMetodoPago())
                .monto(pago.getMonto())
                .referencia(pago.getReferencia())
                .estado(pago.getEstado())
                .createdAt(pago.getCreatedAt())
                .build();
    }

    @Override
    public PagoResponse obtenerPago(UUID id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con ID: " + id));
        return PagoResponse.builder()
                .id(pago.getId())
                .pedidoId(pago.getPedidoId())
                .metodoPago(pago.getMetodoPago())
                .monto(pago.getMonto())
                .referencia(pago.getReferencia())
                .estado(pago.getEstado())
                .createdAt(pago.getCreatedAt())
                .build();
    }
}