package org.icet.crm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.icet.crm.util.DepartmentType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100, message = "user name should have at least 2 characters")
    private String name;

    @Email(message = "Email should be valid")
    @NotEmpty
    private String email;

    private DepartmentType department;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
