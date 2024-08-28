package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingEntityToBuildingSearchResponse;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildingEntityToBuildingSearchResponse buildingEntityToBuildingSearchResponse;

    @Autowired
   private ServletContext servletContext;

    @Autowired
    private RentareaRepository rentareaRepository;

    @Override
    public Page<BuildingSearchResponse> getBuildingSearch(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable ) {
        Page<BuildingEntity> buildingEntities = this.buildingRepository.findAll(buildingSearchBuilder , pageable );

        List<BuildingSearchResponse> results = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities.getContent() ){
            BuildingSearchResponse buildingSearchResponse = this.buildingEntityToBuildingSearchResponse.toBuildingSearchResponse(buildingEntity);
            results.add(buildingSearchResponse);
        }

        return new PageImpl<>(results, pageable, buildingEntities.getTotalPages());
    }

    @Override
    public BuildingEntity findById(Long id) {
        Optional<BuildingEntity> buildingEntity = this.buildingRepository.findById(id);
        BuildingEntity newBuilding = new BuildingEntity();
        if (buildingEntity.isPresent() ){
            newBuilding = buildingEntity.get();
        }
        return newBuilding;
    }

    @Override
    @Transactional
    public void deleteBuilding(Long id) {
        this.buildingRepository.deleteById(id);
    }

    @Override
    public void createBuilding(BuildingDTO buildingDTO , String avatar) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity = this.modelMapper.map(buildingDTO ,BuildingEntity.class);
        String type = buildingDTO.getTypeCode().stream().map(it ->  it ).collect(Collectors.joining(","));
        buildingEntity.setType(type);
        buildingEntity.setAvatar(avatar);
        BuildingEntity building = this.buildingRepository.save(buildingEntity);

        // update thi phai save theo id cu ( vi truong hop lỡ thằng id cũ nó sửa ) còn Create thì cứ
        // buộc phải có id trước vì lúc DTO gửi về ko có id
        List<String> rentAreaValues = Arrays.asList(buildingDTO.getRentArea().split(","));
        for ( String rentAreaValue : rentAreaValues ){
            RentareaEntity rentareaEntity = new RentareaEntity();
            rentareaEntity.setValue(Long.valueOf(rentAreaValue));
            rentareaEntity.setBuildingId(building);
            this.rentareaRepository.save(rentareaEntity);
        }
    }

    @Override
    public void updateBuilding(BuildingDTO buildingDTO) {

        BuildingEntity CurrentBuildingEntity = this.buildingRepository.findById(buildingDTO.getId()).get();
        BuildingEntity UpdateBuildingEntity = this.modelMapper.map(buildingDTO ,BuildingEntity.class);
        String type = buildingDTO.getTypeCode().stream().map(it ->  it ).collect(Collectors.joining(","));
        UpdateBuildingEntity.setType(type);
        // handle save rentAreaValue
        List<String> rentAreaValues = Arrays.asList(buildingDTO.getRentArea().split(","));
        List<RentareaEntity> rentareaEntities = new ArrayList<>();
        for ( String rentAreaValue : rentAreaValues ){
            RentareaEntity rentareaEntity = new RentareaEntity();
            rentareaEntity.setValue(Long.valueOf(rentAreaValue));
            rentareaEntity.setBuildingId(CurrentBuildingEntity);
            rentareaEntities.add(rentareaEntity);
            this.rentareaRepository.save(rentareaEntity);
        }
        UpdateBuildingEntity.setRentValue(rentareaEntities);

        // final save UpdateBuildingEntity
        this.buildingRepository.save(UpdateBuildingEntity);
    }


}
