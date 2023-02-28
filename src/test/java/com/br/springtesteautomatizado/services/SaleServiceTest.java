package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {

    @MockBean
    private ProductServiceImp productService;
    @MockBean
    private SaleRepository saleRepository;

    @Test
    public void saveSaleTest_Void() throws Exception {
        //Arrange
        Sale sale = Mockito.mock(Sale.class);
        List<Product> productList = Collections.singletonList(Mockito.mock(Product.class));

        //Action
        saleRepository.save(sale);
        productService.subtractProducts(productList);

        //Assert
        Mockito.verify(saleRepository, Mockito.times(1)).save(Mockito.any(Sale.class));
        Mockito.verify(productService, Mockito.times(1)).subtractProducts(Mockito.anyList());

    }

}
