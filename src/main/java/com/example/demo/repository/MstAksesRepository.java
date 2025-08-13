package com.example.demo.repository;

import com.example.demo.entity.MstAkses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstAksesRepository extends JpaRepository<MstAkses, Long> {
    boolean existsByNamaAkses(String namaAkses);
}
