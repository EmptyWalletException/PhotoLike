package com.kingguanzhang.toptalk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.entity.Essay;

@Repository
public interface EssayRepository extends JpaRepository<Essay,Long> {


    /**
     * 自定义查询语句,通过搜索栏关键字查询出所有标题或内容有关键字的essay并且分页排序;
     * @param keyword
     * @param status
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from essay where status = :status and (title like :keyword or content like :keyword)",
            countQuery = "select count(*) from essay where status = :status and (title like :keyword or content like :keyword)")
    Page<Essay> findByKeywordAndStatus(@Param("keyword")String keyword,@Param("status")Integer status,Pageable pageable);

    /**
     * 自定义查询语句,查询出用户收藏的essay并且分页排序;
     * @param userId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from essay where status = 1 and id in (select essay_id from user_favorite where user_id = :userId order by id desc) and status = '1'",
            countQuery = "select count(*) from essay where status = 1 and id in (select essay_id from user_favorite where user_id = :userId) and status = '1'")
    Page<Essay> findFavoriteEssay(@Param("userId")Long userId, Pageable pageable);

    /**
     * 自定义查询语句,查询出用户点赞的essay并且分页排序;
     * @param userId
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true, value = "select * from essay where id in (select distinct essay_id from praise where user_id = :userId order by id desc) and status = '1'",
            countQuery = "select count(*) from essay where id in (select distinct essay_id from praise where user_id = :userId) and status = '1'")
    Page<Essay> findPraiseEssay(@Param("userId")Long userId, Pageable pageable);
}
