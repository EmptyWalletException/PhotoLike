package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Transient;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    /*
     * 自定义查询语句,通过topicId查询出所有category;分页并排序;
     * @param topicId
     * @return
    @Query(nativeQuery = true, value = "select * from category where id in (select category_id from category_topic where topic_id= :topicId)",//ORDER BY ?#{#pageable}
            countQuery = "select count(*) from category where id in (select category_id from category_topic where topic_id= :topicId)")
    Page<Category> findByTopicId(@Param("topicId")Long topicId,Pageable pageable);
    */

    /**
     * 自定义更新语句,将指定的分类下的所有topic的分类关联成新的分类;用于删除分类之前调用此方法;
     * @param oldCategoryId
     * @param newCategoryId
     * @return
     */
    @Transient
    @Modifying
    @Query(nativeQuery = true, value = "update topic set category_id = :newCategoryId where category_id = :oldCategoryId"//ORDER BY ?#{#pageable}
            )
    int replaceCategoryInTopic(@Param("oldCategoryId")Long oldCategoryId,@Param("newCategoryId")Long newCategoryId);


}
