package com.zcommerce.ecommerce.order.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "product is mandatory")
        Integer productId,
        @Positive(message = "Quantity is mandatory and should be positive")
        double quantity
) {
}
