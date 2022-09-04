package com.fabiankevin.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Entity
public class CustomerEntity {
    @Id
    private UUID id;
    @Column(name = "request_reference_id")
    private String rrn;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String mobileNumber;
    private String email;
    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;
}
