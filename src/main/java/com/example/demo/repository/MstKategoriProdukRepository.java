package com.example.demo.repository;

import com.example.demo.entity.MstKategoriProduk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstKategoriProdukRepository extends JpaRepository<MstKategoriProduk, Long> {
    boolean existsByNamaKategori(String namaKategori);
}
