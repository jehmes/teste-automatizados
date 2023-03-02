package com.br.springtesteautomatizado.controllers;

import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PaymentProof> save(@RequestBody Sale sale) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iSaleService.saveSale(sale));
    }
}
