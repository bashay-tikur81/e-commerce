package com.zcommerce.ecommerce.order.orderline;

import com.zcommerce.ecommerce.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .productId(orderLineRequest.productId())
                .order(
                        Order.builder()
                                .id(orderLineRequest.id())
                                .build()
                )
                .quantity(orderLineRequest.quantity())
                .build();

    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity());
    }
}
