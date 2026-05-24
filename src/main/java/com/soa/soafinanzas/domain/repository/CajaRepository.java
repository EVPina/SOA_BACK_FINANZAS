package com.soa.soafinanzas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soa.soafinanzas.domain.model.Caja;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CajaRepository extends JpaRepository<Caja, UUID> {
    
    Optional<Caja> findByEstado(String estado);
    
    List<Caja> findByFecha(LocalDate fecha);
    
    @Query("SELECT c FROM Caja c WHERE c.estado = 'ABIERTA' ORDER BY c.aperturaEn DESC")
    Optional<Caja> findCajaAbierta();
    
    boolean existsByEstado(String estado);
}