package com.fabiankevin.app.dto;

import com.fabiankevin.app.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String mobileNumber;
    private String email;
    private OffsetDateTime createdDate;


    public static CustomerResponseDto from(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .birthDate(customer.getBirthDate())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .createdDate(customer.getCreatedDate())
                .build();
    }
}
