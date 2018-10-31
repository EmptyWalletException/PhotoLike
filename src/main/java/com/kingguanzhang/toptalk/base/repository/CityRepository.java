package com.kingguanzhang.toptalk.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kingguanzhang.toptalk.base.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    /**
     * 将指定的城市下的所有活动的所属城市与新的城市关联;用于删除城市之前调用此方法;
     * @param oldCityId
     * @param newCityId
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update event set city_id = :newCityId where city_id = :oldCityId"
    )
    int replaceCityInAllEvent(@Param("oldCityId")Long oldCityId,@Param("newCityId")Long newCityId);

    /**
     * 将指定的城市下的所有用户的所属城市与新的城市关联,即id为1的城市;用于删除城市之前调用此方法;
     * @param oldCityId
     * @param newCityId
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update user set  city_id = :newCityId where city_id = :oldCityId"
    )
    int replaceCityInAllUser(@Param("oldCityId")Long oldCityId,@Param("newCityId")Long newCityId);

}
