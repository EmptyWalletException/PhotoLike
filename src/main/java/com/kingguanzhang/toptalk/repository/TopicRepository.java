package com.kingguanzhang.toptalk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

    /**
     * 自定义查询语句,通过搜索栏关键字查询出所有标题或内容有关键字的topic并且分页排序;
     * @param keyword
     * @param status
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from topic where status = :status and (title like :keyword or content like :keyword)",
            countQuery = "select count(*) from topic where status = :status and (title like :keyword or content like :keyword)")
    Page<Topic> findByKeywordAndStatus(@Param("keyword")String keyword,@Param("status")Integer status,Pageable pageable);


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
     * 自定义查询语句,通过categoryId和状态Status查询出所有topic并且分页排序;
     * @param categoryId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from topic where  category_id= :categoryId and status = :status",//ORDER BY ?#{#pageable}
            countQuery = "select count(*) from topic  where  category_id= :categoryId and status = :status")
    Page<Topic> findAllByCategoryIdAndStatus(@Param("categoryId")Long categoryId,@Param("status")Integer status,Pageable pageable);


    /**
     * 自定义查询语句,查询出用户收藏的topic并且分页排序;
     * @param userId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from topic where status = 1 and id in (select topic_id from user_favorite where user_id = :userId order by id desc )",
            countQuery = "select count(*) from topic where status = 1 and id in (select topic_id from user_favorite where user_id = :userId)")
    Page<Topic> findFavoriteTopic(@Param("userId")Long userId, Pageable pageable);

    /**
     * 自定义查询语句,查询出用户点赞的topic并且分页排序;
     * @param userId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from topic where id in (select distinct topic_id from praise where user_id = :userId order by id desc )",
            countQuery = "select count(*) from topic where id in (select distinct topic_id from praise where user_id = :userId)")
    Page<Topic> findPraiseTopic(@Param("userId")Long userId, Pageable pageable);


}
