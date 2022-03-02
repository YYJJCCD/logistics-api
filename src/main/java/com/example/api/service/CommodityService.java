package com.example.api.service;

import com.example.api.model.entity.Commodity;

import java.util.List;

public interface CommodityService {
    List<Commodity> findLikeByName(String name);

    Commodity findById(String name);

    Commodity save(Commodity commodity) throws Exception;

    void deleteById(String id);

    void deleteByName(String name);

    List<Commodity> findAll();

    Commodity useCommodity(int value, String id) throws Exception;

    Commodity update(Commodity commodity) throws Exception;
}
