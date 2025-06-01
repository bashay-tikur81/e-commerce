package com.zcommerce.ecommerce.product.service;

import com.zcommerce.ecommerce.product.dao.ProductRepository;
import com.zcommerce.ecommerce.product.exception.ProductPurchaseException;
import com.zcommerce.ecommerce.product.model.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Integer createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more missing");
        }
        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0 ; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with id:" +
                        " "+productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity()- productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));

            
        }

        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return  productRepository.findById(productId).map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: "+productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }


    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

//    public ProductResponse updateProduct(Integer id, ProductRequest dto) {
//        Product product = productRepository.findById(id).orElseThrow();
//        Category category = productRepository.findById(dto.categoryId()).orElseThrow();
//
//        product.setName(dto.name());
//        product.setDescription(dto.description());
//        product.setAvailableQuantity(dto.availableQuantity());
//        product.setPrice(dto.price());
//        product.setCategory(category);
//
//        product = productRepository.save(product);
//        return ProductResponse.fromEntity(product);
//    }
}
