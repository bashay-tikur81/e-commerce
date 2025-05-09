package com.zcommerce.ecommerce.product.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @Positive(message = "quantity should be positive")
        double quantity
) {
}
