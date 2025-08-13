package com.example.demo.dto;

import lombok.Data;

@Data
public class ProdukDTO {
    private Long id;
    private Long idKategoriProduk;
    private String namaProduk;
    private String merk;
    private String model;
    private String warna;
    private String deskripsiProduk;
    private Integer stok;
    private String linkImage;
    private String pathImage;
}
