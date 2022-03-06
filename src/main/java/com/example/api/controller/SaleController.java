package com.example.api.controller;

import com.example.api.model.entity.Sale;
import com.example.api.service.SaleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_SALE')")
@RequestMapping("/api/sale")
public class SaleController {
    @Resource
    private SaleService saleService;

    @GetMapping("/findAll")
    public List<Sale> findAll(){
        return saleService.findAll();
    }

    @GetMapping("/findByCompanyName")
    public List<Sale> findByCompanyName(String companyName){
        return saleService.findByCompanyName(companyName);
    }

    @PostMapping("/save")
    public Sale save(@RequestBody Sale sale){
        System.out.println(sale);
        return saleService.save(sale);
    }
}
