package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.models.Cart;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.SaleRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {

    @Autowired
    private SaleService saleService;
    @Autowired
    private ProductService productService;
    @MockBean
    private SaleRepository saleRepository;

    @Test
    public void saveSale() throws Exception {
        //Arrange
        Sale sale = Mockito.mock(Sale.class);
        User user = Mockito.mock(User.class);
        Cart cart = Mockito.mock(Cart.class);

        List<Product> productList = Arrays.asList(
                new Product(1, "Sapato", BigDecimal.valueOf(199.90), 1),
                new Product(2, "Camisa", BigDecimal.valueOf(69.90), 2));

        Mockito.when(user.getId()).thenReturn(1);
        Mockito.when(user.getNome()).thenReturn("Thales");
        Mockito.when(user.getIdade()).thenReturn(26);
        Mockito.when(user.getCpf()).thenReturn("11278342400");

        Mockito.when(cart.getId()).thenReturn(1);
        Mockito.when(cart.getUser()).thenReturn(user);
        Mockito.when(cart.getProducts()).thenReturn(productList);
        Mockito.when(cart.getTotalPrice()).thenReturn(BigDecimal.valueOf(269.8));

        Mockito.when(sale.getId()).thenReturn(1);
        Mockito.when(sale.getUser()).thenReturn(user);
        Mockito.when(sale.getCart()).thenReturn(cart);
        Mockito.when(sale.getTotalPrice()).thenReturn(cart.getTotalPrice());
        Mockito.when(sale.getDate()).thenReturn(LocalDate.now());

        //Action
        saleService.saveSale(cart);

        //Assert
        Mockito.verify(saleService, Mockito.times(1)).saveSale(cart);

    }

}
