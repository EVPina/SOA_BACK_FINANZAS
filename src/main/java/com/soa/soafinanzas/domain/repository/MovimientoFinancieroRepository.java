package com.soa.soafinanzas.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soa.soafinanzas.domain.model.MovimientoFinanciero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MovimientoFinancieroRepository extends JpaRepository<MovimientoFinanciero, UUID> {
    
    List<MovimientoFinanciero> findByCajaId(UUID cajaId);
    
    List<MovimientoFinanciero> findByCajaIdAndTipo(UUID cajaId, String tipo);
    
    @Query("SELECT COALESCE(SUM(m.monto), 0) FROM MovimientoFinanciero m " +
           "WHERE m.cajaId = :cajaId AND m.tipo = :tipo")
    BigDecimal sumMontoByCajaIdAndTipo(@Param("cajaId") UUID cajaId, @Param("tipo") String tipo);
    
    @Query("SELECT COALESCE(SUM(m.monto), 0) FROM MovimientoFinanciero m " +
           "WHERE m.cajaId = :cajaId AND m.tipo = 'INGRESO' AND m.metodoPago = :metodoPago")
    BigDecimal sumIngresosByMetodoPago(@Param("cajaId") UUID cajaId, @Param("metodoPago") String metodoPago);
    
    @Query("SELECT m FROM MovimientoFinanciero m WHERE DATE(m.createdAt) = :fecha")
    List<MovimientoFinanciero> findByFecha(@Param("fecha") LocalDate fecha);
}