package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
