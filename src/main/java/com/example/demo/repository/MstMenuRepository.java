package com.example.demo.repository;

import com.example.demo.entity.MstMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstMenuRepository extends JpaRepository<MstMenu, Long> {
    boolean existsByNamaMenu(String namaMenu);
}
