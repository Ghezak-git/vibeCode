package com.example.demo.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MstProdukTest {

    private Validator validator;
    private MstKategoriProduk testKategori;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        testKategori = MstKategoriProduk.builder()
                .id(1L)
                .namaKategori("Electronics")
                .build();
    }

    @Test
    void testValidProduk_NoValidationErrors() {
        // Given
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15")
                .merk("Apple")
                .model("A2345")
                .warna("Blue")
                .deskripsiProduk("Latest iPhone model")
                .stok(50)
                .linkImage("http://example.com/image.jpg")
                .pathImage("/images/iphone15.jpg")
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNullKategoriProduk_ValidationError() {
        // Given
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(null)
                .namaProduk("iPhone 15")
                .stok(50)
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("kategoriProduk")));
    }

    @Test
    void testBlankNamaProduk_ValidationError() {
        // Given
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("")
                .stok(50)
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("namaProduk")));
    }

    @Test
    void testLongNamaProduk_ValidationError() {
        // Given
        String longName = "a".repeat(101);
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk(longName)
                .stok(50)
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("namaProduk")));
    }

    @Test
    void testNullStok_ValidationError() {
        // Given
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15")
                .stok(null)
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("stok")));
    }

    @Test
    void testNegativeStok_ValidationError() {
        // Given
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15")
                .stok(-1)
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("stok")));
    }

    @Test
    void testZeroStok_ValidProduk() {
        // Given
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15")
                .stok(0)
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void testLongMerk_ValidationError() {
        // Given
        String longMerk = "a".repeat(51);
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15")
                .merk(longMerk)
                .stok(50)
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("merk")));
    }

    @Test
    void testLongModel_ValidationError() {
        // Given
        String longModel = "a".repeat(51);
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15")
                .model(longModel)
                .stok(50)
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("model")));
    }

    @Test
    void testLongWarna_ValidationError() {
        // Given
        String longWarna = "a".repeat(51);
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15")
                .warna(longWarna)
                .stok(50)
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("warna")));
    }

    @Test
    void testOptionalFields_ValidProduk() {
        // Given - only required fields
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15")
                .stok(50)
                // merk, model, warna, deskripsiProduk, linkImage, pathImage are optional
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void testAllFieldsMaxLength_ValidProduk() {
        // Given
        MstProduk produk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("a".repeat(100))
                .merk("b".repeat(50))
                .model("c".repeat(50))
                .warna("d".repeat(50))
                .deskripsiProduk("e".repeat(255))
                .stok(1000)
                .linkImage("f".repeat(255))
                .pathImage("g".repeat(255))
                .build();

        // When
        Set<ConstraintViolation<MstProduk>> violations = validator.validate(produk);

        // Then
        assertTrue(violations.isEmpty());
    }
}