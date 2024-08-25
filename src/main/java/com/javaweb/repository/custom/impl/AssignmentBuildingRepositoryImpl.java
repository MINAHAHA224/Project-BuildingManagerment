package com.javaweb.repository.custom.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AssignmentBuildingRepositoryImpl implements AssignmentBuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public  void joinTable(StringBuilder sql , Long id){
        if ( id != null){
            sql.append(" INNER JOIN building ON building.id = assignmentbuilding.buildingid ");
        }

    }

    public void querySpecial ( StringBuilder sql , Long id){
        if ( id != null){
            sql.append(" AND building.id ="+ id + " ;");
        }
    }
    @Override
    public List<AssignmentBuildingEntity> findAssignmentBuildingEntitiesByBuildingEntity(BuildingEntity buildingEntity) {
        Long id = buildingEntity.getId();
        StringBuilder sql = new StringBuilder(" SELECT assignmentbuilding.id , assignmentbuilding.staffid , assignmentbuilding.buildingid  FROM assignmentbuilding ");
        joinTable(sql , id);
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        querySpecial ( sql , id);

        Query query = entityManager.createNativeQuery(sql.toString() );
        List<Object[]> resultList = query.getResultList();
        List<AssignmentBuildingEntity> answers = new ArrayList<>();
        for(Object[] row : resultList) {
            Long idassign = ((Number) row[0]).longValue();
            Long staffidassign = ((Number) row[1]).longValue();
            Long buildingidassign = ((Number) row[2]).longValue();

            UserEntity userEntityCus = entityManager.find(UserEntity.class, staffidassign);
            BuildingEntity buildingEntityCus = entityManager.find(BuildingEntity.class, buildingidassign);
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setId(idassign);
            assignmentBuildingEntity.setUserEntity(userEntityCus);
            assignmentBuildingEntity.setBuildingEntity(buildingEntityCus);
            answers.add(assignmentBuildingEntity);
        }
            return answers;
    }

    @Override
    public List<AssignmentBuildingEntity> findAllAssignmentBuilding() {
        StringBuilder sql = new StringBuilder(" SELECT assignmentbuilding.id , assignmentbuilding.staffid , assignmentbuilding.buildingid FROM assignmentbuilding ; ");
        Query query  = entityManager.createNativeQuery(sql.toString());

        List<Object[]> answers = query.getResultList();
        List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();
        for ( Object[] answer : answers ){
            Long id = ((Number)answer[0]).longValue();
            Long staffId = ((Number)answer[1]).longValue();
            Long buildingId = ((Number)answer[2]).longValue();

            UserEntity userEntity = entityManager.find(UserEntity.class ,staffId);
            BuildingEntity building = entityManager.find(BuildingEntity.class ,buildingId);
            AssignmentBuildingEntity AssignBuildingEntity = new AssignmentBuildingEntity();
            AssignBuildingEntity.setId(id);
            AssignBuildingEntity.setUserEntity(userEntity);
            AssignBuildingEntity.setBuildingEntity(building);
            assignmentBuildingEntities.add(AssignBuildingEntity);

        }

        return assignmentBuildingEntities;
    }
}
