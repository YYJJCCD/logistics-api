package com.example.api.service.impl;

import com.example.api.model.entity.Commodity;
import com.example.api.repository.CommodityRepository;
import com.example.api.service.CommodityService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.management.Query;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityRepository commodityRepository;

    /**
     * 模糊搜索商品名获取商品列表
     * @param name 商品名
     * @return 搜索结果
     */
    @Override
    public List<Commodity> findLikeByName(String name) {
        return commodityRepository.findCommoditiesByNameLike("%" + name + "%");
    }

    /**
     * 根据id获取商品信息
     * @param id 商品id
     * @return 搜索结果
     */
    @Override
    public Commodity findById(String id) {
        return commodityRepository.findById(id).orElse(null);
    }

    /**
     * 提交商品信息
     * @param commodity 商品信息
     */
    @Override
    public Commodity save(Commodity commodity) throws Exception{
        if(commodityRepository.existsByName(commodity.getName())) throw new Exception("存在重复商品");
        if(commodity.getCount() < 0) throw new Exception("商品数量不能小于0");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime =  df.format(System.currentTimeMillis());
        commodity.setCreateAt(strTime);
        commodity.setUpdateAt(strTime);
        return commodityRepository.save(commodity);
    }

    /**
     * 根据商品id删除商品
     * @param id 商品 id
     */
    @Override
    public void deleteById(String id) {
        commodityRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        commodityRepository.deleteByName(name);
    }

    /**
     * 获取所有的商品列表
     * @return 所有的商品
     */
    @Override
    public List<Commodity> findAll() {
        return commodityRepository.findAll();
    }

    @Override
    public Commodity useCommodity(int value, String id) throws Exception {
        String updateAt = String.valueOf(System.currentTimeMillis());
        Commodity commodity = commodityRepository.findById(id).orElse(null);
        if(commodity == null) throw new Exception("商品不存在");
        if(value > commodity.getCount()) throw new Exception("商品不足");
        commodity.setUpdateAt(updateAt);
        commodity.setCount(commodity.getCount() - value);
        return commodityRepository.save(commodity);
    }

    @Override
    public Commodity update(Commodity commodity) throws Exception {
        if(!commodityRepository.existsByName(commodity.getName())) throw new Exception("修改的商品不存在");
        if(commodity.getCount() < 0) throw new Exception("商品数量不能小于0");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime =  df.format(System.currentTimeMillis());
        commodity.setUpdateAt(strTime);
        return commodityRepository.save(commodity);
    }
}
