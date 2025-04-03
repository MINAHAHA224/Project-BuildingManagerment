package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.service.AssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> handleSaveAssignmentCustomer( AssignmentCustomerEntity assignmentCustomerEntity) {
        try {
            this.assignmentCustomerRepository.save(assignmentCustomerEntity);
            return ResponseEntity.ok().body("Save thành công");
        }catch (RuntimeException e){
            System.out.println("--ER : Lỗi không save duoc Assignment Customer");
            return ResponseEntity.badRequest().body("Lỗi không save duoc Assignment Customer");
        }


    }

    @Override
    @Transactional
    public ResponseEntity<String> handleDeleteAssignmentCustomer(Long id) {
        try {
            this.assignmentCustomerRepository.deleteAssignmentCustomerEntityByCustomer_Id(id);
            return ResponseEntity.ok().body("Xóa thành công AssignmentCustomer");
        }catch (RuntimeException e){
            System.out.println("--ER : lỗi xóa Assignment customer " + e.getMessage());
            return  ResponseEntity.badRequest().body("lỗi xóa Assignment customer");
        }

    }
}
