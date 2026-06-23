package com.soa.soafinanzas.domain.repository;

import com.soa.soafinanzas.domain.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PagoRepository extends JpaRepository<Pago, UUID> {
}