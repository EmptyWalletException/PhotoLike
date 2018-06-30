package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Category;
import com.kingguanzhang.toptalk.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
