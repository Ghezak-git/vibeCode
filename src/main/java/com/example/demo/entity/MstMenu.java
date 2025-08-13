package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "mst_menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String namaMenu;

    @Column(nullable = false)
    private String path;

    @ManyToMany(mappedBy = "menus")
    private Set<MstAkses> aksesList;
}
