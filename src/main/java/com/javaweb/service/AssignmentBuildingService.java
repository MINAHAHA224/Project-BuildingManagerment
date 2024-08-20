package com.javaweb.service;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssignmentBuildingService {

    List<AssignmentBuildingEntity> getAssignmentBuildingEntity (BuildingEntity buildingEntity);
    List<AssignmentBuildingEntity> getAll ( );

    AssignmentBuildingEntity save( AssignmentBuildingEntity assignmentBuildingEntity);
    @Transactional
    void deleteAssignment (UserEntity userEntity , BuildingEntity buildingEntity);
}
