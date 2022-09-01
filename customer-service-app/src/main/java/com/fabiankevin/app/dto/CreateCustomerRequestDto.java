package com.fabiankevin.app.dto;

import com.fabiankevin.app.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequestDto {
    private UUID id;

    @NotEmpty
    @NotNull
    private String firstName;
    @NotEmpty
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate birthDate;
    @NotEmpty
    @NotNull
    private String mobileNumber;
    @NotEmpty(message = "email cannot be empty")
    @NotNull(message = "email is missing")
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