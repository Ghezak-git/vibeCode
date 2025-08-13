-- V1__baseline.sql
CREATE TABLE mst_akses (
    id SERIAL PRIMARY KEY,
    nama_akses VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE mst_menu (
    id SERIAL PRIMARY KEY,
    nama_menu VARCHAR(50) NOT NULL UNIQUE,
    path VARCHAR(100) NOT NULL
);

CREATE TABLE map_akses_menu (
    id_akses INTEGER NOT NULL REFERENCES mst_akses(id),
    id_menu INTEGER NOT NULL REFERENCES mst_menu(id),
    PRIMARY KEY (id_akses, id_menu)
);

CREATE TABLE mst_user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    nama_lengkap VARCHAR(100) NOT NULL,
    no_hp VARCHAR(20),
    alamat VARCHAR(255),
    link_image VARCHAR(255),
    path_image VARCHAR(255),
    is_registered BOOLEAN,
    otp VARCHAR(10),
    id_akses INTEGER REFERENCES mst_akses(id)
);

CREATE TABLE mst_kategori_produk (
    id SERIAL PRIMARY KEY,
    nama_kategori VARCHAR(100) NOT NULL UNIQUE,
    deskripsi VARCHAR(255),
    notes VARCHAR(255)
);

CREATE TABLE mst_supplier (
    id SERIAL PRIMARY KEY,
    nama_supplier VARCHAR(100) NOT NULL UNIQUE,
    alamat VARCHAR(255),
    no_hp VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE mst_produk (
    id SERIAL PRIMARY KEY,
    id_kategori_produk INTEGER REFERENCES mst_kategori_produk(id),
    nama_produk VARCHAR(100) NOT NULL,
    merk VARCHAR(50),
    model VARCHAR(50),
    warna VARCHAR(50),
    deskripsi_produk VARCHAR(255),
    stok INTEGER,
    link_image VARCHAR(255),
    path_image VARCHAR(255)
);

CREATE TABLE map_produk_supplier (
    id_produk INTEGER NOT NULL REFERENCES mst_produk(id),
    id_supplier INTEGER NOT NULL REFERENCES mst_supplier(id),
    PRIMARY KEY (id_produk, id_supplier)
);

CREATE TABLE log_produk (
    id SERIAL PRIMARY KEY,
    id_produk INTEGER,
    nama_produk VARCHAR(100),
    stok INTEGER,
    flag CHAR(1),
    created_at TIMESTAMP,
    created_by INTEGER,
    reason VARCHAR(255)
);

CREATE TABLE log_kategori_produk (
    id SERIAL PRIMARY KEY,
    id_kategori_produk INTEGER,
    nama_kategori VARCHAR(100),
    flag CHAR(1),
    created_at TIMESTAMP,
    created_by INTEGER
);

CREATE TABLE log_supplier (
    id SERIAL PRIMARY KEY,
    id_supplier INTEGER,
    nama_supplier VARCHAR(100),
    flag CHAR(1),
    created_at TIMESTAMP,
    created_by INTEGER
);
