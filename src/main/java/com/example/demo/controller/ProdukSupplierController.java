package com.example.demo.controller;

import com.example.demo.entity.MstSupplier;
import com.example.demo.service.ProdukSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/products/{produkId}/suppliers")
@RequiredArgsConstructor
public class ProdukSupplierController {
    private final ProdukSupplierService produkSupplierService;

    @GetMapping
    public ResponseEntity<Set<MstSupplier>> getSuppliers(@PathVariable Long produkId) {
        return ResponseEntity.ok(produkSupplierService.getSuppliersByProductId(produkId));
    }

    @PutMapping
    public ResponseEntity<Void> setSuppliers(@PathVariable Long produkId, @RequestBody List<Long> supplierIds) {
        produkSupplierService.setSuppliersForProduct(produkId, supplierIds);
        return ResponseEntity.noContent().build();
    }
}
