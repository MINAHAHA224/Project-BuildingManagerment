package com.javaweb.entity;

import lombok.*;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "renttype")
public class RentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String codeRentType;

    @Column(name = "name")
    private String nameRentType;

    @OneToMany(mappedBy = "rentType")
    private List<BuildingRentTypeEntity> buildingRentTypeEntities;


}
