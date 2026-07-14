package com.soa.soafinanzas.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class ProductoRankingResponse {
    @Schema(description = "ID del producto", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID productoId;
    @Schema(description = "Nombre del producto", example = "Producto 1")
    private String nombreProducto;
    @Schema(description = "Cantidad vendida", example = "10")
    private Long cantidadVendida;
    @Schema(description = "Total de ventas", example = "1000.00")
    private BigDecimal totalVentas;
}