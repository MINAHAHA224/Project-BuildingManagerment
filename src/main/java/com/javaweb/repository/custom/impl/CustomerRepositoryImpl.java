package com.javaweb.repository.custom.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    public void joinTable ( CustomerSearchBuilder customerSearchBuilder , StringBuilder sql ){
        if ( customerSearchBuilder.getStaffId() != null ){
            sql.append(" INNER JOIN assignmentcustomer ON customer.id = assignmentcustomer.customerid ");
        }
    }

    public  void queryNormal (CustomerSearchBuilder customerSearchBuilder , StringBuilder sql  ){
        try {
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
            for ( Field field : fields ){
                field.setAccessible(true);
                if ( !field.getName().equals("staffId")){
                    Object value = field.get(customerSearchBuilder);
                    if ( value != null && value != ""){
                        if ( field.getType().getName().equals("java.lang.String")){
                            sql.append(" AND customer." + field.getName() + " LIKE '%" + value + "%' ");
                        }
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void querySpecial (CustomerSearchBuilder customerSearchBuilder , StringBuilder sql ){
        if (customerSearchBuilder.getStaffId()  != null){
            sql.append(" AND assignmentcustomer.staffid = " + customerSearchBuilder.getStaffId());
        }
    }
    @Override
    public Page<CustomerEntity> findAllCustomer(CustomerSearchBuilder customerSearchBuilder , Pageable pageable) {

        StringBuilder sql = new StringBuilder("SELECT customer.id , customer.fullname , customer.phone , customer.email , customer.companyname , customer.demand , customer.status , customer.is_active , customer.createddate , customer.modifieddate , customer.createdby , customer.modifiedby FROM customer ");
        joinTable(customerSearchBuilder,sql);
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        queryNormal(customerSearchBuilder,sql);
        querySpecial(customerSearchBuilder,sql);
        sql.append(" GROUP BY customer.id ");



        // Pagination query
        String querySql = sql.toString();
        Query query = entityManager.createNativeQuery( querySql , CustomerEntity.class);

        // Đếm số bản ghi ( row )
        List<Object> rows = query.getResultList();
        long totalResults = rows.size();


        query.setFirstResult((int)pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<CustomerEntity> customerEntities = query.getResultList();

        return new PageImpl<>(customerEntities , pageable ,totalResults ) ;
    }
}
