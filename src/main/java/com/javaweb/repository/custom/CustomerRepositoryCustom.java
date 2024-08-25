package com.javaweb.repository.custom;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepositoryCustom {


    Page<CustomerEntity> findAllCustomer (CustomerSearchBuilder customerSearchBuilder , Pageable pageable);
}
