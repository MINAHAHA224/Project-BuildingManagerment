package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingEntityToBuildingSearchResponse {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingSearchResponse (BuildingEntity buildingEntity){
        BuildingSearchResponse buildingSearchResponse = this.modelMapper.map(buildingEntity ,BuildingSearchResponse.class );
        Map<String ,String> ans = DistrictCode.code();
        String codeDistrict = buildingEntity.getDistrict() ;

        String  nameDistrict = "";
        for (Map.Entry<String,String> an : ans.entrySet()){
            if (an.getKey().equals(codeDistrict)){
                nameDistrict = an.getValue();
            }
        }

        buildingSearchResponse.setAddress(buildingEntity.getStreet() +" " + buildingEntity.getWard()   +" "+ nameDistrict);

        List<RentareaEntity> rentareaEntities = buildingEntity.getRentValue();

        String resultNew = rentareaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        buildingSearchResponse.setRentArea(resultNew);
        buildingSearchResponse.setEmptyArea(null);
        buildingSearchResponse.setBrokerageFee(0.0);

        return buildingSearchResponse;
    }
}
