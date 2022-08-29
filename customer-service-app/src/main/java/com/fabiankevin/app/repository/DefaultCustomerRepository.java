package com.fabiankevin.app.repository;

import com.fabiankevin.app.domain.Customer;
import com.fabiankevin.data.tables.records.CustomersRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.fabiankevin.data.Tables.CUSTOMERS;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DefaultCustomerRepository implements CustomerRepository {
    private final DSLContext context;
    @Override
    public Customer create(Customer customer) {
        OffsetDateTime now = OffsetDateTime.now();
        CustomersRecord customersRecord = context.newRecord(CUSTOMERS);
        customersRecord.setId(UUID.randomUUID());
        customersRecord.setRequestReferenceId(customer.getRrn());
        customersRecord.setFirstName(customer.getFirstName());
        customersRecord.setLastName(customer.getLastName());
        customersRecord.setEmail(customer.getEmail());
        customersRecord.setMobileNumber(customer.getMobileNumber());
        customersRecord.setBirthDate(customer.getBirthDate());
        customersRecord.setCreatedDate(now);
        customersRecord.setUpdatedDate(now);
        context.executeInsert(customersRecord);
        return customer.toBuilder()
                .id(customersRecord.getId())
                .createdDate(customersRecord.getCreatedDate())
                .updatedDate(customersRecord.getUpdatedDate())
                .build();
    }

    @Override
    public Customer update(Customer customer, UUID customerId) {
        CustomersRecord customersRecord = context.update(CUSTOMERS)
                .set(CUSTOMERS.LAST_NAME, customer.getLastName())
                .set(CUSTOMERS.FIRST_NAME, customer.getFirstName())
                .where(CUSTOMERS.ID.eq(customerId))
                .returning()
                .fetchOne();

        return toDomain(customersRecord);
    }


    @Override
    public Optional<Customer> findById(UUID id) {
        return Optional.ofNullable(context.selectFrom(CUSTOMERS)
                .where(CUSTOMERS.ID.eq(id))
                .fetchOne(DefaultCustomerRepository::toDomain));
    }

    @Override
    public Optional<Customer> findByMobileAndEmail(String mobileNumber, String email) {
        return Optional.ofNullable(context.selectFrom(CUSTOMERS)
                .where(CUSTOMERS.MOBILE_NUMBER.eq(mobileNumber).and(CUSTOMERS.EMAIL.eq(email)))
                .fetchOne(DefaultCustomerRepository::toDomain));
    }


    private static Customer toDomain(CustomersRecord customersRecord) {
        return Customer.builder()
                .id(customersRecord.getId())
                .rrn(customersRecord.getRequestReferenceId())
                .email(customersRecord.getEmail())
                .mobileNumber(customersRecord.getMobileNumber())
                .lastName(customersRecord.getLastName())
                .firstName(customersRecord.getFirstName())
                .birthDate(customersRecord.getBirthDate())
                .createdDate(customersRecord.getCreatedDate())
                .build();
    }
}
