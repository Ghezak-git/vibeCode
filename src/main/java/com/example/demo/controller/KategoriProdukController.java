package com.example.demo.controller;

import com.example.demo.dto.KategoriProdukDTO;
import com.example.demo.entity.MstKategoriProduk;
import com.example.demo.mapper.KategoriProdukMapper;
import com.example.demo.service.KategoriProdukService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class KategoriProdukController {
    private final KategoriProdukService kategoriProdukService;
    private final KategoriProdukMapper kategoriProdukMapper;

    @GetMapping
    public ResponseEntity<List<KategoriProdukDTO>> getAllCategories() {
        List<KategoriProdukDTO> dtos = kategoriProdukService.findAll().stream()
                .map(kategoriProdukMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KategoriProdukDTO> getCategoryById(@PathVariable Long id) {
        return kategoriProdukService.findById(id)
                .map(kategoriProdukMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<KategoriProdukDTO> createCategory(@RequestBody KategoriProdukDTO dto) {
        MstKategoriProduk entity = kategoriProdukMapper.toEntity(dto);
        MstKategoriProduk saved = kategoriProdukService.save(entity);
        return ResponseEntity.ok(kategoriProdukMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KategoriProdukDTO> updateCategory(@PathVariable Long id, @RequestBody KategoriProdukDTO dto) {
        dto.setId(id);
        MstKategoriProduk entity = kategoriProdukMapper.toEntity(dto);
        MstKategoriProduk saved = kategoriProdukService.save(entity);
        return ResponseEntity.ok(kategoriProdukMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        kategoriProdukService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
