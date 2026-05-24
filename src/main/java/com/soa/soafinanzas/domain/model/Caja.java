package com.soa.soafinanzas.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema="public",name = "cajas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Caja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private LocalDate fecha;
    
    @Column(name = "usuario_apertura_id", nullable = false)
    private UUID usuarioAperturaId;
    
    @Column(name = "usuario_cierre_id")
    private UUID usuarioCierreId;
    
    @Column(name = "monto_inicial", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoInicial;
    
    @Column(name = "monto_final_esperado", precision = 10, scale = 2)
    private BigDecimal montoFinalEsperado;
    
    @Column(name = "monto_final_real", precision = 10, scale = 2)
    private BigDecimal montoFinalReal;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal diferencia;
    
    @Column(nullable = false, length = 10)
    private String estado;
    
    @Column(name = "apertura_en", nullable = false)
    private LocalDateTime aperturaEn;
    
    @Column(name = "cierre_en")
    private LocalDateTime cierreEn;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        if (fecha == null) fecha = LocalDate.now();
        if (estado == null) estado = "ABIERTA";
        if (aperturaEn == null) aperturaEn = LocalDateTime.now();
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}