package com.zcommerce.ecommerce.customer.deo;

import com.zcommerce.ecommerce.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
