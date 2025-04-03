package com.javaweb.service.validator.customer;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.service.validator.building.BuildingChecked;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerValidator implements ConstraintValidator<CustomerChecked, CustomerDTO> {
    @Override
    public boolean isValid(CustomerDTO customerDTO, ConstraintValidatorContext context) {
        boolean valid = true;
        try {

            if (customerDTO.getName() == null || customerDTO.getName().isEmpty() ) {
                context.buildConstraintViolationWithTemplate("Tên khách hàng không được để trống. !!!")
                        .addPropertyNode("name")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
            if(customerDTO.getCustomerPhone() == null || customerDTO.getCustomerPhone().isEmpty()  ){
                context.buildConstraintViolationWithTemplate("Vui lòng điền số điện thoại khách hàng. !!!")
                        .addPropertyNode("customerPhone")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            } else {
                String phone = customerDTO.getCustomerPhone();

                // Kiểm tra số điện thoại có ít nhất 10 chữ số
                if (phone.length() < 10) {
                    context.buildConstraintViolationWithTemplate("Số điện thoại phải có ít nhất 10 chữ số.")
                            .addPropertyNode("customerPhone")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                }

                // Kiểm tra số điện thoại có đúng định dạng (chỉ chứa chữ số)
                if (!phone.matches("\\d+")) {
                    context.buildConstraintViolationWithTemplate("Số điện thoại chỉ được chứa các chữ số.")
                            .addPropertyNode("customerPhone")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                }
            }
            if (customerDTO.getEmail() == null || customerDTO.getEmail().isEmpty() ) {
                context.buildConstraintViolationWithTemplate("Email khách hàng không được để trống. !!!")
                        .addPropertyNode("email")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
            if (customerDTO.getDemand() == null || customerDTO.getDemand().isEmpty() ) {
                context.buildConstraintViolationWithTemplate("Nhu cầu khách hàng không được để trống. !!!")
                        .addPropertyNode("demand")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
            if (customerDTO.getStatus() == null || customerDTO.getStatus().isEmpty() ) {
                context.buildConstraintViolationWithTemplate("Tình trạng khách hàng không được để trống. !!!")
                        .addPropertyNode("status")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }




        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return valid;
    }
}
