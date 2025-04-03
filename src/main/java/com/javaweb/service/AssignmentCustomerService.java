package com.javaweb.service;

import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssignmentCustomerService {

    List<AssignmentCustomerEntity> getStaffAssignmentCustomers (CustomerEntity customerEntity );

    ResponseEntity<String> handleSaveAssignmentCustomer(AssignmentCustomerEntity assignmentCustomerEntity);

    ResponseEntity<String> handleDeleteAssignmentCustomer (Long id);
}
