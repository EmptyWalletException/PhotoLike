package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story,Long> {

    /**
     * 自定义查询语句,查询出用户收藏的story并且分页排序;
     * @param userId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from story where id in (select story_id from user_favorite where user_id = :userId)",
            countQuery = "select count(*) from story where id in (select story_id from user_favorite where user_id = :userId)")
    Page<Story> findFavoriteStory(@Param("userId")Long userId, Pageable pageable);
}
