package com.javaweb.service.validator;

import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked , RegisterDTO> {

    @Autowired
    private  UserService userService;
    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        try {

                if (!user.getPassWord().equals(user.getConfirmPassword())) {
                    context.buildConstraintViolationWithTemplate("Passwords nhập lại không khớp")
                            .addPropertyNode("confirmPassword")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                } else if (user.getConfirmPassword()==null || user.getConfirmPassword().isEmpty()) {
                    context.buildConstraintViolationWithTemplate("confirmPassword không được để trống")
                            .addPropertyNode("confirmPassword")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                }

                // Additional validations can be added here
                // check email exist
                if (this.userService.checkEmailExist(user.getEmail())) {
                    context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                            .addPropertyNode("email")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                } else if (user.getEmail() == null ||user.getEmail().isEmpty() ) {
                    context.buildConstraintViolationWithTemplate("Email không được để trống")
                            .addPropertyNode("email")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;

                }
                // check value null firstname , email , password
                if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
                    context.buildConstraintViolationWithTemplate("firstName không được để trống")
                            .addPropertyNode("firstName")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                }

                if (user.getLastName() == null ||user.getLastName().isEmpty() ) {
                    context.buildConstraintViolationWithTemplate("lastName không được để trống")
                            .addPropertyNode("lastName")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                }

                if (user.getPassWord() == null || user.getPassWord().isEmpty() ) {
                    context.buildConstraintViolationWithTemplate("Password không được để trống")
                            .addPropertyNode("passWord")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                }

            // Check if password fields match



        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return valid;
    }
}
