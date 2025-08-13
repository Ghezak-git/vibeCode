package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "mst_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Size(min = 6, max = 255)
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String namaLengkap;

    @Size(max = 20)
    private String noHp;

    @Size(max = 255)
    private String alamat;

    @Size(max = 255)
    private String linkImage;

    @Size(max = 255)
    private String pathImage;

    private Boolean isRegistered;

    @Size(max = 10)
    private String otp;

    @ManyToOne
    @JoinColumn(name = "id_akses")
    private MstAkses akses;
}
