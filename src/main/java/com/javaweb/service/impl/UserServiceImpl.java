package com.javaweb.service.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.UserConverter;
import com.javaweb.entity.UserRoleEntity;
import com.javaweb.model.dto.PasswordDTO;
import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.UserRoleRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements com.javaweb.service.UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRoleRepository userRoleRepository;






    @Override
    public Map< Long ,String> getStaff(Integer status, String role) {
        List<UserEntity> staffs = this.userRepository.findByStatusAndUserRoleEntities_Role_Code(status ,role);
        Map< Long ,String> results = new HashMap<>();
        for(UserEntity staff : staffs){
            results.put(staff.getId() , staff.getFullName());
        }
        return results;
    }

    @Override
    public List<UserEntity> getStaffModels(Integer status, String role) {
        return this.userRepository.findByStatusAndUserRoleEntities_Role_Code(status,role);
    }

    @Override
    public UserDTO findOneByUserNameAndStatus(String name, int status) {
        return userConverter.convertToDto(userRepository.findOneByUserNameAndStatus(name, status));
    }

    @Override
    public List<UserDTO> getUsers(String searchValue, Pageable pageable) {
        Page<UserEntity> users = null;
        if (StringUtils.isNotBlank(searchValue)) {
            users = userRepository.findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0, pageable);
        } else {
            users = userRepository.findByStatusNot(0, pageable);
        }
        List<UserEntity> newsEntities = users.getContent();
        List<UserDTO> result = new ArrayList<>();
        for (UserEntity userEntity : newsEntities) {
            UserDTO userDTO = userConverter.convertToDto(userEntity);
            userDTO.setRoleCode(userEntity.getUserRoleEntities().get(0).getRole().getCode());
            result.add(userDTO);
        }
        return result;
    }



    @Override
    public List<UserDTO> getAllUsers(Pageable pageable) {
        List<UserEntity> userEntities = userRepository.getAllUsers(pageable);
        List<UserDTO> results = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = userConverter.convertToDto(userEntity);
            userDTO.setRoleCode(userEntity.getUserRoleEntities().get(0).getRole().getCode());
            results.add(userDTO);
        }
        return results;
    }

    @Override
    public int countTotalItems() {
        return userRepository.countTotalItem();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public Boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public void handleSaveRegister(RegisterDTO registerDTO) {
        if( registerDTO.getPassWord() != null ){
            UserEntity newUser = new UserEntity();
            List<RoleEntity> roles = new ArrayList<>();
            RoleEntity role = this.roleRepository.findOneByCode("USER");
            List<String> firstName = Arrays.asList(registerDTO.getFirstName().toLowerCase(Locale.ROOT).split(" "));
            List<String> lastName = Arrays.asList(registerDTO.getLastName().toLowerCase(Locale.ROOT).split(" "));
            String firstName2 = firstName.stream().map(it -> it).collect(Collectors.joining(" ")) ;
            String lastName2 = lastName.stream().map(it->it).collect(Collectors.joining(" "));
            String fullName = firstName2 + " " + lastName2;
            String passWord = this.passwordEncoder.encode(registerDTO.getConfirmPassword());
            roles.add(role);
            newUser.setStatus(1);

            newUser.setUserName(registerDTO.getEmail());
            newUser.setPassword(passWord);
            newUser.setFullName(fullName);
            newUser.setEmail(registerDTO.getEmail());
            this.userRepository.save(newUser);


            UserRoleEntity userRoleEntity = new UserRoleEntity();
            for ( RoleEntity oneRole : roles ){
                userRoleEntity.setUser(newUser);
                userRoleEntity.setRole(oneRole);
                this.userRoleRepository.save(userRoleEntity);
            }


        }

    }


    @Override
    public int getTotalItems(String searchValue) {
        int totalItem = 0;
        if (StringUtils.isNotBlank(searchValue)) {
            totalItem = (int) userRepository.countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0);
        } else {
            totalItem = (int) userRepository.countByStatusNot(0);
        }
        return totalItem;
    }

    @Override
    public UserDTO findOneByUserName(String userName) {
        UserEntity userEntity = userRepository.findOneByUserName(userName);
        UserDTO userDTO = userConverter.convertToDto(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO findUserById(long id) {
        UserEntity entity = userRepository.findById(id).get();
        List<UserRoleEntity> userRoleEntities = this.userRoleRepository.findUserRoleEntitiesByUser(entity);
        UserDTO dto = userConverter.convertToDto(entity);
        userRoleEntities.forEach(item -> {
            dto.setRoleCode(item.getRole().getCode());
        });
        return dto;
    }

    @Override
    @Transactional
    public UserDTO insert(UserDTO newUser) {
        RoleEntity role = roleRepository.findOneByCode(newUser.getRoleCode());
        UserEntity userEntity = userConverter.convertToEntity(newUser);
        userEntity.setStatus(1);
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));


        // save user first
        UserEntity user = userRepository.save(userEntity);
        UserDTO userDTO = userConverter.convertToDto(user);
        // then save user_role
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(user);
        userRoleEntity.setRole(role);
        this.userRoleRepository.save(userRoleEntity);

        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO updateUser) {
        RoleEntity role = roleRepository.findOneByCode(updateUser.getRoleCode());
        UserEntity oldUser = userRepository.findById(id).get();
        UserEntity userEntity = userConverter.convertToEntity(updateUser);
        userEntity.setUserName(oldUser.getUserName());
        userEntity.setStatus(oldUser.getStatus());
        userEntity.setPassword(oldUser.getPassword());
        // save user first
        UserEntity user = userRepository.save(userEntity);
        UserDTO userDTO = userConverter.convertToDto(user);
        // then save user_role
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(user);
        userRoleEntity.setRole(role);
        this.userRoleRepository.save(userRoleEntity);


        return userDTO;
    }

    @Override
    @Transactional
    public void updatePassword(long id, PasswordDTO passwordDTO) throws MyException {
        UserEntity user = userRepository.findById(id).get();
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())
                && passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new MyException(SystemConstant.CHANGE_PASSWORD_FAIL);
        }
    }

    @Override
    @Transactional
    public UserDTO resetPassword(long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO updateProfileOfUser(String username, UserDTO updateUser) {
        UserEntity oldUser = userRepository.findOneByUserName(username);
        oldUser.setFullName(updateUser.getFullName());
        return userConverter.convertToDto(userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (Long item : ids) {
            UserEntity userEntity = userRepository.findById(item).get();
            userEntity.setStatus(0);
            userRepository.save(userEntity);
        }
    }



}
