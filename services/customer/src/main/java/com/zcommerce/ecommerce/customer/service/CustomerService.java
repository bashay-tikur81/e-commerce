package com.zcommerce.ecommerce.customer.service;

import ch.qos.logback.core.util.StringUtil;
import com.zcommerce.ecommerce.customer.controller.CustomerRequest;
import com.zcommerce.ecommerce.customer.deo.CustomerRepository;
import com.zcommerce.ecommerce.customer.exception.CustomerNotFoundException;
import com.zcommerce.ecommerce.customer.model.Customer;
import com.zcommerce.ecommerce.customer.model.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    @Autowired
    private CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id()).orElseThrow(()->new CustomerNotFoundException(
               format("Can't not update customer. No customer found with the id: %s", request.id())));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtil.notNullNorEmpty(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtil.notNullNorEmpty(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtil.notNullNorEmpty(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }

    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(format(
                        "No customer found with the provided id: %s", customerId)));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
