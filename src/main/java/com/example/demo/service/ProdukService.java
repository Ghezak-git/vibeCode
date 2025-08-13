package com.example.demo.service;

import com.example.demo.entity.MstProduk;
import com.example.demo.repository.MstProdukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdukService {
    private final MstProdukRepository produkRepository;

    public List<MstProduk> findAll() {
        return produkRepository.findAll();
    }

    public Optional<MstProduk> findById(Long id) {
        return produkRepository.findById(id);
    }

    public MstProduk save(MstProduk produk) {
        return produkRepository.save(produk);
    }

    public void deleteById(Long id) {
        produkRepository.deleteById(id);
    }
}
