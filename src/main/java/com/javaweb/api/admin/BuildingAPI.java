package com.javaweb.api.admin;


import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.BuildingType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import com.javaweb.utils.HandleUploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController

public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @Autowired
    private HandleUploadFile handleUploadFile;

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
            staffAssignments.add(assignmentBuildingEntity.getUser());
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


    @GetMapping(value = "/api/building/{id}/staffs")
    public ModelAndView getStaffModelAssignment (@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/building/assignment");
        // tìm tòa nhà có id
        BuildingEntity buildingEntity = this.buildingService.findById(id);

        // tìm tất cả các nhân viên có role là Staff và đang hoạt động
        List<UserEntity> staffs = this.userService.getStaffModels(1 , "STAFF");

        // tìm tất cả các nhân viên HIỆN đang quản lí cái id building
        List<AssignmentBuildingEntity> assignmentBuildingEntities = this.assignmentBuildingService.getAssignmentBuildingEntity(buildingEntity);
        List<UserEntity> staffAssignments = new ArrayList<UserEntity>();
        for ( AssignmentBuildingEntity assignmentBuildingEntity : assignmentBuildingEntities ){
            staffAssignments.add(assignmentBuildingEntity.getUser());
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

        mav.addObject("dataStaffs" ,staffResponseDTOS );
        mav.addObject("buildingId" ,id );

        return mav;
    }

    @PutMapping("/api/building/assignmentBuilding")
    public void getAssignmentBuilding (@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){

        BuildingEntity buildingEntity = this.buildingService.findById(assignmentBuildingDTO.getBuildingId());

        // handle delete Assignment building
        ResponseEntity<String> handleDeleteAssignment = this.assignmentBuildingService.deleteAssignmentBuilding(assignmentBuildingDTO.getBuildingId());
        if ( handleDeleteAssignment.getStatusCodeValue() == 200){
            // handle save Assignment building
            for ( Long id  :  assignmentBuildingDTO.getStaffs()){
                AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
                UserEntity userEntity = this.userService.getUserById(id);
                assignmentBuildingEntity.setUser(userEntity);
                assignmentBuildingEntity.setBuilding(buildingEntity);

              ResponseEntity<String> handleSaveAssignmentBuilding =  this.assignmentBuildingService.handleSaveAssignmentBuilding(assignmentBuildingEntity);
              if (handleSaveAssignmentBuilding.getStatusCodeValue() != 200 ){
                  System.out.println("--ER : Lỗi save assignment id staff : " + id);
                  break;
              }
            }
        }
    }

    @PostMapping("/api/building/assignmentBuildingAssigment")
    public void getAssignmentBuildingAssigment(@RequestParam("buildingId") String buildingId,
                                               @RequestParam("staffIds") List<String> staffIds){

        Long buildingIdLong = Long.valueOf(buildingId);

        BuildingEntity buildingEntity = this.buildingService.findById(buildingIdLong);

        // handle delete Assignment building
        ResponseEntity<String> handleDeleteAssignment = this.assignmentBuildingService.deleteAssignmentBuilding(buildingIdLong);
        if ( handleDeleteAssignment.getStatusCodeValue() == 200){
            // handle save Assignment building
            for ( String staff  :  staffIds){
                Long id = Long.valueOf(staff);
                AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
                UserEntity userEntity = this.userService.getUserById(id);
                assignmentBuildingEntity.setUser(userEntity);
                assignmentBuildingEntity.setBuilding(buildingEntity);

                ResponseEntity<String> handleSaveAssignmentBuilding =  this.assignmentBuildingService.handleSaveAssignmentBuilding(assignmentBuildingEntity);
                if (handleSaveAssignmentBuilding.getStatusCodeValue() != 200 ){
                    System.out.println("--ER : Lỗi save assignment id staff : " + id);
                    break;
                }
            }
        }

    }

    @DeleteMapping("/api/building/delete-{ids}")
    public void getDeleteBuilding (@PathVariable List<Long>  ids){
        //ResponseDTO responseDTO = new ResponseDTO();
        String message = "";
       for ( Long id :ids ){
           message =  this.buildingService.deleteBuilding(id).getBody();
       }
//       return responseDTO.setMessage(message);
    }

    @PostMapping ("/admin/building-edit")
    public ModelAndView getCreateBuilding(@Valid @ModelAttribute("buildingModel") BuildingDTO buildingDTO , BindingResult bindingResult, @RequestPart("imageFile") MultipartFile file  ){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingModel",buildingDTO);
        Map<String , String > districtCodes = DistrictCode.code();
        Map<String,String> rentCode = BuildingType.type();

        mav.addObject("typeDistrict" ,districtCodes );
        mav.addObject("rentCode" ,rentCode );
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        if (bindingResult.hasErrors()) {
            return mav;
        }
        // update
        if ( buildingDTO.getId() != null){
            ResponseEntity<String> rsUpdateBuidling =  this.buildingService.updateBuilding(buildingDTO,file);
            if ( rsUpdateBuidling.getStatusCodeValue() == 200 ){
                return new  ModelAndView("redirect:/admin/building-list");
            }else {
                String errorSQL = rsUpdateBuidling.getBody();
                mav.addObject("errorSQL" ,errorSQL );
                return mav;
            }
        }
        // create
        else {

            // nếu thành công thì redirect

            ResponseEntity<String> rsCreateBuidling =  this.buildingService.createBuilding(buildingDTO , file);
            if (rsCreateBuidling.getStatusCodeValue()  == 200 ){
                return new  ModelAndView("redirect:/admin/building-list");
            }else {

                String errorSQL = rsCreateBuidling.getBody();
                mav.addObject("errorSQL" ,errorSQL );
                return mav;
            }

        }
//       return null;
    }

//    @PutMapping("/api/building/update")
//    public  void getUpdateBuilding (@RequestBody BuildingDTO buildingDTO){
//        this.buildingService.updateBuilding(buildingDTO);
//    }
}
