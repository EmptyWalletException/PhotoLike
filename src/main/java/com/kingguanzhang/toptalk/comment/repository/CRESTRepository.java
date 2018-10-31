package com.kingguanzhang.toptalk.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kingguanzhang.toptalk.comment.entity.CommentRelateEST;

@Repository
public interface CRESTRepository extends JpaRepository<CommentRelateEST,Long> {

    /**
     * 删除评论与topic对应的关系;
     * @param commentId
     * @param topicId
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from comment_est where comment_id= :commentId and topic_id = :topicId"
    )
    int deleteCommentRelateTopic(@Param("commentId")Long commentId,@Param("topicId")Long topicId);

    /**
     * 删除评论与story对应的关系;
     * @param commentId
     * @param storyId
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from comment_est where comment_id= :commentId and story_id = :storyId"
    )
    int deleteCommentRelateStory(@Param("commentId")Long commentId,@Param("storyId")Long storyId);

}

