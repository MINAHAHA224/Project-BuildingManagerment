package com.javaweb.api.admin;

import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.AssignmentCustomerService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AssignmentCustomerService assignmentCustomerService;



    @PostMapping("/api/customer/{id}/customer")
    public ResponseDTO getAssignmentCustomerShow (@PathVariable Long id){
        CustomerEntity customerEntity = this.customerService.getCustomerById(id);
        List<UserEntity> allStaffs = this.userService.getStaffModels(1 , "STAFF");
        List<AssignmentCustomerEntity> staffAssignmentCustomers = this.assignmentCustomerService.getStaffAssignmentCustomers(customerEntity);
        List<UserEntity> staffs = new ArrayList<>();
        for ( AssignmentCustomerEntity satffAssignmentCustomer : staffAssignmentCustomers ){
            staffs.add(satffAssignmentCustomer.getUserEntity());
        }

        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for ( UserEntity allStaff : allStaffs  ){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(allStaff.getId());
            staffResponseDTO.setFullName(allStaff.getFullName());

            if (staffs.contains(allStaff) ){
                staffResponseDTO.setChecked("checked");
            }else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @PutMapping("/api/customer/assignmentCustomer")
    public void getAssigmentCustomer (@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        CustomerEntity customerEntity = this.customerService.getCustomerById(assignmentCustomerDTO.getCustomerId());
        List<UserEntity> allStaffs = this.userService.getStaffModels(1 , "STAFF");
        List<AssignmentCustomerEntity> staffAssignmentCustomers = this.assignmentCustomerService.getStaffAssignmentCustomers(customerEntity);
        List<UserEntity> staffs = new ArrayList<>();
        for ( AssignmentCustomerEntity satffAssignmentCustomer : staffAssignmentCustomers ){
            staffs.add(satffAssignmentCustomer.getUserEntity());
        }

        List<Long> uncheck = new ArrayList<>();
        List<Long> check = new ArrayList<>();


        if( !assignmentCustomerDTO.getStaffs().isEmpty()){
            List<Long> staffDTO = assignmentCustomerDTO.getStaffs();
            for (UserEntity allStaff : allStaffs ){
                if ( staffDTO.contains(allStaff.getId())){
                    check.add(allStaff.getId());
                }
                else {
                    uncheck.add(allStaff.getId());
                }
            }

            for ( Long  id :  check ){
                UserEntity userEntity = this.userService.getUserById(id);
                if ( !staffs.contains(userEntity)){
                    AssignmentCustomerEntity assignmentCustomerEntity = new AssignmentCustomerEntity();
                    assignmentCustomerEntity.setUserEntity(userEntity);
                    assignmentCustomerEntity.setCustomerEntity(customerEntity);
                    this.assignmentCustomerService.handleSave(assignmentCustomerEntity);
                }

            }

            for ( Long  id :  uncheck  ){
                UserEntity userEntity = this.userService.getUserById(id);
                if ( staffs.contains(userEntity)) {

                    this.assignmentCustomerService.handleDelete( userEntity , customerEntity);
                }
            }
        }else {
            List<Long>  AllStaff = new ArrayList<>();
            for ( UserEntity sf : allStaffs ){
                AllStaff.add(sf.getId());
            }
            for ( Long  id :  AllStaff  ){
                UserEntity userEntity = this.userService.getUserById(id);
                if ( staffs.contains(userEntity)) {

                    this.assignmentCustomerService.handleDelete( userEntity , customerEntity);
                }
            }
        }
    }


    @PutMapping("/api/customer/create")
    public  void getCreateCustomer (@RequestBody CustomerDTO customerDTO){

        if (SecurityUtils.getPrincipal().getId() != null) {
            customerDTO.setManagementStaff(SecurityUtils.getPrincipal().getFullName());

        }

        this.customerService.handleSaveCustomer(customerDTO);

    }

    @PutMapping("/api/customer/update")
    public  void getUpdateCustomer (@RequestBody CustomerDTO customerDTO){

        if (SecurityUtils.getPrincipal().getId() != null) {
            customerDTO.setManagementStaff(SecurityUtils.getPrincipal().getFullName());

        }

        this.customerService.handleUpdateCustomer(customerDTO);

    }


    @DeleteMapping("/api/customer/{customerId}")
    public void getDeleteCustomer (@PathVariable List<Long> customerId){

        this.customerService.handleDeleteCustomer(customerId);
    }

    @PutMapping ("/api/customer/transaction")
    public void getTransaction (@RequestBody TransactionDTO transactionDTO){
        if (transactionDTO.getNote()  != null ){
            transactionDTO.setManagementStaff(SecurityUtils.getPrincipal().getFullName());
        }

        this.transactionService.handleSaveTransaction(transactionDTO );

    }

    @DeleteMapping ("/api/customer/transaction-{id}")
    public void getDeleteTransaction( @PathVariable Long id ){
        this.transactionService.handleDeleteTransaction(id);
    }

}
