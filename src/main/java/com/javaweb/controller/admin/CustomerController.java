package com.javaweb.controller.admin;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.AssignCustomerType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import com.javaweb.service.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerSearchBuilderConverter customerSearchBuilderConverter;


    @GetMapping("/admin/customer-list")
    public ModelAndView getCustomerPage (@ModelAttribute CustomerSearchRequest customerSearchRequest , HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("ModelCustomerRequest" , customerSearchRequest);
        Map<Long , String> listStaffs = this.userService.getStaff( 1 , "STAFF");
        mav.addObject("listStaffs" , listStaffs);

        DisplayTagUtils.ofs(request,customerSearchRequest);
        Pageable pageable = PageRequest.of(customerSearchRequest.getPage() - 1 , 2 );
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            customerSearchRequest.setStaffId(SecurityUtils.getPrincipal().getId());
            CustomerSearchBuilder customerSearchBuilder = this.customerSearchBuilderConverter.toCustomerSearchBuilder(customerSearchRequest);
            Page<CustomerSearchResponse> customerSearchResponse =  this.customerService.getAllCustomer(customerSearchBuilder , pageable);
            long totalPages = (long)Math.ceil((double)customerSearchResponse.getTotalElements()/2);
            mav.addObject("ListCustomerResponse" , customerSearchResponse.getContent());
            mav.addObject("totalPages" , totalPages);
            mav.addObject("currentPage" ,customerSearchRequest.getPage() );
        }else {
            CustomerSearchBuilder customerSearchBuilder = this.customerSearchBuilderConverter.toCustomerSearchBuilder(customerSearchRequest);
            Page<CustomerSearchResponse> customerSearchResponse = this.customerService.getAllCustomer(customerSearchBuilder, pageable);
            long totalPages = (long)Math.ceil((double)customerSearchResponse.getTotalElements()/2);
            mav.addObject("ListCustomerResponse", customerSearchResponse.getContent());
            mav.addObject("totalPages", totalPages);
            mav.addObject("currentPage", customerSearchRequest.getPage());
        }


        return mav;
    }


    @GetMapping("/admin/customer-edit")
    public ModelAndView getCustomerUpdatePage (@ModelAttribute CustomerDTO customerDTO){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("ModelCustomerDTO" , customerDTO);


        return mav;
    }


    @GetMapping("/admin/customer-edit-{id}")
    public ModelAndView getCustomerCreatePage (@ModelAttribute CustomerDTO customerDTO,@PathVariable Long id ){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerEntity customerEntity = this.customerService.getCustomerById(id);
        CustomerDTO newCustomerDTO = this.customerConverter.toCustomerDTO(customerEntity);
        mav.addObject("ModelCustomerDTO" , newCustomerDTO);


        Map<String , String> AssignmentCustomerType = AssignCustomerType.type();
        List<TransactionEntity> listTransactionCodeCSKH = this.transactionService.getTransactionByCodeAndCustomer("CSKH" , customerEntity);
        List<TransactionEntity> listTransactionCodeDDX = this.transactionService.getTransactionByCodeAndCustomer("DDX", customerEntity);
        mav.addObject("AssignmentCustomerType" ,AssignmentCustomerType );
        mav.addObject("ListCSKHs" ,listTransactionCodeCSKH );
        mav.addObject("ListDDXs" ,listTransactionCodeDDX );

        return mav;
    }
}
