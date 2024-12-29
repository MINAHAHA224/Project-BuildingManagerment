package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.BuildingEntityToBuildingSearchResponse;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.HandleUploadFile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private HandleUploadFile handleUploadFile;

    @Override
    public Page<BuildingSearchResponse> getBuildingSearch(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable ) {
        Page<BuildingEntity> buildingEntities = this.buildingRepository.findAll(buildingSearchBuilder , pageable );
        List<BuildingSearchResponse> results = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities.getContent() ){
            BuildingSearchResponse buildingSearchResponse = this.buildingEntityToBuildingSearchResponse.toBuildingSearchResponse(buildingEntity);
            results.add(buildingSearchResponse);
        }
        return new PageImpl<>(results, pageable, buildingEntities.getTotalElements());
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
    @Transactional
    public ResponseEntity<String> createBuilding(BuildingDTO buildingDTO , MultipartFile file) {
        try {
            BuildingEntity buildingEntity = new BuildingEntity();
            buildingEntity = this.modelMapper.map(buildingDTO ,BuildingEntity.class);
            String type = buildingDTO.getTypeCode().stream().map(it ->  it ).collect(Collectors.joining(","));
            buildingEntity.setType(type);
            String avatar = this.handleUploadFile.toHandleUploadFile(file , "building");
            buildingDTO.setImageName(avatar);
            buildingEntity.setAvatar(buildingDTO.getImageName());
            BuildingEntity building = this.buildingRepository.save(buildingEntity);
            // save rentArea

                List<String> rentAreaValues = Arrays.asList(buildingDTO.getRentArea().split(","));
                for ( String rentAreaValue : rentAreaValues ){
                    try {
                        RentareaEntity rentareaEntity = new RentareaEntity();
                        rentareaEntity.setValue(Long.valueOf(rentAreaValue));
                        rentareaEntity.setBuildingId(building);
                        this.rentareaRepository.save(rentareaEntity);
                    } catch (MyException e) {
                        System.out.println("--ER :Lỗi khi lưu giá trị thuê " + e.getMessage());
                        throw new MyException("Lỗi khi lưu giá trị thuê: " + e.getMessage());
                    }
                }

            return ResponseEntity.ok().body("Tạo mới Building thành công !!!");
        }  catch (MyException e) {
            // Trường hợp lỗi chung khác
            System.out.println("--ER :Có lỗi xảy ra khi tạo Building hoặc RentArea " + e.getMessage());
            return ResponseEntity.badRequest().body("Có lỗi xảy ra khi tạo Building hoặc RentArea");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateBuilding(BuildingDTO buildingDTO , MultipartFile file) {
        try {
            BuildingEntity CurrentBuildingEntity = this.buildingRepository.findById(buildingDTO.getId()).get();
            BuildingEntity UpdateBuildingEntity = this.modelMapper.map(buildingDTO ,BuildingEntity.class);
            String type = buildingDTO.getTypeCode().stream().map(it ->  it ).collect(Collectors.joining(","));
            UpdateBuildingEntity.setType(type);
            // handle delete rentArea
            this.rentareaRepository.handleDeleteRentarea(buildingDTO.getId());
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

            // handleSaveImage
            String avatar =  this.handleUploadFile.toHandleUploadFile(file , "building");
            UpdateBuildingEntity.setAvatar(avatar);
            // final save UpdateBuildingEntity
            this.buildingRepository.save(UpdateBuildingEntity);
            return ResponseEntity.ok().body("Cập nhật thành công");
        }catch( RuntimeException e){
            System.out.println("--ER : có lỗi cập nhật tòa nhà " + e.getMessage());
            return ResponseEntity.badRequest().body("Lỗi cập nhật Building !!!");
        }


    }


}
