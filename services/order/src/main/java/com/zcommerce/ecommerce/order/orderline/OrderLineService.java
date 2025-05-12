package com.zcommerce.ecommerce.order.orderline;

import com.zcommerce.ecommerce.order.order.Order;
import com.zcommerce.ecommerce.order.order.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;
    private final OrderRepository orderRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {

        var orderLine =  mapper.toOrderLine(orderLineRequest);
        Order order = orderRepository.findById(orderLineRequest.orderId()).orElseThrow(() ->
                new EntityNotFoundException("order not found: " + orderLineRequest.orderId()));
        orderLine.setOrder(order);
        return repository.save(orderLine).getId();
    }



    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());

    }
}
