package com.example.demo.service;

import com.example.demo.entity.MstProduk;
import com.example.demo.entity.MstSupplier;
import com.example.demo.repository.MstProdukRepository;
import com.example.demo.repository.MstSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProdukSupplierService {
    private final MstProdukRepository produkRepository;
    private final MstSupplierRepository supplierRepository;

    public Set<MstSupplier> getSuppliersByProductId(Long produkId) {
        Optional<MstProduk> produkOpt = produkRepository.findById(produkId);
        return produkOpt.map(MstProduk::getSuppliers).orElse(new HashSet<>());
    }

    @Transactional
    public void setSuppliersForProduct(Long produkId, List<Long> supplierIds) {
        MstProduk produk = produkRepository.findById(produkId)
                .orElseThrow(() -> new RuntimeException("Produk tidak ditemukan"));
        Set<MstSupplier> suppliers = new HashSet<>(supplierRepository.findAllById(supplierIds));
        produk.setSuppliers(suppliers);
        produkRepository.save(produk);
    }
}
