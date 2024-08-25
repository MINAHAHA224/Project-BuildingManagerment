package com.javaweb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity  {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "fullname" , nullable = false)
    private String name;

    @Column(name = "phone" , nullable = false)
    private  String customerPhone;

    @Column(name = "email")
    private  String email;

    @Column(name = "companyname")
    private  String companyName;

    @Column(name = "demand")
    private  String demand;

    @Column(name = "status")
    private  String status;

    @Column(name = "is_active")
    private  String isActive;



    @OneToMany(mappedBy = "customerEntity" , cascade = {CascadeType.PERSIST,CascadeType.MERGE ,CascadeType.REMOVE} )
    private List<AssignmentCustomerEntity> assignmentCustomerEntityList;

    @OneToMany(mappedBy = "customerEntity" , cascade = {CascadeType.PERSIST,CascadeType.MERGE ,CascadeType.REMOVE})
    private  List<TransactionEntity> transactionEntities;

    public List<TransactionEntity> getTransactionEntities() {
        return transactionEntities;
    }

    public void setTransactionEntities(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }

    public List<AssignmentCustomerEntity> getAssignmentCustomerEntityList() {
        return assignmentCustomerEntityList;
    }

    public void setAssignmentCustomerEntityList(List<AssignmentCustomerEntity> assignmentCustomerEntityList) {
        this.assignmentCustomerEntityList = assignmentCustomerEntityList;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
