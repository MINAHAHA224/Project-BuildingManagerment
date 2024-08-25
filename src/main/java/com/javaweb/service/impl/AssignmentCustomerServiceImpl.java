package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.service.AssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AssignmentCustomerServiceImpl implements AssignmentCustomerService {

    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;
    @Override
    public List<AssignmentCustomerEntity> getStaffAssignmentCustomers(CustomerEntity customerEntity) {
        return this.assignmentCustomerRepository.findAssignmentCustomerEntitiesByCustomerEntity(customerEntity);
    }

    @Override
    public void handleSave( AssignmentCustomerEntity assignmentCustomerEntity) {
        this.assignmentCustomerRepository.save(assignmentCustomerEntity);
    }

    @Override
    @Transactional
    public void handleDelete(UserEntity userEntity , CustomerEntity customerEntity) {
        this.assignmentCustomerRepository.deleteAssignmentCustomerEntityByUserEntityAndCustomerEntity(userEntity,customerEntity);
    }
}
