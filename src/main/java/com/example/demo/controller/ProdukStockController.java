package com.example.demo.controller;

import com.example.demo.entity.MstProduk;
import com.example.demo.service.ProdukStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products/{produkId}/stock-adjust")
@RequiredArgsConstructor
public class ProdukStockController {
    private final ProdukStockService produkStockService;

    @PutMapping
    public ResponseEntity<MstProduk> adjustStock(@PathVariable Long produkId, @RequestBody Map<String, Object> body) {
        int delta = (int) body.getOrDefault("delta", 0);
        String reason = (String) body.getOrDefault("reason", "");
        Long userId = body.get("userId") != null ? Long.valueOf(body.get("userId").toString()) : null;
        return ResponseEntity.ok(produkStockService.adjustStock(produkId, delta, reason, userId));
    }
}
