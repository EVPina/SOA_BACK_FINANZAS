package com.soa.soafinanzas.service;

import com.soa.soafinanzas.domain.model.Pago;
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
    private final CajaService cajaService; // Para obtener la caja actual

    @Override
    @Transactional
    public PagoResponse registrarPago(PagoRequest request) {
        log.info("Registrando pago para pedido: {}", request.getPedidoId());

        // Obtener la caja actual abierta
        UUID cajaId = cajaService.getCajaActualEntity().getId();

        Pago pago = Pago.builder()
                .pedidoId(request.getPedidoId())
                .cajaId(cajaId)
                .metodoPago(request.getMetodoPago())
                .monto(request.getMonto())
                .referencia(request.getReferencia())
                .estado("COMPLETADO")
                .build();

        pago = pagoRepository.save(pago);

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