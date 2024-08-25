package com.javaweb.converter;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerSearchBuilderConverter {
    public CustomerSearchBuilder toCustomerSearchBuilder (CustomerSearchRequest customerSearchRequest) {
        return  new CustomerSearchBuilder.Builder()
                .setFullName(MapUtils.getObjectCustomer(customerSearchRequest.getName() , String.class))
                .setEmail(MapUtils.getObjectCustomer(customerSearchRequest.getEmail(),String.class))
                .setPhone(MapUtils.getObjectCustomer(customerSearchRequest.getCustomerPhone(),String.class))
                .setStaffId(MapUtils.getObjectCustomer(customerSearchRequest.getStaffId(), Long.class))
                .Build();


    }
}
