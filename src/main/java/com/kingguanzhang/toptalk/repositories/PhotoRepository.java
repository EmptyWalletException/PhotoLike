package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
