package com.example.demo.repository;

import com.example.demo.entity.MstSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstSupplierRepository extends JpaRepository<MstSupplier, Long> {
    boolean existsByNamaSupplier(String namaSupplier);
}
