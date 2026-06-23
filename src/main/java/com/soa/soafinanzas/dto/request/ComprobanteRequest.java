package com.soa.soafinanzas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ComprobanteRequest {
    @NotNull(message = "El ID del pago es obligatorio")
    private UUID pagoId;

    @NotBlank(message = "El tipo de comprobante es obligatorio")
    private String tipo; // "BOLETA" o "FACTURA"

    private String ruc;
    private String razonSocial;
}