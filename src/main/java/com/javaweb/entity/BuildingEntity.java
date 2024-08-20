package com.javaweb.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name")
    private String name ;

    @Column(name ="street")
    private String street;

    @Column(name ="ward")
    private String ward;

    @Column(name = "district")
    private String district;


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


    @OneToMany(mappedBy = "buildingId")
    private List<RentareaEntity> rentValue;


    @OneToMany(mappedBy = "buildingEntity")
    private List<AssignmentBuildingEntity> assignmentBuildingEntities;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getBrokeRageFee() {
        return brokeRageFee;
    }

    public void setBrokeRageFee(Double brokeRageFee) {
        this.brokeRageFee = brokeRageFee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public List<RentareaEntity> getRentValue() {
        return rentValue;
    }

    public void setRentValue(List<RentareaEntity> rentValue) {
        this.rentValue = rentValue;
    }

    public List<AssignmentBuildingEntity> getAssignmentBuildingEntities() {
        return assignmentBuildingEntities;
    }

    public void setAssignmentBuildingEntities(List<AssignmentBuildingEntity> assignmentBuildingEntities) {
        this.assignmentBuildingEntities = assignmentBuildingEntities;
    }
}
