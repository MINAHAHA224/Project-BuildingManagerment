package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long>, AssignmentBuildingRepositoryCustom {

    void deleteAssignmentBuildingEntityByUserEntityAndBuildingEntity (UserEntity userEntity , BuildingEntity buildingEntity);
}
