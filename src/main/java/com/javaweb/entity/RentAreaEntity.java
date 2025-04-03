package com.javaweb.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table( name = "rentarea")
public class RentAreaEntity extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "value")
    private  Long value;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;


}
