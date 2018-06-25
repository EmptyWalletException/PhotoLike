package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.Story;
import com.kingguanzhang.toptalk.entity.UserFavorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite,Long> {


}
