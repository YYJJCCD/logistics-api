package com.example.api.service.impl;

import com.example.api.model.entity.Sale;
import com.example.api.repository.SaleRepository;
import com.example.api.service.SaleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Resource
    private SaleRepository saleRepository;

    @Override
    public Sale save(Sale sale) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime =  df.format(System.currentTimeMillis());
        sale.setCreateAt(strTime);
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> findByCompanyName(String companyName) {
        return saleRepository.findAllByCommodityNameLike(companyName);
    }
}
