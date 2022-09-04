package com.fabiankevin.app.repository;

import com.fabiankevin.app.domain.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Customer create(Customer customer);
    Customer update(Customer customer, UUID customerId);
    Optional<Customer> findById(UUID id);
    Optional<Customer> findByMobileAndEmail(String mobileNumber, String email);
}
