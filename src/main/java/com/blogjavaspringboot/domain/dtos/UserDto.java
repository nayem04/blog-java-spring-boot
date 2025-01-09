package com.blogjavaspringboot.domain.dtos;

import com.blogjavaspringboot.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDto extends BaseDto {
    @NotBlank(message = "First Name is required.")
    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @Email(message = "Email is invalid.")
    @JsonProperty(value = "email")
    private String email;

    @Pattern(message = "Invalid phone number.", regexp = "^(\\+8801|01)[3-9][0-9]{8}$")
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
