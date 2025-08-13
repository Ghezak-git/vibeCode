package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_produk")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogProduk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idProduk;
    private String namaProduk;
    private Integer stok;
    private String flag; // I/U/D
    private LocalDateTime createdAt;
    private Long createdBy;
    private String reason;
}
