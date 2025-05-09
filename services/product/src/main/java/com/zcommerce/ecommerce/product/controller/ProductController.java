package com.zcommerce.ecommerce.product.controller;

import com.zcommerce.ecommerce.product.model.ProductPurchaseRequest;
import com.zcommerce.ecommerce.product.model.ProductPurchaseResponse;
import com.zcommerce.ecommerce.product.model.ProductRequest;
import com.zcommerce.ecommerce.product.model.ProductResponse;
import com.zcommerce.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purcahseProduct(
            @RequestBody List<ProductPurchaseRequest> request
    ){
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product_id") Integer productId
    ){
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
