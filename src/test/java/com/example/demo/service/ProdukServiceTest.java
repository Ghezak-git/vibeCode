package com.example.demo.service;

import com.example.demo.entity.MstKategoriProduk;
import com.example.demo.entity.MstProduk;
import com.example.demo.repository.MstProdukRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdukServiceTest {

    @Mock
    private MstProdukRepository produkRepository;

    @InjectMocks
    private ProdukService produkService;

    private MstProduk testProduk;
    private MstKategoriProduk testKategori;

    @BeforeEach
    void setUp() {
        testKategori = MstKategoriProduk.builder()
                .id(1L)
                .namaKategori("Electronics")
                .build();

        testProduk = MstProduk.builder()
                .id(1L)
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15")
                .merk("Apple")
                .model("A2345")
                .warna("Blue")
                .deskripsiProduk("Latest iPhone model")
                .stok(50)
                .linkImage("http://example.com/iphone15.jpg")
                .pathImage("/images/iphone15.jpg")
                .build();
    }

    @Test
    void testFindAll_ReturnsAllProducts() {
        // Given
        MstProduk product2 = MstProduk.builder()
                .id(2L)
                .kategoriProduk(testKategori)
                .namaProduk("Samsung Galaxy")
                .merk("Samsung")
                .stok(30)
                .build();

        List<MstProduk> expectedProducts = Arrays.asList(testProduk, product2);
        when(produkRepository.findAll()).thenReturn(expectedProducts);

        // When
        List<MstProduk> actualProducts = produkService.findAll();

        // Then
        assertNotNull(actualProducts);
        assertEquals(2, actualProducts.size());
        assertEquals(expectedProducts, actualProducts);
        assertTrue(actualProducts.contains(testProduk));
        assertTrue(actualProducts.contains(product2));

        verify(produkRepository).findAll();
    }

    @Test
    void testFindAll_ReturnsEmptyList() {
        // Given
        when(produkRepository.findAll()).thenReturn(Arrays.asList());

        // When
        List<MstProduk> actualProducts = produkService.findAll();

        // Then
        assertNotNull(actualProducts);
        assertTrue(actualProducts.isEmpty());

        verify(produkRepository).findAll();
    }

    @Test
    void testFindById_ExistingProduct_ReturnsProduct() {
        // Given
        when(produkRepository.findById(1L)).thenReturn(Optional.of(testProduk));

        // When
        Optional<MstProduk> result = produkService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(testProduk, result.get());
        assertEquals("iPhone 15", result.get().getNamaProduk());
        assertEquals("Apple", result.get().getMerk());

        verify(produkRepository).findById(1L);
    }

    @Test
    void testFindById_NonExistingProduct_ReturnsEmpty() {
        // Given
        when(produkRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<MstProduk> result = produkService.findById(999L);

        // Then
        assertFalse(result.isPresent());

        verify(produkRepository).findById(999L);
    }

    @Test
    void testSave_NewProduct_ReturnsSavedProduct() {
        // Given
        MstProduk newProduk = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("MacBook Pro")
                .merk("Apple")
                .stok(25)
                .build();

        MstProduk savedProduk = MstProduk.builder()
                .id(3L)
                .kategoriProduk(testKategori)
                .namaProduk("MacBook Pro")
                .merk("Apple")
                .stok(25)
                .build();

        when(produkRepository.save(newProduk)).thenReturn(savedProduk);

        // When
        MstProduk result = produkService.save(newProduk);

        // Then
        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("MacBook Pro", result.getNamaProduk());
        assertEquals("Apple", result.getMerk());
        assertEquals(25, result.getStok());

        verify(produkRepository).save(newProduk);
    }

    @Test
    void testSave_UpdateExistingProduct_ReturnsSavedProduct() {
        // Given
        testProduk.setStok(75);
        testProduk.setDeskripsiProduk("Updated description");

        when(produkRepository.save(testProduk)).thenReturn(testProduk);

        // When
        MstProduk result = produkService.save(testProduk);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(75, result.getStok());
        assertEquals("Updated description", result.getDeskripsiProduk());

        verify(produkRepository).save(testProduk);
    }

    @Test
    void testDeleteById_ValidId_CallsRepositoryDelete() {
        // Given
        Long productId = 1L;

        // When
        produkService.deleteById(productId);

        // Then
        verify(produkRepository).deleteById(productId);
    }

    @Test
    void testDeleteById_CallsRepositoryDeleteOnce() {
        // Given
        Long productId = 5L;

        // When
        produkService.deleteById(productId);

        // Then
        verify(produkRepository, times(1)).deleteById(productId);
        verifyNoMoreInteractions(produkRepository);
    }
}