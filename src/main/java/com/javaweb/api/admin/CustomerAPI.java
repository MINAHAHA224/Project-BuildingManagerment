package com.javaweb.api.admin;

import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.AssignCustomerType;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            staffs.add(satffAssignmentCustomer.getUser());
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
        // logic code moi
        List<AssignmentCustomerEntity> staffAssignmentCustomers = this.assignmentCustomerService.getStaffAssignmentCustomers(customerEntity);
        // handle Delete  Assignment Customer
        ResponseEntity<String> handleDeleteAssignmentCustomer =  this.assignmentCustomerService.handleDeleteAssignmentCustomer( customerEntity.getId());
        if (handleDeleteAssignmentCustomer.getStatusCodeValue() == 200 ){

                // handleSave
                for ( Long idStaff : assignmentCustomerDTO.getStaffs() ){
                    AssignmentCustomerEntity assignmentCustomerEntity = new AssignmentCustomerEntity();
                    UserEntity userEntity = this.userService.getUserById(idStaff);
                    assignmentCustomerEntity.setUser(userEntity);
                    assignmentCustomerEntity.setCustomer(customerEntity);
                  ResponseEntity<String> handleSaveAssignmentCustomer =   this.assignmentCustomerService.handleSaveAssignmentCustomer(assignmentCustomerEntity);
                  if ( handleSaveAssignmentCustomer.getStatusCodeValue() != 200 ){
                      System.out.println("Lỗi không save đợc Assignment Customer  có staff id " + idStaff);
                      break;
                  }
                };

        }

    // logic code này chạy chậm khi dữ liệu nhân viên nhiều
//        List<AssignmentCustomerEntity> staffAssignmentCustomers = this.assignmentCustomerService.getStaffAssignmentCustomers(customerEntity);
//        List<UserEntity> staffs = new ArrayList<>();
//        for ( AssignmentCustomerEntity satffAssignmentCustomer : staffAssignmentCustomers ){
//            staffs.add(satffAssignmentCustomer.getUserEntity());
//        }
//
//        List<Long> uncheck = new ArrayList<>();
//        List<Long> check = new ArrayList<>();
//
//
//        if( !assignmentCustomerDTO.getStaffs().isEmpty()){
//            List<Long> staffDTO = assignmentCustomerDTO.getStaffs();
//            for (UserEntity allStaff : allStaffs ){
//                if ( staffDTO.contains(allStaff.getId())){
//                    check.add(allStaff.getId());
//                }
//                else {
//                    uncheck.add(allStaff.getId());
//                }
//            }
//
//            for ( Long  id :  check ){
//                UserEntity userEntity = this.userService.getUserById(id);
//                if ( !staffs.contains(userEntity)){
//                    AssignmentCustomerEntity assignmentCustomerEntity = new AssignmentCustomerEntity();
//                    assignmentCustomerEntity.setUserEntity(userEntity);
//                    assignmentCustomerEntity.setCustomerEntity(customerEntity);
//                    this.assignmentCustomerService.handleSave(assignmentCustomerEntity);
//                }
//
//            }
//
//            for ( Long  id :  uncheck  ){
//                UserEntity userEntity = this.userService.getUserById(id);
//                if ( staffs.contains(userEntity)) {
//
//                    this.assignmentCustomerService.handleDelete( userEntity , customerEntity);
//                }
//            }
//        }else {
//            List<Long>  AllStaff = new ArrayList<>();
//            for ( UserEntity sf : allStaffs ){
//                AllStaff.add(sf.getId());
//            }
//            for ( Long  id :  AllStaff  ){
//                UserEntity userEntity = this.userService.getUserById(id);
//                if ( staffs.contains(userEntity)) {
//
//                    this.assignmentCustomerService.handleDelete( userEntity , customerEntity);
//                }
//            }
//        }
    }


    @PostMapping("/admin/customer-edit")
    public  ModelAndView getCreateCustomer (@Valid  @ModelAttribute ("ModelCustomerDTO") CustomerDTO customerDTO , BindingResult bindingResult){


        // check data
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for ( FieldError error : fieldErrors ){
            System.out.println(">>>>>" + error.getField() + "-" + error.getDefaultMessage());
        }

        if (SecurityUtils.getPrincipal().getId() != null) {
            customerDTO.setManagementStaff(SecurityUtils.getPrincipal().getFullName());

        }
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject( "ModelCustomerDTO" , customerDTO);
        if ( customerDTO.getId() == null){

            if (bindingResult.hasErrors() ){
                return mav;
            }
            ResponseEntity<String> handleSaveCustomer = this.customerService.handleSaveCustomer(customerDTO);
            if ( handleSaveCustomer.getStatusCodeValue() == 200){
               return new ModelAndView("redirect:/admin/customer-list");
            }else {
                mav.addObject("errorSQL" , handleSaveCustomer.getBody());
                return mav;
            }
        }else {



            CustomerEntity customerEntity = this.customerService.getCustomerById(customerDTO.getId() );

            Map<String , String> AssignmentCustomerType = AssignCustomerType.type();
            List<TransactionEntity> listTransactionCodeCSKH = this.transactionService.getTransactionByCodeAndCustomer("CSKH" , customerEntity);
            List<TransactionEntity> listTransactionCodeDDX = this.transactionService.getTransactionByCodeAndCustomer("DDX", customerEntity);
            mav.addObject("AssignmentCustomerType" ,AssignmentCustomerType );
            mav.addObject("ListCSKHs" ,listTransactionCodeCSKH );
            mav.addObject("ListDDXs" ,listTransactionCodeDDX );
            if (bindingResult.hasErrors() ){
                return mav;
            }

            ResponseEntity<String> handleUpdateCustomer =   this.customerService.handleUpdateCustomer(customerDTO);
            if ( handleUpdateCustomer.getStatusCodeValue() == 200){
                mav.addObject("errorSQL" , handleUpdateCustomer.getBody());
                return mav;
            }else {
                mav.addObject("errorSQL" , handleUpdateCustomer.getBody());
                return mav;
            }
        }



    }

//    @PutMapping("/api/customer/update")
//    public  void getUpdateCustomer (@RequestBody CustomerDTO customerDTO){
//
//        if (SecurityUtils.getPrincipal().getId() != null) {
//            customerDTO.setManagementStaff(SecurityUtils.getPrincipal().getFullName());
//
//        }
//
//
//
//    }


    @DeleteMapping("/api/customer/{customerId}")
    public void getDeleteCustomer (@PathVariable List<Long> customerId){

      ResponseEntity<String>  handleDeleteCustomer =  this.customerService.handleDeleteCustomer(customerId);
      if (handleDeleteCustomer.getStatusCodeValue()  != 200 ){
          // handle
      }

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
