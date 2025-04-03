package com.javaweb.converter;

import com.javaweb.entity.UserRoleEntity;
import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.model.dto.RoleDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto (UserEntity entity){
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (UserRoleEntity userRoleEntity : entity.getUserRoleEntities()){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setCode(userRoleEntity.getRole().getCode());
            roleDTO.setName(userRoleEntity.getRole().getName());
            roleDTOS.add(roleDTO);
        }

        result.setRoles(roleDTOS);
        return result;
    }

    public UserEntity convertToEntity (UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }


    
}
