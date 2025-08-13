package com.example.demo.controller;

import com.example.demo.entity.LogProduk;
import com.example.demo.repository.LogProdukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs/products")
@RequiredArgsConstructor
public class LogProdukController {
    private final LogProdukRepository logProdukRepository;

    @GetMapping
    public ResponseEntity<List<LogProduk>> getAll(@RequestParam(required = false) Long productId) {
        if (productId != null) {
            return ResponseEntity.ok(logProdukRepository.findAll().stream()
                .filter(l -> l.getIdProduk() != null && l.getIdProduk().equals(productId))
                .toList());
        }
        return ResponseEntity.ok(logProdukRepository.findAll());
    }
}
