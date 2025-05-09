package com.zcommerce.ecommerce.customer.controller;

import com.zcommerce.ecommerce.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "First Name should not be null")
        String firstName,
        @NotNull(message = "Last Name should not be null")
        String lastName,
        @NotNull(message = "Email should not be null")
        @Email(message = "Email is not a valid email address")
        String email,
        Address address
) {
}
