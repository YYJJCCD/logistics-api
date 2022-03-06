package com.example.api.service;

import com.example.api.model.entity.InventoryRecord;
import com.example.api.model.vo.CommodityVo;

import java.util.List;

public interface InventoryRecordService {

    //出入库排行统计
    List<CommodityVo> analyzeCommodity(String type);

    List<InventoryRecord> findAllByStorageId(String storageId);

    List<InventoryRecord> findAllByCommodityId(String commodityId);

    //出库
    InventoryRecord out(InventoryRecord record) throws Exception;

    //入库
    InventoryRecord in(InventoryRecord record) throws Exception;

}
