package com.soa.soafinanzas.service;

import com.soa.soafinanzas.dto.request.PagoRequest;
import com.soa.soafinanzas.dto.response.PagoResponse;
import java.util.UUID;

public interface PagoService {
    PagoResponse registrarPago(PagoRequest request);
    PagoResponse obtenerPago(UUID id);
}