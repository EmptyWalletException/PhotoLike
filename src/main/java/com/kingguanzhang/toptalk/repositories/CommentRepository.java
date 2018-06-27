package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    /**
     * 自定义查询语句,通过topicId查询出所有comment;分页并排序;
     * @param topicId
     * @return
     */
    @Query(nativeQuery = true, value = "select * from comment where supcomment_id =0 and id in (select comment_id from comment_est where topic_id= :topicId)",//ORDER BY ?#{#pageable}
            countQuery = "select count(*) from comment where supcomment_id =0 and id in (select comment_id from comment_est where topic_id= :topicId)")
    Page<Comment> findByTopicId(@Param("topicId")Long topicId, Pageable pageable);


    /**
     * 自定义查询语句,通过storyId查询出所有comment;分页并排序;
     * @param storyId
     * @return
     */
    @Query(nativeQuery = true, value = "select * from comment where supcomment_id =0 and id in (select comment_id from comment_est where story_id= :storyId)",//ORDER BY ?#{#pageable}
            countQuery = "select count(*) from comment where supcomment_id =0 and id in (select comment_id from comment_est where story_id= :storyId)")
    Page<Comment> findByStoryId(@Param("storyId")Long storyId, Pageable pageable);

}
