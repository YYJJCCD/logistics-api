package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.entity.Commodity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {
    List<Commodity> findCommoditiesByName(String name);
}
