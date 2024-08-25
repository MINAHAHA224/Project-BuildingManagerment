package com.javaweb.repository;

import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.custom.AssignmentCustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity , Long > , AssignmentCustomerRepositoryCustom {
    void deleteAssignmentCustomerEntityByUserEntityAndCustomerEntity(UserEntity userEntity , CustomerEntity customerEntity);


}
