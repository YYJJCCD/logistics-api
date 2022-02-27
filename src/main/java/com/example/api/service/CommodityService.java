package com.example.api.service;
import com.example.api.model.entity.Commodity;
import java.util.List;

public interface CommodityService {
    List<Commodity> findLikeByName(String name);
    Commodity findById(String name);
    void save(Commodity commodity);
    void deleteById(String id);
    List<Commodity> findAll();
}
