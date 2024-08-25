package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository

public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder , StringBuilder sql){
        if ( buildingSearchBuilder.getAreaTo() != null || buildingSearchBuilder.getAreaFrom() != null){
            sql.append(" INNER JOIN rentarea ON rentarea.buildingid = building.id ");
        }
        if ( buildingSearchBuilder.getStaffId() != null ){
            sql.append(" INNER JOIN assignmentbuilding ON assignmentbuilding.buildingid = building.id ");
        }
    }

    public static  void queryNormal (BuildingSearchBuilder buildingSearchBuilder , StringBuilder sql){
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields ){
                field.setAccessible(true);
                if( !field.getName().equals("rentAreaTo") &&
                        !field.getName().equals("rentAreaFrom") &&
                        !field.getName().equals("rentPriceTo") &&
                        !field.getName().equals("rentPriceFrom") &&
                        !field.getName().equals("rentTypes") &&
                        !field.getName().equals("staffId")
                ){
                    Object value = field.get(buildingSearchBuilder);
                    if ( value != null && value != "" ){
                        if (field.getType().getName().equals("java.lang.Long")){
                            sql.append(" AND building." + field.getName() + " = " + value );
                        }
                        if ( field.getType().getName().equals("java.lang.String")){
                            sql.append(" AND building." + field.getName() + " LIKE '%" + value + "%' ");
                        }
                    }

                }
            }
        }catch( Exception e){
            e.printStackTrace();
        }

    }

    public  static void querySpecial (BuildingSearchBuilder buildingSearchBuilder , StringBuilder sql ){
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        Long rentAreaFrom =  buildingSearchBuilder.getAreaFrom();
        if (rentAreaTo != null ) {

            sql.append(" AND rentarea.value <= " + rentAreaTo + " ");
        }
        if (rentAreaFrom != null) {

            sql.append(" AND rentarea.value >= " + rentAreaFrom + " ");
        }
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        if (rentPriceTo != null  ) {

            sql.append(" AND building.rentprice <= " + rentPriceTo + " ");

        }
        if( rentPriceFrom != null) {

            sql.append(" AND building.rentprice >= " + rentPriceFrom + " ");
        }
        Long staffId = buildingSearchBuilder.getStaffId();
        if ( staffId != null) {
            sql.append(" AND assignmentbuilding.staffid = " + staffId +" ");
        }

        List<String> rentTypes = buildingSearchBuilder.getTypeCode();
        if ( rentTypes != null && !rentTypes.isEmpty()) {

            // nguyen_can,noi_that tìm không ra => nhưng database có ==> fix lại lo gic , logic dưới là sai
            String answer = rentTypes.stream().map(it ->   it ).collect(Collectors.joining(","));
             sql.append(" AND building.type" + " LIKE '%" + answer + "%' ");


        }
    }
    @Override
    public Page<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder , Pageable pageable) {


        StringBuilder sql = new StringBuilder("SELECT  building.id , building.name , building.street , building.ward , building.district , building.numberofbasement , building.floorarea , building.direction , building.level , building.rentprice , building.rentpricedescription ,building.servicefee , building.brokeragefee , building.type , building.managername,building.managerphone\r\n"
                + "FROM building");
        joinTable(buildingSearchBuilder ,sql);
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        queryNormal(buildingSearchBuilder ,sql);
        querySpecial(buildingSearchBuilder ,sql );
        sql.append(" GROUP BY building.id ");

        // Pagination query
        String querySql = sql.toString();
        Query query = entityManager.createNativeQuery( querySql , BuildingEntity.class);

        // Đếm số bản ghi ( row )
        List<Object> rows = query.getResultList();
        long i = 0;
        for ( Object row : rows ){
            i+=1;
        }
        long totalResults = i;

        // setup pagination cho List<BuildingEntity>
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<BuildingEntity> answer = query.getResultList();

        return new PageImpl<>(answer, pageable, totalResults);
    }
}
