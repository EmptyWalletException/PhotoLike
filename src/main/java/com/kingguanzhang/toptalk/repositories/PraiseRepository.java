package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Praise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PraiseRepository extends JpaRepository<Praise,Long> {

    /**
     * 自定义删除语句,通过userId 和 commentId删除对应的praise;
     * @param userId
     * @param commentId
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from praise where  user_id= :userId and comment_id=:commentId"
    )
    void deletePraiseComment(@Param("userId")Long userId,@Param("commentId")Long commentId );

    /**
     * 自定义删除语句,通过userId 和 topicId删除对应的praise;
     * @param userId
     * @param topicId
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from praise where  user_id= :userId and topic_id=:topicId"
    )
    void deletePraiseTopic(@Param("userId")Long userId, @Param("topicId")Long topicId );

    /**
     * 自定义删除语句,通过userId 和 essayId删除对应的praise;
     * @param userId
     * @param essayId
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from praise where  user_id= :userId and essay_id=:essayId"
    )
    void deletePraiseEssay(@Param("userId")Long userId,@Param("essayId")Long essayId );

    /**
     * 自定义删除语句,通过userId 和 storyId删除对应的praise;
     * @param userId
     * @param storyId
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from praise where  user_id= :userId and story_id=:storyId"
    )
    void deletePraiseStory(@Param("userId")Long userId,@Param("storyId")Long storyId );

}
