package com.javaweb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;



    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;


    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "user")
    private List<UserRoleEntity> userRoleEntities;

    @OneToMany(mappedBy = "user")
    private List<AssignmentBuildingEntity> assignmentBuildingEntities;


    @OneToMany(mappedBy = "user")
    private List<AssignmentCustomerEntity> assignmentCustomerEntities;

//    @OneToMany(mappedBy="userEntity",  cascade = {CascadeType.MERGE,CascadeType.PERSIST} ,orphanRemoval = true)
//    private List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();
//
//    @OneToMany(mappedBy = "userEntity" , cascade = {CascadeType.MERGE,CascadeType.PERSIST} ,orphanRemoval = true)
//    private  List<AssignmentCustomerEntity> assignmentCustomerEntities = new ArrayList<>();
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
//    private List<RoleEntity> roles = new ArrayList<>();



}
