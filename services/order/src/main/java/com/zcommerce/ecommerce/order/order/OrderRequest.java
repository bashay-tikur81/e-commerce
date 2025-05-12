package com.zcommerce.ecommerce.order.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zcommerce.ecommerce.order.product.PurchaseRequest;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderRequest(
        @GeneratedValue
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        String customerId,
        @NotEmpty(message = "At least needs one product")
        List<PurchaseRequest> products
) {
}
