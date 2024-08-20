package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

@Component
public class BuildingSearchBuilderConverter {
//    public  BuildingSearchBuilder toBuildingSearchBuilder (Map<String , Object> params , List<String> rentTypes){
//
//        return new BuildingSearchBuilder.Builder()
//                .setName(MapUtils.getObjects(params,"name" , String.class))
//                .setFloorArea(MapUtils.getObjects(params ,"floorArea" , Long.class))
//                .setDistrict(MapUtils.getObjects(params,"district", String.class))
//                .setWard(MapUtils.getObjects(params,"ward" ,String.class))
//                .setStreet(MapUtils.getObjects(params,"street", String.class))
//                .setNumberOfBasement(MapUtils.getObjects(params,"numberOfBasement",Long.class))
//                .setDirection(MapUtils.getObjects(params ,"direction" ,String.class))
//                .setLevel(MapUtils.getObjects(params, "level" , Long.class))
//                .setAreaTo(MapUtils.getObjects(params,"areaTo", Long.class))
//                .setAreaFrom(MapUtils.getObjects(params,"areaFrom" , Long.class))
//                .setRentPriceTo(MapUtils.getObjects(params ,"rentPriceTo" ,Long.class))
//                .setRentPriceFrom(MapUtils.getObjects(params , "rentPriceFrom" ,Long.class))
//                .setManagerName(MapUtils.getObjects(params ,"managerName" , String.class))
//                .setManagerPhone(MapUtils.getObjects(params , "managerPhone" , String.class))
//                .setStaffId(MapUtils.getObjects(params , "staffId" ,Long.class))
//                .setTypeCode(rentTypes)
//                .build();
//    }
    public  BuildingSearchBuilder toBuildingSearchBuilder ( BuildingSearchRequest buildingSearchRequest){

        return new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObjects(buildingSearchRequest.getName() , String.class))
                .setFloorArea(MapUtils.getObjects(buildingSearchRequest.getFloorArea() , Long.class))
                .setDistrict(MapUtils.getObjects(buildingSearchRequest.getDistrict(), String.class))
                .setWard(MapUtils.getObjects(buildingSearchRequest.getWard(),String.class))
                .setStreet(MapUtils.getObjects(buildingSearchRequest.getStreet(), String.class))
                .setNumberOfBasement(MapUtils.getObjects(buildingSearchRequest.getNumberOfBasement(),Long.class))
                .setDirection(MapUtils.getObjects(buildingSearchRequest.getDirection() ,String.class))
                .setLevel(MapUtils.getObjects(buildingSearchRequest.getLevel(), Long.class))
                .setAreaTo(MapUtils.getObjects(buildingSearchRequest.getAreaTo(), Long.class))
                .setAreaFrom(MapUtils.getObjects(buildingSearchRequest.getAreaFrom() , Long.class))
                .setRentPriceTo(MapUtils.getObjects(buildingSearchRequest.getRentPriceTo() ,Long.class))
                .setRentPriceFrom(MapUtils.getObjects(buildingSearchRequest.getRentPriceFrom() ,Long.class))
                .setManagerName(MapUtils.getObjects(buildingSearchRequest.getManagerName() , String.class))
                .setManagerPhone(MapUtils.getObjects(buildingSearchRequest.getManagerPhone() , String.class))
                .setStaffId(MapUtils.getObjects(buildingSearchRequest.getStaffId() ,Long.class))
                .setTypeCode(buildingSearchRequest.getTypeCode())
                .build();
    }
}
