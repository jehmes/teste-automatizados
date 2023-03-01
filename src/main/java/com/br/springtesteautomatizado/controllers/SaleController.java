package com.br.springtesteautomatizado.controllers;

import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    ISaleService iSaleService;
    @PostMapping("/save")
    public void save(@RequestBody Sale sale) throws Exception {
        iSaleService.saveSale(sale);
    }
}
