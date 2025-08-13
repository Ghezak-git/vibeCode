package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "mst_akses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstAkses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String namaAkses;

    @OneToMany(mappedBy = "akses")
    private Set<MstUser> users;

    @ManyToMany
    @JoinTable(
        name = "map_akses_menu",
        joinColumns = @JoinColumn(name = "id_akses"),
        inverseJoinColumns = @JoinColumn(name = "id_menu")
    )
    private Set<MstMenu> menus;
}
