package com.example.demo.service;

import com.example.demo.entity.LogSupplier;
import com.example.demo.entity.MstSupplier;
import com.example.demo.repository.LogSupplierRepository;
import com.example.demo.repository.MstSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SupplierAuditService {
    private final MstSupplierRepository supplierRepository;
    private final LogSupplierRepository logSupplierRepository;

    @Transactional
    public MstSupplier saveWithAudit(MstSupplier supplier, String flag, Long userId) {
        MstSupplier saved = supplierRepository.save(supplier);
        logSupplierRepository.save(LogSupplier.builder()
                .idSupplier(saved.getId())
                .namaSupplier(saved.getNamaSupplier())
                .flag(flag)
                .createdAt(LocalDateTime.now())
                .createdBy(userId)
                .build());
        return saved;
    }
}
