package com.soa.soafinanzas.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoRequest {
    
    @NotBlank(message = "El tipo es requerido")
    private String tipo;
    
    @NotBlank(message = "El concepto es requerido")
    @Size(max = 255, message = "El concepto no puede exceder 255 caracteres")
    private String concepto;
    
    @NotBlank(message = "La categoría es requerida")
    private String categoria;
    
    @NotBlank(message = "El método de pago es requerido")
    private String metodoPago;
    
    @NotNull(message = "El monto es requerido")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;
    
    private UUID referenciaId;
}