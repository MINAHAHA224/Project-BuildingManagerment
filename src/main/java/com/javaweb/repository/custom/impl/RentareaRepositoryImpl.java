package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RentareaRepositoryImpl implements RentAreaRepositoryCustom {


    @Override
    public void handleSaveRentArea(List<String> newValue, BuildingEntity CurrentBuildingEntity) {

    }
}
