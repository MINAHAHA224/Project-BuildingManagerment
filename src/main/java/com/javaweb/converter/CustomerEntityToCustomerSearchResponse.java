package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.response.CustomerSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityToCustomerSearchResponse {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerSearchResponse toCustomerSearchResponse (CustomerEntity  customerEntity){
        return this.modelMapper.map(customerEntity , CustomerSearchResponse.class);
    }
}
