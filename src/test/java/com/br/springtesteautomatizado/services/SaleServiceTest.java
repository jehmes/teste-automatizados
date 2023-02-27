package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.models.Cart;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {

    @Autowired
    private SaleService saleService;
    @Autowired
    private ProductService productService;
    @MockBean
    private SaleRepository saleRepository;
    @MockBean
    private ProductRepository productRepository;

    @Test
    public void saveSale() throws Exception {
        //Arrange
        User user = new User();
        user.setId(1);
        user.setNome("Thales");
        user.setIdade(26);
        user.setCpf("11278342400");

        List<Product> productList = Arrays.asList(
                new Product(1L, "Sapato", BigDecimal.valueOf(199.90), 1),
                new Product(2L, "Camisa", BigDecimal.valueOf(69.90), 2));

        Cart cart = new Cart();
        cart.setId(1);
        cart.setUser(user);
        cart.setProducts(productList);
        cart.setTotalPrice(BigDecimal.valueOf(269.8));

        Sale sale = new Sale();
        sale.setId(1);
        sale.setUser(user);
        sale.setCart(cart);
        sale.setTotalPrice(BigDecimal.valueOf(269.8));
        sale.setDateTime(LocalDate.now());

        Mockito.when(productRepository.findById(productList.get(0).getId())).thenReturn(Optional.of(productList.get(0)));
        Mockito.when(productRepository.findById(productList.get(1).getId())).thenReturn(Optional.of(productList.get(1)));

        //Action
        productService.subtractProducts(productList);
        saleRepository.save(sale);

        //Assert
        Mockito.verify(productRepository, Mockito.times(1)).saveAll(Mockito.anyCollection());
        Mockito.verify(saleRepository, Mockito.times(1)).save(Mockito.any(Sale.class));
        Assert.assertEquals(sale.getDateTime(), LocalDate.now());

    }

}
