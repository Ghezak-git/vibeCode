package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_kategori_produk")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogKategoriProduk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idKategoriProduk;
    private String namaKategori;
    private String flag; // I/U/D
    private LocalDateTime createdAt;
    private Long createdBy;
}
