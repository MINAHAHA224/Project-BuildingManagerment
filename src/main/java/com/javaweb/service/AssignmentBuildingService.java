package com.javaweb.service;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssignmentBuildingService {

    List<AssignmentBuildingEntity> getAssignmentBuildingEntity (BuildingEntity buildingEntity);
    List<AssignmentBuildingEntity> getAll ();

    ResponseEntity<String>   handleSaveAssignmentBuilding( AssignmentBuildingEntity assignmentBuildingEntity);

    ResponseEntity<String> deleteAssignment (UserEntity userEntity , BuildingEntity buildingEntity);

    ResponseEntity<String> deleteAssignmentBuilding (Long id);

}
