package com.zcommerce.ecommerce.order.order;

import com.zcommerce.ecommerce.order.customer.CustomerClient;
import com.zcommerce.ecommerce.order.exception.BusinessException;
import com.zcommerce.ecommerce.order.kafka.OrderConfirmation;
import com.zcommerce.ecommerce.order.kafka.OrderProducer;
import com.zcommerce.ecommerce.order.orderline.OrderLineRequest;
import com.zcommerce.ecommerce.order.orderline.OrderLineService;
import com.zcommerce.ecommerce.order.payment.PaymentClient;
import com.zcommerce.ecommerce.order.payment.PaymentRequest;
import com.zcommerce.ecommerce.order.product.ProductClient;
import com.zcommerce.ecommerce.order.product.PurchaseRequest;
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
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;


    @Transactional
    public Integer createOrder(OrderRequest request) {
       var customer = this. customerClient.findCustomerById(request.customerId())
               .orElseThrow(()-> new BusinessException("Can't create order with the provided id "));

       var purchasedProducts = this.productClient.purchaseProducts(request.products());
       var order = this.repository.save(mapper.toOrder(request));

       for(PurchaseRequest purchaseRequest: request.products()){
           orderLineService.saveOrderLine(
                   new OrderLineRequest(
                   null,
                   order.getId(),
                   purchaseRequest.productId(),
                   purchaseRequest.quantity()
                   )
           );

       }
       var paymentRequest = new PaymentRequest(
               request.amount(),
               request.paymentMethod(),
               order.getId(),
               order.getReference(),
               customer
       );
       paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the" +
                        "provided id: %s", orderId)));
    }
}
