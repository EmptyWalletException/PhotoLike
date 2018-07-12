package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite,Long> {

    /**
     * 自定义删除语句,通过userId 和 topicId删除对应的userFavorite;
     * @param userId
     * @param topicId
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from user_favorite where  user_id= :userId and topic_id=:topicId"
           )
     void deleteFavoriteTopic(@Param("userId")Long userId,@Param("topicId")Long topicId );

    /**
     * 自定义删除语句,通过userId 和 essayId删除对应的userFavorite;
     * @param userId
     * @param essayId
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from user_favorite where  user_id= :userId and essay_id=:essayId"
    )
    void deleteFavoriteEssay(@Param("userId")Long userId,@Param("essayId")Long essayId );

    /**
     * 自定义删除语句,通过userId 和 storyId删除对应的userFavorite;
     * @param userId
     * @param storyId
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from user_favorite where  user_id= :userId and story_id=:storyId"
    )
    void deleteFavoriteStory(@Param("userId")Long userId,@Param("storyId")Long storyId );

}
