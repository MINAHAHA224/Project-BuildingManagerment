package com.javaweb.service;


import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionEntity> getTransactionByCodeAndCustomer (String code , CustomerEntity customerEntity);

    void handleSaveTransaction (TransactionDTO transactionDTO );

    void handleDeleteTransaction ( Long id);
}
