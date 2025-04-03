package com.javaweb.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private  String note;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private  CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "type")
    private TransactionTypeEntity transactionType;



}
