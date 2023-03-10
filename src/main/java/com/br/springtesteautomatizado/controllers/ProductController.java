package com.br.springtesteautomatizado.controllers;

import com.br.springtesteautomatizado.interfaces.IProductService;
import com.br.springtesteautomatizado.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @PostMapping("/create")
    public ResponseEntity<List<Product>> createProducts() {
        return ResponseEntity.status(HttpStatus.CREATED).body(iProductService.createProducts());
    }

}
