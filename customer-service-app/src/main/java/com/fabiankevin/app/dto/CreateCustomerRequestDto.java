package com.fabiankevin.app.dto;

import com.fabiankevin.app.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequestDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String mobileNumber;
    private String email;


    public static Customer toDomain(CreateCustomerRequestDto requestDto) {
        return Customer.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .mobileNumber(requestDto.getMobileNumber())
                .birthDate(requestDto.getBirthDate())
                .build();
    }
}