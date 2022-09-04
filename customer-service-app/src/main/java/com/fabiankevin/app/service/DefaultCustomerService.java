package com.fabiankevin.app.service;

import com.fabiankevin.app.domain.Customer;
import com.fabiankevin.app.exception.CustomerExistsException;
import com.fabiankevin.app.exception.CustomerNotFoundException;
import com.fabiankevin.app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {

        customerRepository.findByMobileAndEmail(customer.getMobileNumber(), customer.getEmail())
                .ifPresent(c -> { throw new CustomerExistsException(String.format("Customer with mobileNumber=%s and email=%s already exists", customer.getMobileNumber(), customer.getEmail()));
                });
        log.info("Inserting customer mobileNumber={}, email={}, lastName={}, firstName={}, ",
                customer.getMobileNumber(),
                customer.getEmail(),
                customer.getFirstName(),
                customer.getLastName());
        return customerRepository.create(customer);
    }

    @Override
    public Customer getById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(HttpStatus.NOT_FOUND, String.format("Customer with id=%s not found", id), "CUSCUS404"));
    }

    @Override
    public Customer updateName(Customer customer, UUID customerId) {
        return customerRepository.update(customer, customerId);
    }
}
