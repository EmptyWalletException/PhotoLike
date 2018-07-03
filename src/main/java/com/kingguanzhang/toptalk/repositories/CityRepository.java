package com.kingguanzhang.toptalk.repositories;

import com.kingguanzhang.toptalk.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {



}
