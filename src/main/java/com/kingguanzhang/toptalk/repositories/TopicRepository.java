package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {
}
