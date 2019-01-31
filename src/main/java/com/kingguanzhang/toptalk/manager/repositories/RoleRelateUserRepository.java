package com.kingguanzhang.toptalk.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.manager.entity.RoleRelateUser;

@Repository
public interface RoleRelateUserRepository extends JpaRepository<RoleRelateUser,Long> {
}
