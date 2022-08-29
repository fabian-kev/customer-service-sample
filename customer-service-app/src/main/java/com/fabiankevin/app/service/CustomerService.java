package com.fabiankevin.app.service;

import com.fabiankevin.app.domain.Customer;

import java.util.UUID;

public interface CustomerService {

    Customer create(Customer customer);
    Customer getById(UUID id);
    Customer updateName(Customer customer, UUID customerId);
}
