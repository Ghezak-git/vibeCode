package com.example.demo.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MstUserTest {

    private Validator validator;
    private MstAkses testAkses;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        testAkses = MstAkses.builder()
                .id(1L)
                .namaAkses("ADMIN")
                .build();
    }

    @Test
    void testValidUser_NoValidationErrors() {
        // Given
        MstUser user = MstUser.builder()
                .username("testuser")
                .password("password123")
                .email("test@example.com")
                .namaLengkap("Test User")
                .noHp("1234567890")
                .alamat("Test Address")
                .akses(testAkses)
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void testBlankUsername_ValidationError() {
        // Given
        MstUser user = MstUser.builder()
                .username("")
                .password("password123")
                .email("test@example.com")
                .namaLengkap("Test User")
                .akses(testAkses)
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("username")));
    }

    @Test
    void testShortUsername_ValidationError() {
        // Given
        MstUser user = MstUser.builder()
                .username("ab")
                .password("password123")
                .email("test@example.com")
                .namaLengkap("Test User")
                .akses(testAkses)
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("username")));
    }

    @Test
    void testLongUsername_ValidationError() {
        // Given
        String longUsername = "a".repeat(51);
        MstUser user = MstUser.builder()
                .username(longUsername)
                .password("password123")
                .email("test@example.com")
                .namaLengkap("Test User")
                .akses(testAkses)
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("username")));
    }

    @Test
    void testInvalidEmail_ValidationError() {
        // Given
        MstUser user = MstUser.builder()
                .username("testuser")
                .password("password123")
                .email("invalid-email")
                .namaLengkap("Test User")
                .akses(testAkses)
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    void testShortPassword_ValidationError() {
        // Given
        MstUser user = MstUser.builder()
                .username("testuser")
                .password("12345")
                .email("test@example.com")
                .namaLengkap("Test User")
                .akses(testAkses)
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }

    @Test
    void testBlankNamaLengkap_ValidationError() {
        // Given
        MstUser user = MstUser.builder()
                .username("testuser")
                .password("password123")
                .email("test@example.com")
                .namaLengkap("")
                .akses(testAkses)
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("namaLengkap")));
    }

    @Test
    void testLongNamaLengkap_ValidationError() {
        // Given
        String longName = "a".repeat(101);
        MstUser user = MstUser.builder()
                .username("testuser")
                .password("password123")
                .email("test@example.com")
                .namaLengkap(longName)
                .akses(testAkses)
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("namaLengkap")));
    }

    @Test
    void testLongNoHp_ValidationError() {
        // Given
        String longPhone = "1".repeat(21);
        MstUser user = MstUser.builder()
                .username("testuser")
                .password("password123")
                .email("test@example.com")
                .namaLengkap("Test User")
                .noHp(longPhone)
                .akses(testAkses)
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("noHp")));
    }

    @Test
    void testOptionalFields_ValidUser() {
        // Given
        MstUser user = MstUser.builder()
                .username("testuser")
                .password("password123")
                .email("test@example.com")
                .namaLengkap("Test User")
                .akses(testAkses)
                // noHp, alamat, linkImage, pathImage are optional
                .build();

        // When
        Set<ConstraintViolation<MstUser>> violations = validator.validate(user);

        // Then
        assertTrue(violations.isEmpty());
    }
}