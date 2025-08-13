package com.example.demo.controller;

import com.example.demo.dto.ProdukDTO;
import com.example.demo.entity.MstKategoriProduk;
import com.example.demo.entity.MstProduk;
import com.example.demo.mapper.ProdukMapper;
import com.example.demo.service.ProdukService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdukControllerTest {

    @Mock
    private ProdukService produkService;

    @Mock
    private ProdukMapper produkMapper;

    @InjectMocks
    private ProdukController produkController;

    private MstProduk testProduk;
    private ProdukDTO testProdukDTO;
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

        testProdukDTO = new ProdukDTO();
        testProdukDTO.setId(1L);
        testProdukDTO.setIdKategoriProduk(1L);
        testProdukDTO.setNamaProduk("iPhone 15");
        testProdukDTO.setMerk("Apple");
        testProdukDTO.setModel("A2345");
        testProdukDTO.setWarna("Blue");
        testProdukDTO.setDeskripsiProduk("Latest iPhone model");
        testProdukDTO.setStok(50);
        testProdukDTO.setLinkImage("http://example.com/iphone15.jpg");
        testProdukDTO.setPathImage("/images/iphone15.jpg");
    }

    @Test
    void testGetAllProducts_ReturnsProductList() {
        // Given
        List<MstProduk> products = Arrays.asList(testProduk);
        when(produkService.findAll()).thenReturn(products);
        when(produkMapper.toDto(testProduk)).thenReturn(testProdukDTO);

        // When
        ResponseEntity<List<ProdukDTO>> response = produkController.getAllProducts();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("iPhone 15", response.getBody().get(0).getNamaProduk());
        assertEquals("Apple", response.getBody().get(0).getMerk());

        verify(produkService).findAll();
        verify(produkMapper).toDto(testProduk);
    }

    @Test
    void testGetAllProducts_EmptyList_ReturnsEmptyResponse() {
        // Given
        when(produkService.findAll()).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<List<ProdukDTO>> response = produkController.getAllProducts();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(produkService).findAll();
        verify(produkMapper, never()).toDto(any());
    }

    @Test
    void testGetProductById_ExistingProduct_ReturnsProduct() {
        // Given
        when(produkService.findById(1L)).thenReturn(Optional.of(testProduk));
        when(produkMapper.toDto(testProduk)).thenReturn(testProdukDTO);

        // When
        ResponseEntity<ProdukDTO> response = produkController.getProductById(1L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("iPhone 15", response.getBody().getNamaProduk());
        assertEquals("Apple", response.getBody().getMerk());

        verify(produkService).findById(1L);
        verify(produkMapper).toDto(testProduk);
    }

    @Test
    void testGetProductById_NonExistingProduct_ReturnsNotFound() {
        // Given
        when(produkService.findById(999L)).thenReturn(Optional.empty());

        // When
        ResponseEntity<ProdukDTO> response = produkController.getProductById(999L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(produkService).findById(999L);
        verify(produkMapper, never()).toDto(any());
    }

    @Test
    void testCreateProduct_ValidProduct_ReturnsCreatedProduct() {
        // Given
        ProdukDTO newProductDTO = new ProdukDTO();
        newProductDTO.setIdKategoriProduk(1L);
        newProductDTO.setNamaProduk("MacBook Pro");
        newProductDTO.setMerk("Apple");
        newProductDTO.setStok(25);

        MstProduk newProduct = MstProduk.builder()
                .kategoriProduk(testKategori)
                .namaProduk("MacBook Pro")
                .merk("Apple")
                .stok(25)
                .build();

        MstProduk savedProduct = MstProduk.builder()
                .id(2L)
                .kategoriProduk(testKategori)
                .namaProduk("MacBook Pro")
                .merk("Apple")
                .stok(25)
                .build();

        ProdukDTO savedProductDTO = new ProdukDTO();
        savedProductDTO.setId(2L);
        savedProductDTO.setIdKategoriProduk(1L);
        savedProductDTO.setNamaProduk("MacBook Pro");
        savedProductDTO.setMerk("Apple");
        savedProductDTO.setStok(25);

        when(produkMapper.toEntity(newProductDTO)).thenReturn(newProduct);
        when(produkService.save(newProduct)).thenReturn(savedProduct);
        when(produkMapper.toDto(savedProduct)).thenReturn(savedProductDTO);

        // When
        ResponseEntity<ProdukDTO> response = produkController.createProduct(newProductDTO);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2L, response.getBody().getId());
        assertEquals("MacBook Pro", response.getBody().getNamaProduk());
        assertEquals("Apple", response.getBody().getMerk());
        assertEquals(25, response.getBody().getStok());

        verify(produkMapper).toEntity(newProductDTO);
        verify(produkService).save(newProduct);
        verify(produkMapper).toDto(savedProduct);
    }

    @Test
    void testUpdateProduct_ValidProduct_ReturnsUpdatedProduct() {
        // Given
        ProdukDTO updateDTO = new ProdukDTO();
        updateDTO.setIdKategoriProduk(1L);
        updateDTO.setNamaProduk("iPhone 15 Pro");
        updateDTO.setMerk("Apple");
        updateDTO.setStok(75);

        MstProduk updateEntity = MstProduk.builder()
                .id(1L)
                .kategoriProduk(testKategori)
                .namaProduk("iPhone 15 Pro")
                .merk("Apple")
                .stok(75)
                .build();

        ProdukDTO updatedDTO = new ProdukDTO();
        updatedDTO.setId(1L);
        updatedDTO.setIdKategoriProduk(1L);
        updatedDTO.setNamaProduk("iPhone 15 Pro");
        updatedDTO.setMerk("Apple");
        updatedDTO.setStok(75);

        when(produkMapper.toEntity(updateDTO)).thenReturn(updateEntity);
        when(produkService.save(updateEntity)).thenReturn(updateEntity);
        when(produkMapper.toDto(updateEntity)).thenReturn(updatedDTO);

        // When
        ResponseEntity<ProdukDTO> response = produkController.updateProduct(1L, updateDTO);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("iPhone 15 Pro", response.getBody().getNamaProduk());
        assertEquals(75, response.getBody().getStok());

        // Verify that the ID was set on the DTO
        assertEquals(1L, updateDTO.getId());

        verify(produkMapper).toEntity(updateDTO);
        verify(produkService).save(updateEntity);
        verify(produkMapper).toDto(updateEntity);
    }

    @Test
    void testDeleteProduct_ValidId_ReturnsNoContent() {
        // Given
        doNothing().when(produkService).deleteById(1L);

        // When
        ResponseEntity<Void> response = produkController.deleteProduct(1L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(produkService).deleteById(1L);
    }

    @Test
    void testDeleteProduct_NonExistingId_ReturnsNoContent() {
        // Given
        doNothing().when(produkService).deleteById(999L);

        // When
        ResponseEntity<Void> response = produkController.deleteProduct(999L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(produkService).deleteById(999L);
    }
}