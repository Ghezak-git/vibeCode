package com.example.demo.service;

import com.example.demo.entity.LogProduk;
import com.example.demo.entity.MstProduk;
import com.example.demo.repository.LogProdukRepository;
import com.example.demo.repository.MstProdukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProdukStockService {
    private final MstProdukRepository produkRepository;
    private final LogProdukRepository logProdukRepository;

    @Transactional
    public MstProduk adjustStock(Long produkId, int delta, String reason, Long userId) {
        MstProduk produk = produkRepository.findById(produkId)
                .orElseThrow(() -> new RuntimeException("Produk tidak ditemukan"));
        int newStock = (produk.getStok() == null ? 0 : produk.getStok()) + delta;
        if (newStock < 0) throw new RuntimeException("Stok tidak boleh kurang dari 0");
        produk.setStok(newStock);
        produkRepository.save(produk);
        logProdukRepository.save(LogProduk.builder()
                .idProduk(produk.getId())
                .namaProduk(produk.getNamaProduk())
                .stok(newStock)
                .flag("U")
                .createdAt(LocalDateTime.now())
                .createdBy(userId)
                .reason(reason)
                .build());
        return produk;
    }
}
