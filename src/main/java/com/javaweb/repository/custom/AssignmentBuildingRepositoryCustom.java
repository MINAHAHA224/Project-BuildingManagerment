package com.javaweb.repository.custom;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentBuildingRepositoryCustom {
    List<AssignmentBuildingEntity> findAssignmentBuildingEntitiesByBuildingEntity (BuildingEntity buildingEntity);
    List<AssignmentBuildingEntity> findAllAssignmentBuilding ( );
}
