package com.example.api.controller;

import com.example.api.model.entity.Inventory;
import com.example.api.model.entity.InventoryRecord;
import com.example.api.model.vo.CommodityVo;
import com.example.api.service.InventoryRecordService;
import com.example.api.service.InventoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_STORAGE')")
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Resource
    private InventoryService inventoryService;

    @Resource
    private InventoryRecordService recordService;

    @GetMapping("/findAll")
    public List<Inventory> findAll() {
        return inventoryService.findAll();
    }

    @GetMapping("/analyzeCommodity")
    public List<CommodityVo> analyzeCommodity(String type) {
        return recordService.analyzeCommodity(type);
    }

    //指定仓库id
    //查询某个仓库的库存情况
    @GetMapping("/findAllByStorageId")
    public List<Inventory> findAllByStorageId(String storageId) {
        System.out.println(storageId);
        return inventoryService.findAllByStorageId(storageId);
    }

    //指定商品id
    //查询某个商品在所有仓库的库存
    @GetMapping("/findByCommodityId")
    public List<Inventory> findByCommodityId(String commodityId) {
        return inventoryService.findByCommodityId(commodityId);
    }

    //指定仓库id
    //查询某个仓库库内商品的出库入库记录


    @PostMapping("/in")
    public InventoryRecord in(@RequestBody InventoryRecord record) throws Exception {
        return recordService.in(record);
    }

    @PostMapping("/out")
    public InventoryRecord out(@RequestBody InventoryRecord record) throws Exception {
        return recordService.out(record);
    }

    @DeleteMapping("/deleteInventoryById")
    public void deleteInventoryById(String inventoryId){

    }

    @GetMapping("/record/findAllRecordByStorageId")
    public List<InventoryRecord> findAllRecordByStorageId(String storageId) {
        return recordService.findAllByStorageId(storageId);
    }

    //指定商品id
    //查询某个商品在所有仓库出库入库记录
    @GetMapping("/record/findRecordByCommodityId")
    public List<InventoryRecord> findRecordByCommodityId(String commodityId) {
        return recordService.findAllByCommodityId(commodityId);
    }

}
