package com.example.api.controller;

import com.example.api.model.entity.Commodity;
import com.example.api.service.CommodityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_COMMODITY')")
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
    public Commodity save(@RequestBody Commodity commodity) throws Exception{
        return commodityService.save(commodity);
    }

    @PostMapping("/update")
    public Commodity update(@RequestBody Commodity commodity) throws Exception{
        return commodityService.update(commodity);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(String id){
        commodityService.deleteById(id);
    }

    @DeleteMapping("/deleteByName")
    public void deleteByName(String name){
        System.out.println(name);
        commodityService.deleteByName(name);
    }
}
