package com.example.demo.controller;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.entity.MstSupplier;
import com.example.demo.mapper.SupplierMapper;
import com.example.demo.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> dtos = supplierService.findAll().stream()
                .map(supplierMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        return supplierService.findById(id)
                .map(supplierMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO dto) {
        MstSupplier entity = supplierMapper.toEntity(dto);
        MstSupplier saved = supplierService.save(entity);
        return ResponseEntity.ok(supplierMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO dto) {
        dto.setId(id);
        MstSupplier entity = supplierMapper.toEntity(dto);
        MstSupplier saved = supplierService.save(entity);
        return ResponseEntity.ok(supplierMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
