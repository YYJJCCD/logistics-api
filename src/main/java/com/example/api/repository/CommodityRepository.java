package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.entity.Commodity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {
    List<Commodity> findCommoditiesByNameLike(String name);
    boolean existsByName(String name);
    @Transactional
    void deleteByName(String name);
}
