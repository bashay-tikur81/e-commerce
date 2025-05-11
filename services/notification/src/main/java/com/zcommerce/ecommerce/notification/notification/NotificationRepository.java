package com.zcommerce.ecommerce.notification.notification;

import com.zcommerce.ecommerce.notification.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
