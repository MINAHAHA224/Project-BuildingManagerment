package com.javaweb.repository.custom;


import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentCustomerRepositoryCustom {

    List<AssignmentCustomerEntity> findAssignmentCustomerEntitiesByCustomerEntity (CustomerEntity customerEntity);


}
