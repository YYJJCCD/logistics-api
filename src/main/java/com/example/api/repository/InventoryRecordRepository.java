package com.example.api.repository;

import com.example.api.model.entity.InventoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRecordRepository extends JpaRepository<InventoryRecord, String> {

    List<InventoryRecord> findAllByStorageId(String storageId);

    List<InventoryRecord> findAllByType(String type);

    List<InventoryRecord> findAllByCommodityId(String commodityId);

}
