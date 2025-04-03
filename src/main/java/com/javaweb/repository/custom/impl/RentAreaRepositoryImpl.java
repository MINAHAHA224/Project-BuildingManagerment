package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {

    @PersistenceContext
   private  EntityManager entityManager;

    @Override
    public void handleSaveRentArea(List<String> newValue, BuildingEntity CurrentBuildingEntity) {

    }

    @Override
    public String getValueRentArea(BuildingEntity buildingEntity) {
        String sql = "SELECT rentarea.value FROM rentarea WHERE rentarea.buildingid ='" +buildingEntity.getId()+"'";
        Query rs = entityManager.createNativeQuery(sql.toString());
        List<Object> rentAreaValues  = rs.getResultList();
        String rentAreaValue = "";
        if(rentAreaValues.size() > 1) {
            rentAreaValue = rentAreaValues.stream().map(it -> String.valueOf(it)).collect(Collectors.joining(","));
        }else {
            for ( Object value  :rentAreaValues  ){
                rentAreaValue = String.valueOf(value);
            }
        }

        return rentAreaValue;
    }

    @Override
    @Transactional
    public void handleDeleteRentarea(Long id) {
        try {
            String sql = "DELETE FROM  rentarea WHERE rentarea.buildingid =" +id;
            Query delete = entityManager.createNativeQuery(sql.toString());
            delete.executeUpdate();
        }catch (RuntimeException e){
            System.out.println("--ER : Lỗi xóa diện tích thuê!!!" + e.getMessage());

        }

    }
}
