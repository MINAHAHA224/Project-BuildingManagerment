package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingEntityToBuildingSearchResponse;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildingEntityToBuildingSearchResponse buildingEntityToBuildingSearchResponse;


    @Override
    public List<BuildingSearchResponse> getBuildingSearch(BuildingSearchBuilder buildingSearchBuilder ) {
        List<BuildingEntity> buildingEntities = this.buildingRepository.findAll(buildingSearchBuilder );

        List<BuildingSearchResponse> results = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities ){
            BuildingSearchResponse buildingSearchResponse = this.buildingEntityToBuildingSearchResponse.toBuildingSearchResponse(buildingEntity);
            results.add(buildingSearchResponse);
        }

        return results;
    }

    @Override
    public BuildingEntity findById(Long id) {
        Optional<BuildingEntity> buildingEntity = this.buildingRepository.findById(id);
        BuildingEntity newBuilding = new BuildingEntity();
        if (buildingEntity.isPresent() ){
            newBuilding = buildingEntity.get();
        }
        return newBuilding;
    }

    @Override
    @Transactional
    public void deleteBuilding(Long id) {
        this.buildingRepository.deleteById(id);
    }

    @Override
    public void saveBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity = this.modelMapper.map(buildingDTO ,BuildingEntity.class);

       this.buildingRepository.save(buildingEntity);
    }

    @Override
    public void updateBuilding(BuildingDTO buildingDTO) {

        BuildingEntity CurrentBuildingEntity = this.buildingRepository.findById(buildingDTO.getId()).get();
        BuildingEntity UpdateBuildingEntity = this.modelMapper.map(buildingDTO ,BuildingEntity.class);

//        this.buildingRepository.deleteById(buildingDTO.getId());
        this.buildingRepository.save(UpdateBuildingEntity);
    }
}
