package com.zcommerce.ecommerce.order.payment;

import com.zcommerce.ecommerce.order.order.PaymentMethod;
import com.zcommerce.ecommerce.order.customer.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
