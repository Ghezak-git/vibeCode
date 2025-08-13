package com.example.demo.mapper;

import com.example.demo.dto.KategoriProdukDTO;
import com.example.demo.entity.MstKategoriProduk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KategoriProdukMapper {
    KategoriProdukDTO toDto(MstKategoriProduk entity);
    
    @Mapping(target = "produkList", ignore = true)
    MstKategoriProduk toEntity(KategoriProdukDTO dto);
}
