package com.kingguanzhang.toptalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingguanzhang.toptalk.entity.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
