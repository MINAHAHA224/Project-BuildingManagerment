package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.RentareaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RentareaRepository rentareaRepository;

    public BuildingDTO toBuildingDTO (BuildingEntity buildingEntity){
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO = this.modelMapper.map(buildingEntity , BuildingDTO.class);
        // avater
        buildingDTO.setImageName(buildingEntity.getAvatar());
        // typeCode
        String [] typeCodeEntity = buildingEntity.getType().split(",");
        List<String> typeCodes = new ArrayList<>();
        for ( String  typeCode : typeCodeEntity){
            boolean add = typeCodes.add(typeCode);
        }

        buildingDTO.setTypeCode(typeCodes);
        // rentArea
        String reatAreaValue = this.rentareaRepository.getValueRentArea(buildingEntity);
        buildingDTO.setRentArea(reatAreaValue);
        return buildingDTO;
    }

}
