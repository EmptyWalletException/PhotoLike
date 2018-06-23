package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.CommentRelateEST;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRESTRepository extends JpaRepository<CommentRelateEST,Long> {
}
