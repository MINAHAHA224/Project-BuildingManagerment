package com.javaweb.service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BuildingService {

    List<BuildingSearchResponse> getBuildingSearch(BuildingSearchBuilder buildingSearchBuilder );

    BuildingEntity findById ( Long id);

    @Transactional
    void deleteBuilding( Long id);

    void saveBuilding (BuildingDTO buildingDTO);

    void updateBuilding ( BuildingDTO buildingDTO);
}
