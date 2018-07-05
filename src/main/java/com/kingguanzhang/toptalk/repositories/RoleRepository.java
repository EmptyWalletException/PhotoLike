package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    /**
     * 自定义查询语句,查询出用户关联的角色;
     * @param userId
     * @return
     */
    @Query(nativeQuery = true, value = "select * from role where id in (select role_id from role_user where user_id = :userId)",
            countQuery = "select count(*) from role where id in (select role_id from role_user where user_id = :userId)")
    List<Role> findRoleByUserId(@Param("userId")Long userId);
}
