package com.fabiankevin.app.service;


import com.fabiankevin.app.domain.Customer;
import com.fabiankevin.app.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private DefaultCustomerService customerService;

    @Test
    void createUser_happyPath(){
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .birthDate(LocalDate.now())
                .firstName("test-fn")
                .lastName("test-ln")
                .mobileNumber("0922222")
                .email("email@test.com")
                .rrn(UUID.randomUUID().toString())
                .build();
        Mockito.when(customerRepository.create(any()))
                .thenReturn(customer);
        Customer customerResult = customerService.create(customer);

        assertEquals(customer, customerResult, "customer object should be equal");
    }
}
