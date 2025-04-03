package com.javaweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity  {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "fullname")
    private String name;

    @Column(name = "phone" )
    private  String customerPhone;

    @Column(name = "email")
    private  String email;


    @OneToMany(mappedBy = "customer")
    private List<AssignmentCustomerEntity> assignmentCustomerEntities;



//    @OneToMany(mappedBy = "customerEntity" , cascade = {CascadeType.PERSIST,CascadeType.MERGE ,CascadeType.REMOVE} )
//    private List<AssignmentCustomerEntity> assignmentCustomerEntityList;
//
//    @OneToMany(mappedBy = "customerEntity" , cascade = {CascadeType.PERSIST,CascadeType.MERGE ,CascadeType.REMOVE})
//    private  List<TransactionEntity> transactionEntities;


}
