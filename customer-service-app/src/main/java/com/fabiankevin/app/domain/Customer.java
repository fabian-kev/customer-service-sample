package com.fabiankevin.app.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {
    private UUID id;
    private String rrn;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String mobileNumber;
    private String email;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
}
