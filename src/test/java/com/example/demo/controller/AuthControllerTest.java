package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.UserProfile;
import com.example.demo.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private LoginRequest loginRequest;
    private LoginResponse loginResponse;
    private UserProfile userProfile;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();
        loginRequest.setUsernameOrEmail("testuser");
        loginRequest.setPassword("password123");

        userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setUsername("testuser");
        userProfile.setEmail("test@example.com");
        userProfile.setNamaLengkap("Test User");
        userProfile.setNoHp("1234567890");
        userProfile.setAlamat("Test Address");
        userProfile.setRole("ADMIN");

        loginResponse = new LoginResponse();
        loginResponse.setAccessToken("jwt-token-123");
        loginResponse.setExpiresIn(3600L);
        loginResponse.setUser(userProfile);
    }

    @Test
    void testLogin_ValidCredentials_ReturnsSuccessResponse() {
        // Given
        when(authService.login(any(LoginRequest.class))).thenReturn(loginResponse);

        // When
        ResponseEntity<LoginResponse> response = authController.login(loginRequest);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("jwt-token-123", response.getBody().getAccessToken());
        assertEquals(3600L, response.getBody().getExpiresIn());
        assertEquals("testuser", response.getBody().getUser().getUsername());
        assertEquals("ADMIN", response.getBody().getUser().getRole());

        verify(authService).login(loginRequest);
    }

    @Test
    void testLogin_ServiceThrowsException_ExceptionPropagated() {
        // Given
        when(authService.login(any(LoginRequest.class)))
                .thenThrow(new RuntimeException("Username/email atau password salah"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authController.login(loginRequest);
        });

        assertEquals("Username/email atau password salah", exception.getMessage());
        verify(authService).login(loginRequest);
    }

    @Test
    void testLogin_WithEmailInsteadOfUsername_ReturnsSuccessResponse() {
        // Given
        loginRequest.setUsernameOrEmail("test@example.com");
        when(authService.login(any(LoginRequest.class))).thenReturn(loginResponse);

        // When
        ResponseEntity<LoginResponse> response = authController.login(loginRequest);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("jwt-token-123", response.getBody().getAccessToken());
        assertEquals("testuser", response.getBody().getUser().getUsername());

        verify(authService).login(loginRequest);
    }

    @Test
    void testLogin_NullRequest_ServiceCalled() {
        // Given
        when(authService.login(null)).thenReturn(loginResponse);

        // When
        ResponseEntity<LoginResponse> response = authController.login(null);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(authService).login(null);
    }

    @Test
    void testLogin_DifferentUserProfile_ReturnsCorrectData() {
        // Given
        UserProfile differentProfile = new UserProfile();
        differentProfile.setId(2L);
        differentProfile.setUsername("admin");
        differentProfile.setEmail("admin@example.com");
        differentProfile.setRole("SUPER_ADMIN");

        LoginResponse differentResponse = new LoginResponse();
        differentResponse.setAccessToken("different-token");
        differentResponse.setExpiresIn(7200L);
        differentResponse.setUser(differentProfile);

        when(authService.login(any(LoginRequest.class))).thenReturn(differentResponse);

        // When
        ResponseEntity<LoginResponse> response = authController.login(loginRequest);

        // Then
        assertNotNull(response);
        assertEquals("different-token", response.getBody().getAccessToken());
        assertEquals(7200L, response.getBody().getExpiresIn());
        assertEquals("admin", response.getBody().getUser().getUsername());
        assertEquals("SUPER_ADMIN", response.getBody().getUser().getRole());
    }
}