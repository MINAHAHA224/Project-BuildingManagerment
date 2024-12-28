package com.javaweb.service.validator.building;

import com.javaweb.service.validator.RegisterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = BuildingValidator.class)
@Target({ ElementType.TYPE }) // Adjusted to apply to the class level
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BuildingChecked {
    String message() default "Tạo mới building bị lỗi";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
