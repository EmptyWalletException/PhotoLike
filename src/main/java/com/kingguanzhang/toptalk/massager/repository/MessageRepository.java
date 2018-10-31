package com.kingguanzhang.toptalk.massager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.massager.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
