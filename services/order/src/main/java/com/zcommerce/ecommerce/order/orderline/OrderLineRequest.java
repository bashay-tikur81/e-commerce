package com.zcommerce.ecommerce.order.orderline;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        @GeneratedValue
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}
