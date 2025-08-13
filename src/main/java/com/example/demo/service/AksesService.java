package com.example.demo.service;

import com.example.demo.entity.MstAkses;
import com.example.demo.repository.MstAksesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AksesService {
    private final MstAksesRepository aksesRepository;

    public List<MstAkses> findAll() {
        return aksesRepository.findAll();
    }

    public Optional<MstAkses> findById(Long id) {
        return aksesRepository.findById(id);
    }

    public MstAkses save(MstAkses akses) {
        return aksesRepository.save(akses);
    }

    public void deleteById(Long id) {
        aksesRepository.deleteById(id);
    }
}
