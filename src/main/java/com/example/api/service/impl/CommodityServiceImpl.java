package com.example.api.service.impl;

import com.example.api.model.entity.Commodity;
import com.example.api.repository.CommodityRepository;
import com.example.api.service.CommodityService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


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
        return commodityRepository.findCommoditiesByName("%" + name + "%");
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
    public void save(Commodity commodity) {
        commodityRepository.save(commodity);
    }

    /**
     * 根据商品id删除商品
     * @param id 商品 id
     */
    @Override
    public void deleteById(String id) {
        commodityRepository.deleteById(id);
    }

    /**
     * 获取所有的商品列表
     * @return 所有的商品
     */
    @Override
    public List<Commodity> findAll() {
        return commodityRepository.findAll();
    }
}
