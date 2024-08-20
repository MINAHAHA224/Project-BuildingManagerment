package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentareaRepository extends JpaRepository<RentareaEntity, Long> {


}
