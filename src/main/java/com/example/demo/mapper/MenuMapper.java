package com.example.demo.mapper;

import com.example.demo.dto.MenuDTO;
import com.example.demo.entity.MstMenu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuDTO toDto(MstMenu entity);
    
    @Mapping(target = "aksesList", ignore = true)
    MstMenu toEntity(MenuDTO dto);
}
