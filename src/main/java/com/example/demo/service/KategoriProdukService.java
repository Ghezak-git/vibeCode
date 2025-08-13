package com.example.demo.service;

import com.example.demo.entity.MstKategoriProduk;
import com.example.demo.repository.MstKategoriProdukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KategoriProdukService {
    private final MstKategoriProdukRepository kategoriProdukRepository;

    public List<MstKategoriProduk> findAll() {
        return kategoriProdukRepository.findAll();
    }

    public Optional<MstKategoriProduk> findById(Long id) {
        return kategoriProdukRepository.findById(id);
    }

    public MstKategoriProduk save(MstKategoriProduk kategori) {
        return kategoriProdukRepository.save(kategori);
    }

    public void deleteById(Long id) {
        kategoriProdukRepository.deleteById(id);
    }
}
