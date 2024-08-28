package com.javaweb.service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BuildingService {

    Page<BuildingSearchResponse> getBuildingSearch(BuildingSearchBuilder buildingSearchBuilder , Pageable pageable );

    BuildingEntity findById ( Long id);

    @Transactional
    void deleteBuilding( Long id);

    void createBuilding (BuildingDTO buildingDTO , String avatar);

    void updateBuilding ( BuildingDTO buildingDTO);


}
