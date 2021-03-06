package com.kingguanzhang.toptalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
