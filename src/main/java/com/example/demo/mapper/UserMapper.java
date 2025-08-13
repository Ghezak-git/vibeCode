package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.MstUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "akses.namaAkses", target = "role")
    UserDTO toDto(MstUser entity);
    
    @Mapping(target = "akses", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "otp", ignore = true)
    MstUser toEntity(UserDTO dto);
}
