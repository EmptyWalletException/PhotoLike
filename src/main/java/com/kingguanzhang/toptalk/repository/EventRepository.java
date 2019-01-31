package com.kingguanzhang.toptalk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    /**
     * 自定义查询语句,通过cityId查询出所有event并且分页排序;
     * @param cityId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from event where  city_id= :cityId",//ORDER BY ?#{#pageable}
            countQuery = "select count(*) from event  where  city_id= :cityId")
    Page<Event> findByCategoryId(@Param("cityId")Long cityId, Pageable pageable);

}
