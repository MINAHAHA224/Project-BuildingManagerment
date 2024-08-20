package com.javaweb.controller.admin;



import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.BuildingType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private  BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private BuildingConverter buildingConverter;

    @RequestMapping(value = "/admin/building-list" , method = RequestMethod.GET)
    public ModelAndView getBuildingListPage(@ModelAttribute BuildingSearchRequest buildingSearchRequest , HttpServletRequest request
                                            ){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("buildingDTOSearch" ,buildingSearchRequest);

        Map<String , String > districtCodes = DistrictCode.code();
        Map<Long,String> staffs = this.userService.getStaff( 1 , "STAFF");
        Map<String,String> rentCode = BuildingType.type();

        mav.addObject("typeDistrict" ,districtCodes );
        mav.addObject("staffs" , staffs);
        mav.addObject("rentCode" ,rentCode );


        BuildingSearchBuilder buildingSearchBuilder = this.buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingSearchResponse> buildingSearchResponses = this.buildingService.getBuildingSearch(buildingSearchBuilder );

        mav.addObject("buildingLists" , buildingSearchResponses );

        return mav;
    }

    @RequestMapping(value = "/admin/building-edit" , method = RequestMethod.GET)
    public ModelAndView getBuildingEditCreatePage (@ModelAttribute BuildingDTO buildingDTO)  {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("buildingModel" ,buildingDTO);
        Map<String , String > districtCodes = DistrictCode.code();
        Map<String,String> rentCode = BuildingType.type();

        mav.addObject("typeDistrict" ,districtCodes );
        mav.addObject("rentCode" ,rentCode );
        return mav;
    }

    @RequestMapping(value = "admin/building-edit-{id}" , method = RequestMethod.GET)
    public ModelAndView getBuildingEditUpdatePage ( @PathVariable Long id , @ModelAttribute BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingEntity buildingEntity = this.buildingService.findById(id);
        BuildingDTO newBuildingDTO = this.buildingConverter.toBuildingDTO(buildingEntity);
        mav.addObject("buildingModel" , newBuildingDTO);
        Map<String , String > districtCodes = DistrictCode.code();
        Map<String,String> rentCode = BuildingType.type();

        mav.addObject("typeDistrict" ,districtCodes );
        mav.addObject("rentCode" ,rentCode );

        return mav;
    }


}
