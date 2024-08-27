package com.javaweb.model.dto;


import com.javaweb.service.validator.RegisterChecked;

import javax.validation.constraints.NotNull;

@RegisterChecked
public class RegisterDTO extends AbstractDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String passWord;
    @NotNull

    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

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
}
