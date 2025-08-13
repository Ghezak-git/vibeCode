package com.example.demo.controller;

import com.example.demo.entity.LogKategoriProduk;
import com.example.demo.repository.LogKategoriProdukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs/categories")
@RequiredArgsConstructor
public class LogKategoriProdukController {
    private final LogKategoriProdukRepository logKategoriProdukRepository;

    @GetMapping
    public ResponseEntity<List<LogKategoriProduk>> getAll(@RequestParam(required = false) Long categoryId) {
        if (categoryId != null) {
            return ResponseEntity.ok(logKategoriProdukRepository.findAll().stream()
                .filter(l -> l.getIdKategoriProduk() != null && l.getIdKategoriProduk().equals(categoryId))
                .toList());
        }
        return ResponseEntity.ok(logKategoriProdukRepository.findAll());
    }
}
