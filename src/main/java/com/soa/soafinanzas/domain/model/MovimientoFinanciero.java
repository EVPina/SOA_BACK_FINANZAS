package com.soa.soafinanzas.domain.model;
    
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movimientos_financieros")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoFinanciero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "caja_id", nullable = false)
    private UUID cajaId;
    
    @Column(nullable = false, length = 10)
    private String tipo;
    
    @Column(nullable = false, length = 255)
    private String concepto;
    
    @Column(nullable = false, length = 25)
    private String categoria;
    
    @Column(name = "metodo_pago", nullable = false, length = 15)
    private String metodoPago;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
    
    @Column(name = "referencia_id")
    private UUID referenciaId;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}