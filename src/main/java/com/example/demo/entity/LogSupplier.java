package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idSupplier;
    private String namaSupplier;
    private String flag; // I/U/D
    private LocalDateTime createdAt;
    private Long createdBy;
}
