package com.soa.soafinanzas.service;

import com.soa.soafinanzas.domain.model.Comprobante;
import com.soa.soafinanzas.domain.repository.ComprobanteRepository;
import com.soa.soafinanzas.dto.request.ComprobanteRequest;
import com.soa.soafinanzas.dto.response.ComprobanteResponse;
import com.soa.soafinanzas.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;

    @Override
    @Transactional
    public ComprobanteResponse generarComprobante(ComprobanteRequest request) {
        log.info("Generando comprobante para pago: {}", request.getPagoId());

        // Definir serie y número automático (puedes personalizarlo)
        String serie = "BOLETA".equals(request.getTipo()) ? "B001" : "F001";
        Integer numero = (int) (comprobanteRepository.count() + 1);

        Comprobante comprobante = Comprobante.builder()
                .pagoId(request.getPagoId())
                .tipo(request.getTipo())
                .serie(serie)
                .numero(numero)
                .ruc(request.getRuc())
                .razonSocial(request.getRazonSocial())
                .build();

        comprobante = comprobanteRepository.save(comprobante);

        return ComprobanteResponse.builder()
                .id(comprobante.getId())
                .pagoId(comprobante.getPagoId())
                .tipo(comprobante.getTipo())
                .serie(comprobante.getSerie())
                .numero(comprobante.getNumero())
                .ruc(comprobante.getRuc())
                .razonSocial(comprobante.getRazonSocial())
                .createdAt(comprobante.getCreatedAt())
                .build();
    }

    @Override
    public ComprobanteResponse obtenerComprobante(UUID id) {
        Comprobante comprobante = comprobanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comprobante no encontrado con ID: " + id));
        return ComprobanteResponse.builder()
                .id(comprobante.getId())
                .pagoId(comprobante.getPagoId())
                .tipo(comprobante.getTipo())
                .serie(comprobante.getSerie())
                .numero(comprobante.getNumero())
                .ruc(comprobante.getRuc())
                .razonSocial(comprobante.getRazonSocial())
                .createdAt(comprobante.getCreatedAt())
                .build();
    }
}