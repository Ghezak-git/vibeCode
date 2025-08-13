package com.example.demo.controller;

import com.example.demo.dto.AksesDTO;
import com.example.demo.entity.MstAkses;
import com.example.demo.mapper.AksesMapper;
import com.example.demo.service.AksesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class AksesController {
    private final AksesService aksesService;
    private final AksesMapper aksesMapper;

    @GetMapping
    public ResponseEntity<List<AksesDTO>> getAllRoles() {
        List<AksesDTO> dtos = aksesService.findAll().stream()
                .map(aksesMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AksesDTO> getRoleById(@PathVariable Long id) {
        return aksesService.findById(id)
                .map(aksesMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AksesDTO> createRole(@RequestBody AksesDTO dto) {
        MstAkses entity = aksesMapper.toEntity(dto);
        MstAkses saved = aksesService.save(entity);
        return ResponseEntity.ok(aksesMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AksesDTO> updateRole(@PathVariable Long id, @RequestBody AksesDTO dto) {
        dto.setId(id);
        MstAkses entity = aksesMapper.toEntity(dto);
        MstAkses saved = aksesService.save(entity);
        return ResponseEntity.ok(aksesMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        aksesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
