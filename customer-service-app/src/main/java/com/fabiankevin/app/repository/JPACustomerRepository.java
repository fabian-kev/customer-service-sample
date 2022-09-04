package com.fabiankevin.app.repository;

import com.fabiankevin.app.domain.Customer;
import com.fabiankevin.jpa.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
@Primary
public class JPACustomerRepository implements CustomerRepository {
    private final com.fabiankevin.jpa.repository.CustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {
        log.info("Inserting Customer. lastName={}, firstName={}, mobileNumber={}, email={}", customer.getLastName(), customer.getFirstName(), customer.getMobileNumber(), customer.getEmail());
        Customer updatedCustomer = customer.toBuilder()
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .updatedDate(OffsetDateTime.now())
                .build();
        customerRepository.save(toEntity(updatedCustomer));
        return updatedCustomer;
    }

    @Override
    public Customer update(Customer customer, UUID customerId) {
        return null;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        return customerEntityOptional.map(entity -> toDomain(entity));
    }

    @Override
    public Optional<Customer> findByMobileAndEmail(String mobileNumber, String email) {
        return Optional.empty();
    }

    private CustomerEntity toEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .rrn(customer.getRrn())
                .birthDate(customer.getBirthDate())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .mobileNumber(customer.getMobileNumber())
                .updatedDate(customer.getUpdatedDate())
                .createdDate(customer.getCreatedDate())
                .build();
    }

    private Customer toDomain(CustomerEntity entity) {
        return Customer.builder()
                .id(entity.getId())
                .rrn(entity.getRrn())
                .birthDate(entity.getBirthDate())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .mobileNumber(entity.getMobileNumber())
                .updatedDate(entity.getUpdatedDate())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
