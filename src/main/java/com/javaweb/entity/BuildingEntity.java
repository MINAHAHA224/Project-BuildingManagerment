package com.javaweb.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "building")
public class BuildingEntity implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name")
    private String name ;

    @Column(name ="street")
    private String street;

    @Column(name ="ward")
    private String ward;




    @Column(name ="numberofbasement")
    private Long numberOfBasement;

    @Column(name ="floorarea")
    private Long floorArea;

    @Column(name ="direction")
    private String direction;

    @Column(name ="level")
    private Long level;

    @Column(name ="rentprice")
    private Long rentPrice;

    @Column(name ="rentpricedescription")
    private String rentPriceDescription;

    @Column(name ="servicefee")
    private String serviceFee;



    @Column(name ="brokeragefee")
    private Double brokeRageFee;

    @Column(name = "type")
    private String type;
    @Column(name ="managername")
    private String managerName;

    @Column(name ="managerphone")
    private String managerPhone;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "districtid")
    private DistrictEntity district;

    @OneToMany(mappedBy = "building")
    public List<AssignmentBuildingEntity> buildingEntities;

    @OneToMany(mappedBy = "building")
    public List<BuildingRentTypeEntity> buildingRentTypeEntities;

    @OneToMany(mappedBy = "building")
    public List<RentAreaEntity> rentAreaEntities;

}
