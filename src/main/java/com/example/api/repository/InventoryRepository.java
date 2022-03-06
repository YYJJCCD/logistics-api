package com.example.api.repository;

import com.example.api.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {

    Inventory findByStorageIdAndCommodityId(String storageId, String commodityId);

    List<Inventory> findAllByCommodityId(String commodityId);

    List<Inventory> findAllByStorageId(String storageId);

}
