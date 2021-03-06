package com.example.api.service;

import com.example.api.model.entity.Inventory;

import java.util.List;

public interface InventoryService {

    Inventory save(Inventory inventory);

    List<Inventory> findAll();

    List<Inventory> findByCommodityId(String commodityId);

    List<Inventory> findAllByStorageId(String storageId);

}
