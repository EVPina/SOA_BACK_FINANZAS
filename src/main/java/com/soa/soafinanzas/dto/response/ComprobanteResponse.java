package com.soa.soafinanzas.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class ComprobanteResponse {

    @Schema(description = "ID del comprobante", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "ID del pago asociado al comprobante", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID pagoId;
    @Schema(description = "Tipo de comprobante", example = "FACTURA")
    private String tipo;
    @Schema(description = "Serie del comprobante", example = "F001")
    private String serie;
    @Schema(description = "Número del comprobante", example = "001")
    private Integer numero;
    @Schema(description = "RUC del cliente", example = "20123456789")
    private String ruc;
    @Schema(description = "Razón social del cliente", example = "Empresa S.A.")
    private String razonSocial;
    @Schema(description = "Fecha y hora de creación del comprobante", example = "2023-10-01T08:00:00")
    private LocalDateTime createdAt;
}