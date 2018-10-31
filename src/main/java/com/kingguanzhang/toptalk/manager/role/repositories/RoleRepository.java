package com.kingguanzhang.toptalk.manager.role.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.manager.role.entity.Role;

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
