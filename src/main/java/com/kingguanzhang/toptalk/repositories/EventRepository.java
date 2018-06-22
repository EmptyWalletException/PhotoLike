package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}
