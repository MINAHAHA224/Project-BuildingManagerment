package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum AssignCustomerType {

    CSKH("Chăm sóc khách hàng"),

    DDX("Dẫn đi xem căn hộ");

    private final String name;

    AssignCustomerType( String name ){
        this.name = name;
    }

   public static Map<String , String > type (){
       Map<String , String > code = new TreeMap<>();
       for (AssignCustomerType item : AssignCustomerType.values() ){
           code.put(item.toString() , item.name );

       }
       return code;
   }

}
