package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerEntityToCustomerSearchResponse;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl  implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerEntityToCustomerSearchResponse customerEntityToCustomerSearchResponse;
    @Override
    public Page<CustomerSearchResponse> getAllCustomer(CustomerSearchBuilder customerSearchBuilder , Pageable pageable) {
        Page<CustomerEntity> customerEntities = this.customerRepository.findAllCustomer(customerSearchBuilder , pageable);
        List<CustomerSearchResponse> customerSearchResponses = new ArrayList<>();
        for (  CustomerEntity customerEntity : customerEntities.getContent() ){
            CustomerSearchResponse customerResponse =  this.customerEntityToCustomerSearchResponse.toCustomerSearchResponse(customerEntity);
            customerSearchResponses.add(customerResponse);

        }
        return new PageImpl<>(customerSearchResponses,pageable ,customerEntities.getTotalElements()  ) ;
    }

    @Override
    public CustomerEntity getCustomerById(Long id) {
        return this.customerRepository.findById(id).get();
    }

    @Override
    public void handleSaveCustomer(CustomerDTO customerDTO) {


            CustomerEntity customerEntity = this.modelMapper.map(customerDTO ,CustomerEntity.class);

            customerEntity.setCreatedBy(customerDTO.getManagementStaff());
            customerEntity.setCreatedDate(new Date());
            this.customerRepository.save(customerEntity);
    }

    @Override
    @Transactional
    public void handleUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntityDate = this.customerRepository.findById(customerDTO.getId()).get();
        CustomerEntity customerEntity = this.modelMapper.map(customerDTO ,CustomerEntity.class);
        customerEntity.setCreatedBy(customerEntityDate.getCreatedBy());
        customerEntity.setCreatedDate(customerEntityDate.getCreatedDate());
        customerEntity.setModifiedBy(customerDTO.getManagementStaff());
        customerEntity.setModifiedDate(new Date());
        this.customerRepository.save(customerEntity);

    }

    @Override
    public void handleDeleteCustomer(List<Long> ids) {
        for ( Long id : ids ){
            this.customerRepository.deleteById(id);
        }
    }


}
