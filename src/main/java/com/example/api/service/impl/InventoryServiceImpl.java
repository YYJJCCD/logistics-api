package com.example.api.service.impl;

import com.example.api.model.entity.Inventory;
import com.example.api.repository.InventoryRepository;
import com.example.api.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findByCommodityId(String commodityId) {
        return inventoryRepository.findAllByCommodityId(commodityId);
    }

    @Override
    public List<Inventory> findAllByStorageId(String storageId) {
        return inventoryRepository.findAllByStorageId(storageId);
    }

}
