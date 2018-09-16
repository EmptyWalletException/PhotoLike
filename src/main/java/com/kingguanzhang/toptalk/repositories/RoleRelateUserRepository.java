package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.RoleRelateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRelateUserRepository extends JpaRepository<RoleRelateUser,Long> {
}
