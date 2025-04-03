package com.javaweb.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "assignmentcustomer")
public class AssignmentCustomerEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staffid")
    private  UserEntity user;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;


}
