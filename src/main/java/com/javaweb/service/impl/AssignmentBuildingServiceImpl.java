package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;


    @Override
    public List<AssignmentBuildingEntity> getAssignmentBuildingEntity(BuildingEntity buildingEntity) {
        return this.assignmentBuildingRepository.findAssignmentBuildingEntitiesByBuildingEntity(buildingEntity);
    }

    @Override
    public List<AssignmentBuildingEntity> getAll() {
        return this.assignmentBuildingRepository.findAllAssignmentBuilding();
    }

    @Override
    public AssignmentBuildingEntity save(AssignmentBuildingEntity assignmentBuildingEntity) {
        this.assignmentBuildingRepository.save(assignmentBuildingEntity);

        return null;
    }

    @Override
    @Transactional
    public void deleteAssignment(UserEntity userEntity, BuildingEntity buildingEntity) {
        this.assignmentBuildingRepository.deleteAssignmentBuildingEntityByUserEntityAndBuildingEntity(userEntity,buildingEntity);
    }
}
