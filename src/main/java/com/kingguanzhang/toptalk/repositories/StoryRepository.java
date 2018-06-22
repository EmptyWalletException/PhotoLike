package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story,Long> {
}
