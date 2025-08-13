package com.example.demo.service;

import com.example.demo.entity.MstSupplier;
import com.example.demo.repository.MstSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final MstSupplierRepository supplierRepository;

    public List<MstSupplier> findAll() {
        return supplierRepository.findAll();
    }

    public Optional<MstSupplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    public MstSupplier save(MstSupplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }
}
