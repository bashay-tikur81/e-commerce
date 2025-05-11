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

//    @Transactional
//    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
//        var order =  mapper.toOrderLine(orderLineRequest);
//        return repository.save(order).getId();
//    }


//    @Transactional
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var orderLine = mapper.toOrderLine(orderLineRequest); // Convert request to OrderLine

        // Ensure Order is persisted before setting it in OrderLine
        Order order = orderRepository.findById(orderLineRequest.id())
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + orderLineRequest.id()));
//        entityManager.refresh(order);
        orderLine.setOrder(order); // Now setting a managed Order entity
        return repository.save(orderLine).getId();
    }



    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());

    }
}
