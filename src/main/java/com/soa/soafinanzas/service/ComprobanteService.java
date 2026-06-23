package com.soa.soafinanzas.service;

import com.soa.soafinanzas.dto.request.ComprobanteRequest;
import com.soa.soafinanzas.dto.response.ComprobanteResponse;
import java.util.UUID;

public interface ComprobanteService {
    ComprobanteResponse generarComprobante(ComprobanteRequest request);
    ComprobanteResponse obtenerComprobante(UUID id);
}