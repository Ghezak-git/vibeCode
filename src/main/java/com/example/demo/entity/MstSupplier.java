package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "mst_supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String namaSupplier;

    @Size(max = 255)
    private String alamat;

    @Size(max = 20)
    private String noHp;

    @Email
    @Size(max = 100)
    private String email;

    @ManyToMany(mappedBy = "suppliers")
    private Set<MstProduk> produkList;
}
