package sk.ukf.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 1, max = 255, message = "First name must be between 1 and 255 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 1, max = 255, message = "Last name must be between 1 and 255 characters")
    private String lastName;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Email cannot be blank")
    @Size(max = 254, message = "Email cannot exceed 254 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone cannot be blank")
    @Size(min = 10, max = 15, message = "Phone must be between 10 and 15 characters")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Invalid phone format (digits only, optional + at start)")
    private String phone;

    @NotBlank(message = "Job title cannot be blank")
    @Size(min = 1, max = 255, message = "Job title must be between 1 and 255 characters")
    private String jobTitle;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", message = "Salary cannot be negative")
    private Double salary;

    @NotNull(message = "Full-time status is required")
    private Boolean fullTime;
}