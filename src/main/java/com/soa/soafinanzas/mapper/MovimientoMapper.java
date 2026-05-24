package com.soa.soafinanzas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.soa.soafinanzas.domain.model.MovimientoFinanciero;
import com.soa.soafinanzas.dto.request.MovimientoRequest;
import com.soa.soafinanzas.dto.response.MovimientoResponse;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    
    MovimientoMapper INSTANCE = Mappers.getMapper(MovimientoMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cajaId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    MovimientoFinanciero toEntity(MovimientoRequest request);
    
    MovimientoResponse toResponse(MovimientoFinanciero movimiento);
}