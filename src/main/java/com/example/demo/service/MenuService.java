package com.example.demo.service;

import com.example.demo.entity.MstMenu;
import com.example.demo.repository.MstMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MstMenuRepository menuRepository;

    public List<MstMenu> findAll() {
        return menuRepository.findAll();
    }

    public Optional<MstMenu> findById(Long id) {
        return menuRepository.findById(id);
    }

    public MstMenu save(MstMenu menu) {
        return menuRepository.save(menu);
    }

    public void deleteById(Long id) {
        menuRepository.deleteById(id);
    }
}
