package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String namaLengkap;
    private String noHp;
    private String alamat;
    private String linkImage;
    private String pathImage;
    private Boolean isRegistered;
    private String role;
}
