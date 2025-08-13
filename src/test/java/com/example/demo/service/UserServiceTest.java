package com.example.demo.service;

import com.example.demo.entity.MstAkses;
import com.example.demo.entity.MstUser;
import com.example.demo.repository.MstUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private MstUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private MstUser testUser;

    @BeforeEach
    void setUp() {
        MstAkses testAkses = MstAkses.builder()
                .id(1L)
                .namaAkses("ADMIN")
                .build();

        testUser = MstUser.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password123")
                .namaLengkap("Test User")
                .akses(testAkses)
                .build();
    }

    @Test
    void testLoadUserByUsername_UserExists_ReturnsUserDetails() {
        // Given
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));

        // When
        UserDetails userDetails = userService.loadUserByUsername("testuser");

        // Then
        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());

        verify(userRepository).findByUsername("testuser");
    }

    @Test
    void testLoadUserByUsername_UserNotFound_ThrowsException() {
        // Given
        when(userRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());

        // When & Then
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("nonexistent");
        });

        assertEquals("User not found: nonexistent", exception.getMessage());
        verify(userRepository).findByUsername("nonexistent");
    }

    @Test
    void testLoadUserByUsername_NullUsername_ThrowsException() {
        // When & Then
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(null);
        });

        assertEquals("User not found: null", exception.getMessage());
        verify(userRepository).findByUsername(null);
    }

    @Test
    void testLoadUserByUsername_EmptyUsername_ThrowsException() {
        // Given
        when(userRepository.findByUsername("")).thenReturn(Optional.empty());

        // When & Then
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("");
        });

        assertEquals("User not found: ", exception.getMessage());
        verify(userRepository).findByUsername("");
    }
}