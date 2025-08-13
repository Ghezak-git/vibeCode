package com.example.demo.mapper;

import com.example.demo.dto.ProdukDTO;
import com.example.demo.entity.MstProduk;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProdukMapper {
    @Mapping(source = "kategoriProduk.id", target = "idKategoriProduk")
    ProdukDTO toDto(MstProduk entity);

    @Mapping(source = "idKategoriProduk", target = "kategoriProduk.id")
    @Mapping(target = "suppliers", ignore = true)
    MstProduk toEntity(ProdukDTO dto);
}
