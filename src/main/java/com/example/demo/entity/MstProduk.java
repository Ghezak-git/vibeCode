package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "mst_produk")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstProduk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_kategori_produk")
    @NotNull
    private MstKategoriProduk kategoriProduk;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String namaProduk;

    @Size(max = 50)
    private String merk;

    @Size(max = 50)
    private String model;

    @Size(max = 50)
    private String warna;

    @Size(max = 255)
    private String deskripsiProduk;

    @NotNull
    @Min(0)
    private Integer stok;

    @Size(max = 255)
    private String linkImage;

    @Size(max = 255)
    private String pathImage;

    @ManyToMany
    @JoinTable(
        name = "map_produk_supplier",
        joinColumns = @JoinColumn(name = "id_produk"),
        inverseJoinColumns = @JoinColumn(name = "id_supplier")
    )
    private Set<MstSupplier> suppliers;
}
