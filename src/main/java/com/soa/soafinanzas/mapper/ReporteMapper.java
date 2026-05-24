package com.soa.soafinanzas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.soa.soafinanzas.domain.model.ReporteDiario;
import com.soa.soafinanzas.dto.response.ReporteResponse;

@Mapper(componentModel = "spring")
public interface ReporteMapper {
    
    ReporteMapper INSTANCE = Mappers.getMapper(ReporteMapper.class);
    
    @Mapping(source = "ventasEfectivo", target = "desgloseVentas.efectivo")
    @Mapping(source = "ventasTarjeta", target = "desgloseVentas.tarjeta")
    @Mapping(source = "ventasYapePlin", target = "desgloseVentas.yapePlin")
    @Mapping(source = "ventasQr", target = "desgloseVentas.qr")
    @Mapping(source = "totalFacturas", target = "documentos.facturas")
    @Mapping(source = "totalBoletas", target = "documentos.boletas")
    ReporteResponse toResponse(ReporteDiario reporte);
}