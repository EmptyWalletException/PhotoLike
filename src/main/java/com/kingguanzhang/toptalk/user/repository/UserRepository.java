package com.kingguanzhang.toptalk.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
