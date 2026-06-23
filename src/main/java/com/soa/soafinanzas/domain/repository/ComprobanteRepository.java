package com.soa.soafinanzas.domain.repository;

import com.soa.soafinanzas.domain.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ComprobanteRepository extends JpaRepository<Comprobante, UUID> {
}