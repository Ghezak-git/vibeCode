package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "mst_kategori_produk")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstKategoriProduk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String namaKategori;

    @Size(max = 255)
    private String deskripsi;

    @Size(max = 255)
    private String notes;

    @OneToMany(mappedBy = "kategoriProduk")
    private Set<MstProduk> produkList;
}
