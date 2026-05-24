package com.soa.soafinanzas.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soa.soafinanzas.domain.model.ReporteDiario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReporteDiarioRepository extends JpaRepository<ReporteDiario, UUID> {
    
    Optional<ReporteDiario> findByFecha(LocalDate fecha);
    
    List<ReporteDiario> findByFechaBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT r FROM ReporteDiario r ORDER BY r.fecha DESC LIMIT 7")
    List<ReporteDiario> findLast7DaysReports();
}