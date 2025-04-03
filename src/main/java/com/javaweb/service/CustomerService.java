package com.javaweb.service;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

    Page<CustomerSearchResponse> getAllCustomer (CustomerSearchBuilder customerSearchBuilder , Pageable pageable);

    CustomerEntity getCustomerById (Long id);

    ResponseEntity<String> handleSaveCustomer (CustomerDTO customerDTO);


    ResponseEntity<String> handleUpdateCustomer ( CustomerDTO customerDTO);

    ResponseEntity<String> handleDeleteCustomer ( List<Long> ids );


}
