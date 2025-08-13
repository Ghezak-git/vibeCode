package com.example.demo.controller;

import com.example.demo.dto.ProdukDTO;
import com.example.demo.entity.MstProduk;
import com.example.demo.mapper.ProdukMapper;
import com.example.demo.service.ProdukService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProdukController {
    private final ProdukService produkService;
    private final ProdukMapper produkMapper;

    @GetMapping
    public ResponseEntity<List<ProdukDTO>> getAllProducts() {
        List<ProdukDTO> dtos = produkService.findAll().stream()
                .map(produkMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdukDTO> getProductById(@PathVariable Long id) {
        return produkService.findById(id)
                .map(produkMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdukDTO> createProduct(@RequestBody ProdukDTO dto) {
        MstProduk entity = produkMapper.toEntity(dto);
        MstProduk saved = produkService.save(entity);
        return ResponseEntity.ok(produkMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdukDTO> updateProduct(@PathVariable Long id, @RequestBody ProdukDTO dto) {
        dto.setId(id);
        MstProduk entity = produkMapper.toEntity(dto);
        MstProduk saved = produkService.save(entity);
        return ResponseEntity.ok(produkMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        produkService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
