package com.StudentData.StudentData.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class StudentDto {
    private Long id;
    @NotNull(message = "Admission Number is required")
    @Min(value = 1, message = "Number must be greater than or equal to 1")
    private int AdmissionNo;
    @NotEmpty(message = "First Name cannot be blank")
    private String FirstName;
    @NotEmpty(message = "Surname cannot be blank")
    private String OtherName;
    @NotEmpty(message = "Gender cannot be blank")
    private String Gender;
    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Number must be greater than or equal to 1")
    private int Age;
    @NotNull(message = "Admission Year is required")
    @Min(value = 1, message = "Number must be greater than or equal to 1")
    private int Year;
    private LocalDateTime DateCreated;
    private LocalDateTime UpdatedOn;
}
