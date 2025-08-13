package com.example.demo.mapper;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.entity.MstSupplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDTO toDto(MstSupplier entity);
    
    @Mapping(target = "produkList", ignore = true)
    MstSupplier toEntity(SupplierDTO dto);
}
