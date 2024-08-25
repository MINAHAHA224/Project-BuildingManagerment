package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TransactionServiceImpl  implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionConverter transactionConverter;
    @Override
    public List<TransactionEntity> getTransactionByCodeAndCustomer(String code, CustomerEntity customerEntity) {
        return this.transactionRepository.getTransactionEntitiesByCodeAndCustomerEntity(code,customerEntity);
    }


    @Override
    public void handleSaveTransaction(TransactionDTO transactionDTO ) {
        if (transactionDTO.getIdTransaction() != null ){
            CustomerEntity customerEntity = this.customerService.getCustomerById(transactionDTO.getCustomerTransID());
            TransactionEntity transactionEntity = this.transactionRepository.findById(transactionDTO.getIdTransaction()).get();
            TransactionEntity newTransaction = this.transactionConverter.toTransactionEntity(transactionDTO);
            newTransaction.setId(transactionDTO.getIdTransaction());
            newTransaction.setCustomerEntity(customerEntity);
            newTransaction.setCreatedBy(transactionEntity.getCreatedBy());
            newTransaction.setCreatedDate(transactionEntity.getCreatedDate());
            newTransaction.setModifiedBy(transactionDTO.getManagementStaff());
            newTransaction.setModifiedDate(new Date());
            this.transactionRepository.save(newTransaction);
        }else{
            CustomerEntity customerEntity = this.customerService.getCustomerById(transactionDTO.getCustomerTransID());
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setCode(transactionDTO.getCode());
            transactionEntity.setCustomerEntity(customerEntity);
            transactionEntity.setNote(transactionDTO.getNote());
            transactionEntity.setCreatedBy(transactionDTO.getManagementStaff());
            this.transactionRepository.save(transactionEntity);
        }

    }

    @Override
    public void handleDeleteTransaction(Long id) {
        this.transactionRepository.deleteById(id);
    }
}
