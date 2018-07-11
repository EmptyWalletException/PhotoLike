package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Essay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayRepository extends JpaRepository<Essay,Long> {


    /**
     * 自定义查询语句,查询出用户收藏的essay并且分页排序;
     * @param userId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from essay where id in (select essay_id from user_favorite where user_id = :userId order by id desc)",
            countQuery = "select count(*) from essay where id in (select essay_id from user_favorite where user_id = :userId)")
    Page<Essay> findFavoriteEssay(@Param("userId")Long userId, Pageable pageable);
}
