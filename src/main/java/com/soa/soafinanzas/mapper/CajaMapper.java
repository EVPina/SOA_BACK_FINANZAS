package com.soa.soafinanzas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.soa.soafinanzas.domain.model.Caja;
import com.soa.soafinanzas.dto.request.AperturaCajaRequest;
import com.soa.soafinanzas.dto.response.CajaResponse;

@Mapper(componentModel = "spring")
public interface CajaMapper {
    
    CajaMapper INSTANCE = Mappers.getMapper(CajaMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "usuarioCierreId", ignore = true)
    @Mapping(target = "montoFinalEsperado", ignore = true)
    @Mapping(target = "montoFinalReal", ignore = true)
    @Mapping(target = "diferencia", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "aperturaEn", ignore = true)
    @Mapping(target = "cierreEn", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Caja toEntity(AperturaCajaRequest request);
    
    CajaResponse toResponse(Caja caja);
}