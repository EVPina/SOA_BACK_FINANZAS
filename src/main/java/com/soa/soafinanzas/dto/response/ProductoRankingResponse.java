package com.soa.soafinanzas.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProductoRankingResponse {
    private UUID productoId;
    private String nombreProducto;
    private Long cantidadVendida;
    private BigDecimal totalVentas;
}