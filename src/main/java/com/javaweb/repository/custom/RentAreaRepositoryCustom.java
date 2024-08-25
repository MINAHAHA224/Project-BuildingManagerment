package com.javaweb.repository.custom;


import com.javaweb.entity.BuildingEntity;

import java.util.List;

public interface RentAreaRepositoryCustom {
    void handleSaveRentArea (List<String> newValue , BuildingEntity CurrentBuildingEntity);
}
