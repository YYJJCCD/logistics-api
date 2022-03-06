package com.example.api.service.impl;

import com.example.api.model.entity.Commodity;
import com.example.api.model.entity.Inventory;
import com.example.api.model.entity.InventoryRecord;
import com.example.api.model.vo.CommodityVo;
import com.example.api.repository.CommodityRepository;
import com.example.api.repository.InventoryRecordRepository;
import com.example.api.repository.InventoryRepository;
import com.example.api.service.InventoryRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InventoryRecordServiceImpl implements InventoryRecordService {

    @Resource
    private InventoryRepository inventoryRepository;

    @Resource
    private CommodityRepository commodityRepository;

    @Resource
    private InventoryRecordRepository recordRepository;

    @Override
    public List<CommodityVo> analyzeCommodity(String type) {
        List<CommodityVo> result = new ArrayList<>();
        List<InventoryRecord> all = recordRepository.findAllByType(type);
        Map<String, Integer> map = new HashMap<>();
        for (InventoryRecord r : all) {
            if (map.containsKey(r.getCommodityName())) {
                map.put(r.getCommodityName(), map.get(r.getCommodityName()) + r.getCount());
            } else {
                map.put(r.getCommodityName(), r.getCount());
            }
        }
        for (String key : map.keySet()) {
            result.add(new CommodityVo(map.get(key), key));
        }
        return result;
    }

    @Override
    public List<InventoryRecord> findAllByStorageId(String storageId) {
        return recordRepository.findAllByStorageId(storageId);
    }

    @Override
    public List<InventoryRecord> findAllByCommodityId(String commodityId) {
        return recordRepository.findAllByCommodityId(commodityId);
    }

    @Override
    public InventoryRecord out(InventoryRecord record) throws Exception {

        //查找当前商品在该仓库的库存
        Inventory inventory = inventoryRepository.findByStorageIdAndCommodityId(record.getStorageId(), record.getCommodityId());
        //查询结果为空
        if (inventory == null) throw new Exception("仓库内不存在该商品");
        //比较库存
        if (inventory.getCount() < record.getCount()) throw new Exception("出库失败，库存数量不足");

        Optional<Commodity> optional = commodityRepository.findById(record.getCommodityId());
        if (optional.isEmpty()) {
            throw new Exception("不存在的商品id");
        }
        Commodity commodity = optional.get();
        commodity.setCount(commodity.getCount() - record.getCount());
        commodityRepository.save(optional.get());
        inventory.setCount(inventory.getCount() - record.getCount());

        inventoryRepository.save(inventory);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createAt = df.format(System.currentTimeMillis());
        record.setCreateAt(createAt);
        record.setType("出库");
        return recordRepository.save(record);
    }

    @Override
    public InventoryRecord in(InventoryRecord record) throws Exception {
        System.out.println(record);
        Optional<Commodity> optional = commodityRepository.findById(record.getCommodityId());
        if (optional.isEmpty()) {
            throw new Exception("不存在的商品id");
        }
        Commodity commodity = optional.get();
        commodity.setCount(commodity.getCount() + record.getCount());
        commodityRepository.save(optional.get());
        //查找当前商品在该仓库的库存
        Inventory inventory = inventoryRepository.findByStorageIdAndCommodityId(record.getStorageId(), record.getCommodityId());
        //查询结果为空
        if (inventory == null) {
            //新建该商品库存信息
            inventory = new Inventory();
            inventory.setCommodityId(record.getCommodityId());
            inventory.setStorageId(record.getStorageId());
            inventory.setCount(0);
            inventory.setCommodityName(record.getCommodityName());
        }
        inventory.setCount(inventory.getCount() + record.getCount());
        inventoryRepository.save(inventory);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createAt = df.format(System.currentTimeMillis());
        record.setCreateAt(createAt);
        record.setType("入库");
        return recordRepository.save(record);
    }

}
