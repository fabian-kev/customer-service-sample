package com.fabiankevin.app.controller;

import com.fabiankevin.app.domain.Customer;
import com.fabiankevin.app.dto.CreateCustomerRequestDto;
import com.fabiankevin.app.dto.CustomerResponseDto;
import com.fabiankevin.app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final static String HEADER_RRN = "request-reference-number";
    private final CustomerService customerService;


    @PostMapping
    public CustomerResponseDto create(
            @RequestHeader(HEADER_RRN) String rrn,
            @Validated @RequestBody CreateCustomerRequestDto request) {
        try {
            MDC.put("RRN", rrn);
            log.debug("Receive request={}", request);
            Customer customer = CreateCustomerRequestDto
                    .toDomain(request);
            customer = customer.toBuilder()
                    .rrn(rrn)
                    .build();
            customer = customerService.create(customer);
            log.debug("Created Customer={}",customer);
            return CustomerResponseDto.from(customer);
        } finally {
            MDC.clear();
        }
    }

    @GetMapping("/{customerId}")
    public CustomerResponseDto getById(@PathVariable("customerId") UUID customerId){
        return CustomerResponseDto.from(customerService.getById(customerId));
    }

    @PatchMapping("/{customerId}")
    public CustomerResponseDto patchCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer){
        return CustomerResponseDto.from(customerService.updateName(customer, customerId));
    }

}
