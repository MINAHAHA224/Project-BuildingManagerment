package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO toBuildingDTO (BuildingEntity buildingEntity){
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO = this.modelMapper.map(buildingEntity , BuildingDTO.class);

        return buildingDTO;
    }

}
