package com.example.demo.service;

import com.example.demo.entity.LogKategoriProduk;
import com.example.demo.entity.MstKategoriProduk;
import com.example.demo.repository.LogKategoriProdukRepository;
import com.example.demo.repository.MstKategoriProdukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KategoriProdukAuditService {
    private final MstKategoriProdukRepository kategoriProdukRepository;
    private final LogKategoriProdukRepository logKategoriProdukRepository;

    @Transactional
    public MstKategoriProduk saveWithAudit(MstKategoriProduk kategori, String flag, Long userId) {
        MstKategoriProduk saved = kategoriProdukRepository.save(kategori);
        logKategoriProdukRepository.save(LogKategoriProduk.builder()
                .idKategoriProduk(saved.getId())
                .namaKategori(saved.getNamaKategori())
                .flag(flag)
                .createdAt(LocalDateTime.now())
                .createdBy(userId)
                .build());
        return saved;
    }
}
