package com.javaweb.api.admin;


import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping(value = "/api/building/{id}/staffs")
    public ResponseDTO getStaffModel (@PathVariable Long id){

        // tìm tòa nhà có id
        BuildingEntity buildingEntity = this.buildingService.findById(id);

        // tìm tất cả các nhân viên có role là Staff và đang hoạt động
        List<UserEntity> staffs = this.userService.getStaffModels(1 , "STAFF");

        // tìm tất cả các nhân viên HIỆN đang quản lí cái id building
       List<AssignmentBuildingEntity> assignmentBuildingEntities = this.assignmentBuildingService.getAssignmentBuildingEntity(buildingEntity);
        List<UserEntity> staffAssignments = new ArrayList<UserEntity>();
        for ( AssignmentBuildingEntity assignmentBuildingEntity : assignmentBuildingEntities ){
            staffAssignments.add(assignmentBuildingEntity.getUserEntity());
        };


        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for ( UserEntity staff : staffs ){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(staff.getId());
            staffResponseDTO.setFullName(staff.getFullName());
            if ( staffAssignments.contains(staff)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }

        // sau đó quang vô quy chuẩn ResponseDTO
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");

        return responseDTO;
    }

    @PostMapping("/api/building/assignmentBuilding")
    public void getAssignmentBuilding (@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){

        BuildingEntity buildingEntity = this.buildingService.findById(assignmentBuildingDTO.getBuildingId());



        List<UserEntity> staffs = this.userService.getStaffModels(1 , "STAFF");
        // cái bài toán giờ là sẽ có 1 cái List<> nhân viên mặc định , thì khi mà dữ liệu đổ về
        // mà dữ liệu đổ về chỉ có thằng được tick thôi
        // TH1 : check ấy thằng tick với danh sách nhân viên , thằng nào tích có nghĩa là ==  với nhân viên
            // lại chia làm 2TH :
                // THa : thằng đó đã có tòn tại buildingid ==> nó đang tick ==> để nguyên
                // THb : thằng đó không tồn tại cái buildingid ==> chưa tick ==> new AssibnmentBuilding và save()
        //TH2 : check ấy thằng tick với danh sách nhân viên , thằng nào không tích tức là != nhân viên( có
        // nghĩa là không gửi về những vẫn check )
            // lại chia làm 2TH :
                //THa : thằng đó đã có tòn tại buildingid ==> xóa cái AssibnmentBuilding đó đi
                //THb : thằng đó không tồn tại cái buildingid ==> để nguyên

        // cách làm check cái List<> id đổ về với cái List<> idnv
        // vd : List data đổ về là 1,2 | vd : list<> idnv 1,2,3,4 ==> thằng đánh tích là 1,2 cho nó vô 1 cái list , thằng không tích là 3,4 cho nó vô 1 cái list
        List<Long> checkedList = new ArrayList<>(); // Danh sách đã tích
        List<Long> uncheckedList = new ArrayList<>(); // Danh sách chưa tích
        if (!assignmentBuildingDTO.getStaffs().isEmpty()){
            List<Long> idStaffs = assignmentBuildingDTO.getStaffs();
            for (UserEntity id : staffs) {
                if (idStaffs.contains(id.getId())) {
                    checkedList.add(id.getId()); // Thêm vào danh sách đã tích
                } else {
                    uncheckedList.add(id.getId()); // Thêm vào danh sách chưa tích
                }
            }

            List<AssignmentBuildingEntity> assignmentBuildingEntities = this.assignmentBuildingService.getAssignmentBuildingEntity(buildingEntity);
            List<UserEntity> staffAssignments = new ArrayList<UserEntity>();
            for ( AssignmentBuildingEntity assignmentBuildingEntity : assignmentBuildingEntities ){
                staffAssignments.add(assignmentBuildingEntity.getUserEntity());
            };

            for ( Long id : checkedList ){
                UserEntity userEntity = this.userService.getUserById(id);

                if ( !staffAssignments.contains(userEntity)){
                    AssignmentBuildingEntity NewAssignmentBuildingEntity = new AssignmentBuildingEntity();
                    NewAssignmentBuildingEntity.setUserEntity(userEntity);
                    NewAssignmentBuildingEntity.setBuildingEntity(buildingEntity);
                    this.assignmentBuildingService.save(NewAssignmentBuildingEntity);
                }
            }

            for ( Long id :uncheckedList ){
                UserEntity userEntity = this.userService.getUserById(id);
                if ( staffAssignments.contains(userEntity)){
                    this.assignmentBuildingService.deleteAssignment(userEntity,buildingEntity);
                }
            }
        } else {

            List<AssignmentBuildingEntity> assignmentBuildingEntities = this.assignmentBuildingService.getAssignmentBuildingEntity(buildingEntity);
            List<UserEntity> staffAssignments = new ArrayList<UserEntity>();
            for ( AssignmentBuildingEntity assignmentBuildingEntity : assignmentBuildingEntities ){
                staffAssignments.add(assignmentBuildingEntity.getUserEntity());
            };

            List<Long>  AllStaff = new ArrayList<>();
            for ( UserEntity sf : staffs ){
                AllStaff.add(sf.getId());
            }

            for ( Long id : AllStaff){
                UserEntity userEntity = this.userService.getUserById(id);
                if ( staffAssignments.contains(userEntity)){
                    this.assignmentBuildingService.deleteAssignment(userEntity,buildingEntity);
                }
            }
        }

        List<AssignmentBuildingEntity> assignmentBuildingEntitiesFinal = this.assignmentBuildingService.getAssignmentBuildingEntity(buildingEntity);
        System.out.println("ok");


    }

    @PostMapping("/api/building/delete-{ids}")
    public void getDeleteBuilding (@PathVariable List<Long>  ids){
       for ( Long id :ids ){
           this.buildingService.deleteBuilding(id);
       }
    }

    @PostMapping("/api/building/create")
    public void getCreateBuilding(@RequestBody BuildingDTO buildingDTO){
        this.buildingService.createBuilding(buildingDTO);
    }

    @PostMapping("/api/building/update")
    public  void getUpdateBuilding (@RequestBody BuildingDTO buildingDTO){
        this.buildingService.updateBuilding(buildingDTO);
    }
}
