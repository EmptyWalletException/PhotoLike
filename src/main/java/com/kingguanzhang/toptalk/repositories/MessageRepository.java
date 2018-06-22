package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
