package com.example.api.controller;

import com.example.api.model.entity.Commodity;
import com.example.api.service.CommodityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/commodity")
public class CommodityController {
    @Resource
    private CommodityService commodityService;

    @GetMapping("/findAll")
    public List<Commodity> findAll(){
        return commodityService.findAll();
    }

    @GetMapping("/findLikeByName")
    public List<Commodity> findLikeByName(@RequestParam String name){
        return commodityService.findLikeByName(name);
    }

    @GetMapping("/findById")
    public Commodity findById(@RequestParam String id){
        return commodityService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Commodity commodity){
        commodityService.save(commodity);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam String id){
        commodityService.deleteById(id);
    }
}
