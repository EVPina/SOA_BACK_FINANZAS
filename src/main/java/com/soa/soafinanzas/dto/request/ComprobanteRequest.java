package com.soa.soafinanzas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ComprobanteRequest {

    @Schema(description = "ID del pago asociado al comprobante", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "El ID del pago es obligatorio")
    private UUID pagoId;

    @Schema(description = "Tipo de comprobante", example = "BOLETA")
    @NotBlank(message = "El tipo de comprobante es obligatorio")
    private String tipo; // "BOLETA" o "FACTURA"

    @Schema(description = "Número de serie del comprobante", example = "20605723056")
    @NotBlank(message = "El número de serie es obligatorio")
    private String ruc;

    @Schema(description = "Razón social del cliente", example = "Empresa S.A.")
    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;
}