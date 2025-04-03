package com.javaweb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "transactiontype")
public class TransactionTypeEntity extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private  String nameTransactionType;

    @Column(name = "code")
    private  String codeTransactionType;

    @OneToMany(mappedBy = "transactionType")
    private List<TransactionEntity> transactionEntities;


}
