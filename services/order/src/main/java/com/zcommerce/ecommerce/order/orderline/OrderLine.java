package com.zcommerce.ecommerce.order.orderline;

import com.zcommerce.ecommerce.order.order.Order;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "customer_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
    private Integer productId;
    private double quantity;
}
