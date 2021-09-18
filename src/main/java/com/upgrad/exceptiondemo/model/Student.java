package com.upgrad.exceptiondemo.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class Student {

    private String studentId;
    @NotBlank(message = "Student Name cannot be blank")
    @Size(min = 5, max = 20, message = "Student Name length should be between 5 and 20 characters")
    private String studentName;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of Birth should be in yyyy-mm-dd format")
    private String studentDob;
    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}", message = "Mobile number is invalid")
    private String mobile;

}
