package com.example.demo.mapper;

import com.example.demo.dto.AksesDTO;
import com.example.demo.entity.MstAkses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AksesMapper {
    AksesDTO toDto(MstAkses entity);
    
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "users", ignore = true)
    MstAkses toEntity(AksesDTO dto);
}
