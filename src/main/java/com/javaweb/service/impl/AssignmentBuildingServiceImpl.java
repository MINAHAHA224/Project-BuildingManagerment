package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Transactional
    public ResponseEntity<String>  handleSaveAssignmentBuilding(AssignmentBuildingEntity assignmentBuildingEntity) {
        try {
            this.assignmentBuildingRepository.save(assignmentBuildingEntity);
            return  ResponseEntity.ok().body("Tạo mới thành công Assignment Building!!");
        }catch (RuntimeException e){
            System.out.println("--ER : Lỗi tạo mới   Assignment Building : " + assignmentBuildingEntity.getBuilding().getId()+e.getMessage());
            return  ResponseEntity.badRequest().body("Lỗi tạo mới AssignmentBuilding");
        }



    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteAssignment(UserEntity userEntity, BuildingEntity buildingEntity) {
//        try {
//            this.assignmentBuildingRepository.deleteAssignmentBuildingEntityByUserEntityAndBuildingEntity(userEntity,buildingEntity);
//            return ResponseEntity.ok().body("Xóa thành công Assignment trong Building !!!");
//        }catch (RuntimeException e)
//        {
//            System.out.println("--ER : Lỗi không xóa được assignment building");
//            return ResponseEntity.badRequest().body("Lỗi không xóa được assignment trong building");
//        }
    return null;

    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteAssignmentBuilding(Long id) {
        try {
            this.assignmentBuildingRepository.deleteAssignmentBuildingEntityByBuilding_Id(id);
            return ResponseEntity.ok().body("Xóa thành công Assignment trong Building !!!");
        }catch (RuntimeException e)
        {
            System.out.println("--ER : Lỗi không xóa được assignment building");
            return ResponseEntity.badRequest().body("Lỗi không xóa được assignment trong building");
        }

    }
}
