package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Story;
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

    /**
     * 自定义查询语句,通过categoryId查询出所有topic并且分页排序;
     * @param categoryId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from topic where  category_id= :categoryId",//ORDER BY ?#{#pageable}
            countQuery = "select count(*) from topic  where  category_id= :categoryId")
    Page<Topic> findByCategoryId(@Param("categoryId")Long categoryId,Pageable pageable);

    /**
     * 自定义查询语句,查询出用户收藏的topic并且分页排序;
     * @param userId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from topic where id in (select topic_id from user_favorite where user_id = :userId)",
            countQuery = "select count(*) from topic where id in (select topic_id from user_favorite where user_id = :userId)")
    Page<Topic> findFavoriteTopic(@Param("userId")Long userId, Pageable pageable);


}
