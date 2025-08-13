package com.example.demo.controller;

import com.example.demo.entity.LogSupplier;
import com.example.demo.repository.LogSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs/suppliers")
@RequiredArgsConstructor
public class LogSupplierController {
    private final LogSupplierRepository logSupplierRepository;

    @GetMapping
    public ResponseEntity<List<LogSupplier>> getAll(@RequestParam(required = false) Long supplierId) {
        if (supplierId != null) {
            return ResponseEntity.ok(logSupplierRepository.findAll().stream()
                .filter(l -> l.getIdSupplier() != null && l.getIdSupplier().equals(supplierId))
                .toList());
        }
        return ResponseEntity.ok(logSupplierRepository.findAll());
    }
}
