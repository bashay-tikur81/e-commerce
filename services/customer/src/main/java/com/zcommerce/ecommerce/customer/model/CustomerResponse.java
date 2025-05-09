package com.zcommerce.ecommerce.customer.model;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {

}
