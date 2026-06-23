package com.soa.soafinanzas.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ComprobanteResponse {
    private UUID id;
    private UUID pagoId;
    private String tipo;
    private String serie;
    private Integer numero;
    private String ruc;
    private String razonSocial;
    private LocalDateTime createdAt;
}