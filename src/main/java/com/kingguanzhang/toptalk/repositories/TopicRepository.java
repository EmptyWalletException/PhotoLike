package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {


    @Query(nativeQuery = true, value = "select * from topic  where id in (select topic_id from category_topic where category_id= :categoryId)",//ORDER BY ?#{#pageable}
            countQuery = "select count(*) from topic  where id in (select topic_id from category_topic where category_id= :categoryId)")
    Page<Topic> findByCategoryId(@Param("categoryId")Long categoryId,Pageable pageable);


}
