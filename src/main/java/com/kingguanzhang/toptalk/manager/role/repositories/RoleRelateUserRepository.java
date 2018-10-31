package com.kingguanzhang.toptalk.manager.role.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.manager.role.entity.RoleRelateUser;

@Repository
public interface RoleRelateUserRepository extends JpaRepository<RoleRelateUser,Long> {
}
