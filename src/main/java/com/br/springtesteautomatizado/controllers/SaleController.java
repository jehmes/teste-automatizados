package com.br.springtesteautomatizado.controllers;

import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.exceptions.UserException;
import com.br.springtesteautomatizado.interfaces.ISaleService;
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
    private ISaleService iSaleService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Sale sale) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(iSaleService.saveSale(sale));
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        } catch (PaymentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
