package com.zcommerce.ecommerce.notification.kafka;

import com.zcommerce.ecommerce.notification.email.EmailService;
import com.zcommerce.ecommerce.notification.kafka.order.OrderConfirmation;
import com.zcommerce.ecommerce.notification.kafka.payment.PaymentConfirmation;
import com.zcommerce.ecommerce.notification.notification.Notification;
import com.zcommerce.ecommerce.notification.notification.NotificationRepository;
import com.zcommerce.ecommerce.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Consuming message from payment-topic: {}", paymentConfirmation);

        // Save the payment notification
        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();

        try {
            emailService.sendPaymentSuccessEmail(
                    paymentConfirmation.customerEmail(),
                    customerName,
                    paymentConfirmation.amount(),
                    paymentConfirmation.orderReference()
            );
        } catch (MessagingException e) {
            log.error("Failed to send payment confirmation email", e);
        }
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info("Consuming message from order-topic: {}", orderConfirmation);

        // Save the order notification
        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();

        try {
            emailService.sendOrderConfirmationEmail(
                    orderConfirmation.customer().email(),
                    customerName,
                    orderConfirmation.totalAmount(),
                    orderConfirmation.orderReference(),
                    orderConfirmation.products()
            );
        } catch (MessagingException e) {
            log.error("Failed to send order confirmation email", e);
        }
    }
}
