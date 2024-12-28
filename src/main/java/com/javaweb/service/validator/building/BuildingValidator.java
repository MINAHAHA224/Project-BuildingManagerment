package com.javaweb.service.validator.building;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BuildingValidator implements  ConstraintValidator<BuildingChecked , BuildingDTO> {

    @Override
    public boolean isValid(BuildingDTO building, ConstraintValidatorContext context) {
        boolean valid = true;

        try {

            if (building.getName() == null || building.getName().isEmpty() ) {
                context.buildConstraintViolationWithTemplate("Tên tòa nhà không được để trống. !!!")
                        .addPropertyNode("name")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
            if (building.getStreet() == null || building.getStreet().isEmpty()) {
                context.buildConstraintViolationWithTemplate("Tên đường không được để trống. !!!")
                        .addPropertyNode("street")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
            if(building.getWard() == null || building.getWard().isEmpty()){
                context.buildConstraintViolationWithTemplate("Tên phường không được để trống. !!!")
                        .addPropertyNode("ward")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }

            if(building.getDistrict() == null || building.getDistrict().isEmpty()){
                context.buildConstraintViolationWithTemplate("Vui lòng chọn địa chỉ quận. !!!")
                        .addPropertyNode("district")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
            if(building.getNumberOfBasement() == null ){
                context.buildConstraintViolationWithTemplate("Số lượng tầng hầm không được để trống. !!!")
                        .addPropertyNode("numberOfBasement")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }

            if(building.getFloorArea() == null ){
                context.buildConstraintViolationWithTemplate("Diện tích sàn không được để trống. !!!")
                        .addPropertyNode("floorArea")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }

            if(building.getTypeCode().isEmpty()  ){
                context.buildConstraintViolationWithTemplate("Vui lòng chọn loại tòa nhà. !!!")
                        .addPropertyNode("typeCode")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
            if(building.getRentArea().isEmpty()  ){
                context.buildConstraintViolationWithTemplate("Vui lòng điền diện tích thuê. !!!")
                        .addPropertyNode("rentArea")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
            if(building.getManagerName() == null||building.getManagerName().isEmpty()  ){
                context.buildConstraintViolationWithTemplate("Vui lòng điền tên người quản lí. !!!")
                        .addPropertyNode("managerName")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
            if(building.getManagerPhone() == null || building.getManagerPhone().isEmpty()  ){
                context.buildConstraintViolationWithTemplate("Vui lòng điền số điện thoại quản lí. !!!")
                        .addPropertyNode("managerPhone")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            } else {
                String phone = building.getManagerPhone();

                // Kiểm tra số điện thoại có ít nhất 10 chữ số
                if (phone.length() < 10) {
                    context.buildConstraintViolationWithTemplate("Số điện thoại phải có ít nhất 10 chữ số.")
                            .addPropertyNode("managerPhone")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                }

                // Kiểm tra số điện thoại có đúng định dạng (chỉ chứa chữ số)
                if (!phone.matches("\\d+")) {
                    context.buildConstraintViolationWithTemplate("Số điện thoại chỉ được chứa các chữ số.")
                            .addPropertyNode("managerPhone")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    valid = false;
                }
            }



        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return valid;
    }
}
