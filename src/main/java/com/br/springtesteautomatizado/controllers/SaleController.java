package com.br.springtesteautomatizado.controllers;

import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final ISaleService saleService;
    private final SaleRepository saleRepository;

    public SaleController(ISaleService saleService, SaleRepository saleRepository) {
        this.saleService = saleService;
        this.saleRepository = saleRepository;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Sale sale) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(saleService.saveSale(sale));
    }

    @GetMapping
    public ResponseEntity<Object> getAllSales() {
        return ResponseEntity.status(HttpStatus.OK).body(saleRepository.findAll());
    }
}
