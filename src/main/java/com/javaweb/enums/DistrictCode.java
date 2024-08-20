package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum DistrictCode {

    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),

    QUAN_4("Quận 4"),
    ;

    private final String districtName;

    DistrictCode(String districtName){
        this.districtName = districtName;
    }

    public static Map<String , String > code ( ){
        Map<String , String > codes = new TreeMap<>();
        for (DistrictCode id : DistrictCode.values() ){
            codes.put(id.toString() , id.districtName);
        }
        return codes;
    }

}
