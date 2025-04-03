package com.javaweb.service.validator.customer;

import com.javaweb.service.validator.building.BuildingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = CustomerValidator.class)
@Target({ ElementType.TYPE }) // Adjusted to apply to the class level
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomerChecked {
    String message() default "Tạo mới customer bị lỗi";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
