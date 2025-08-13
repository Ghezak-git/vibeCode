package com.example.demo.controller;

import com.example.demo.dto.MenuDTO;
import com.example.demo.entity.MstMenu;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final MenuMapper menuMapper;

    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        List<MenuDTO> dtos = menuService.findAll().stream()
                .map(menuMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable Long id) {
        return menuService.findById(id)
                .map(menuMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO dto) {
        MstMenu entity = menuMapper.toEntity(dto);
        MstMenu saved = menuService.save(entity);
        return ResponseEntity.ok(menuMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> updateMenu(@PathVariable Long id, @RequestBody MenuDTO dto) {
        dto.setId(id);
        MstMenu entity = menuMapper.toEntity(dto);
        MstMenu saved = menuService.save(entity);
        return ResponseEntity.ok(menuMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
