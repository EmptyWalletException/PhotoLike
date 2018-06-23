package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.CategoryRelateTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CRTRepository extends JpaRepository<CategoryRelateTopic,Long> {

}
