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
@Table(name = "reportes_diarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDiario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private LocalDate fecha;
    
    @Column(name = "total_ingresos", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalIngresos;
    
    @Column(name = "total_egresos", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalEgresos;
    
    @Column(name = "flujo_neto", nullable = false, precision = 10, scale = 2)
    private BigDecimal flujoNeto;
    
    @Column(name = "ventas_efectivo", nullable = false, precision = 10, scale = 2)
    private BigDecimal ventasEfectivo;
    
    @Column(name = "ventas_tarjeta", nullable = false, precision = 10, scale = 2)
    private BigDecimal ventasTarjeta;
    
    @Column(name = "ventas_yape_plin", nullable = false, precision = 10, scale = 2)
    private BigDecimal ventasYapePlin;
    
    @Column(name = "ventas_qr", nullable = false, precision = 10, scale = 2)
    private BigDecimal ventasQr;
    
    @Column(name = "total_facturas", nullable = false)
    private Integer totalFacturas;
    
    @Column(name = "total_boletas", nullable = false)
    private Integer totalBoletas;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}