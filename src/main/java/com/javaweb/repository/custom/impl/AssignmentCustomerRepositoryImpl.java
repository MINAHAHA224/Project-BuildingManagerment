package com.javaweb.repository.custom.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.repository.custom.AssignmentCustomerRepositoryCustom;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AssignmentCustomerRepositoryImpl implements AssignmentCustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void querySpecial ( CustomerEntity customerEntity , StringBuilder sql ){
        if ( customerEntity.getId() != null ){
            sql.append(" AND assignmentcustomer.customerid = " + customerEntity.getId() );
        }
    }
    @Override
    public List<AssignmentCustomerEntity> findAssignmentCustomerEntitiesByCustomerEntity(CustomerEntity customerEntity) {
        StringBuilder sql = new StringBuilder("SELECT assignmentcustomer.id , assignmentcustomer.staffid , assignmentcustomer.customerid FROM assignmentcustomer ");
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        querySpecial(customerEntity,sql);
        sql.append(" GROUP BY assignmentcustomer.id ; ");

        Query query = entityManager.createNativeQuery(sql.toString());
        List<AssignmentCustomerEntity> assignmentCustomerEntities = new ArrayList<>();
        List<Object[]> rows = query.getResultList();
        for ( Object[] row : rows ){
            Long id = ((Number)row[0]).longValue();
            Long staffId = ((Number)row[1]).longValue();
            Long customerId = ((Number)row[2]).longValue();

            UserEntity userEntity = entityManager.find(UserEntity.class , staffId);
            CustomerEntity customer =  entityManager.find(CustomerEntity.class , customerId);

            AssignmentCustomerEntity assignmentCustomerEntity = new AssignmentCustomerEntity();
            assignmentCustomerEntity.setId(id);
            assignmentCustomerEntity.setUserEntity(userEntity);
            assignmentCustomerEntity.setCustomerEntity(customer);
            assignmentCustomerEntities.add(assignmentCustomerEntity);

        }


        return assignmentCustomerEntities;
    }
}
