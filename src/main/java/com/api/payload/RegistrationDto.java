package com.api.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationDto {
    @NotBlank(message = "Name is required")
    @Size(min=2,message = "Name two short. should be more that two characters")
    private String name;
    @Email
    private String email;
    @Size(max = 10, min = 10, message = "Mobile No should be 10 characters")
    private String mobile;

}