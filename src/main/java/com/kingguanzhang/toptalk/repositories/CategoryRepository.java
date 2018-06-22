package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
