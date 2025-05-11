package com.zcommerce.ecommerce.order.kafka;

import com.zcommerce.ecommerce.order.order.PaymentMethod;
import com.zcommerce.ecommerce.order.product.PurchaseResponse;
import com.zcommerce.ecommerce.order.customer.CustomerResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
