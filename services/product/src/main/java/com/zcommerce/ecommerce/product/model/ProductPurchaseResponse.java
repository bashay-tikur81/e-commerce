package com.zcommerce.ecommerce.product.model;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity,
        String imageUrl
) {
}
