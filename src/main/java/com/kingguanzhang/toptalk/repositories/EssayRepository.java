package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayRepository extends JpaRepository<Essay,Long> {

}
