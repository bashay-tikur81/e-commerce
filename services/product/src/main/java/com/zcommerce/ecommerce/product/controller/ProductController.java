package com.zcommerce.ecommerce.product.controller;

import com.zcommerce.ecommerce.product.model.*;
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
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purcahseProduct(
            @RequestBody List<ProductPurchaseRequest> request
    ){
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product_id") Integer productId
    ){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
        List<Product> products = productService.searchProducts(query);
        System.out.println("search with query: "+query);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductResponse> updateProduct(
//            @PathVariable Integer id,
//            @RequestBody ProductRequest dto)
//    {
//        ProductResponse updatedProduct = productService.updateProduct(id, dto);
//        return ResponseEntity.ok(updatedProduct);
//    }
}
