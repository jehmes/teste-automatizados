package com.br.springtesteautomatizado.integration;

import com.br.springtesteautomatizado.exceptionHandlerController.ExceptionResponse;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTests {

    private static MockHttpServletRequest request;
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private MockMvc mockMvc;
    @Value("${sql.script.create.product1}")
    private String sqlAddProduct1;
    @Value("${sql.script.create.product2}")
    private String sqlAddProduct2;
    @Value("${sql.script.create.product3}")
    private String sqlAddProduct3;
    @Value("${sql.script.delete.products}")
    private String sqlRemoveProducts;
    @Autowired
    ProductRepository productRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    @BeforeAll
    public static void setUp() {
        request = new MockHttpServletRequest();
//        request.setParameter("name", "Novo Produto");
//        request.setParameter("price", "20");
//        request.setParameter("quantity", "12");
    }
    @BeforeEach
    public void beforeEach() {
        jdbc.execute(sqlAddProduct1);
        jdbc.execute(sqlAddProduct2);
        jdbc.execute(sqlAddProduct3);
    }
    @AfterEach
    public void afterEach() {
        jdbc.execute(sqlRemoveProducts);
    }

    @Test
    void shouldCreateProducts() throws Exception {
        List<Product> products = (List<Product>) productRepository.findAll();
        System.out.println(products);

        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Product 4");
        product.setPrice(BigDecimal.valueOf(50.00));
        product.setQuantity(5);
        productList.add(product);

        String requestBody = objectMapper.writeValueAsString(productList);

        MvcResult mvcResult = mockMvc.perform(post("/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated()).andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        List<Product> productListResponse = objectMapper.readValue(responseContent, new TypeReference<List<Product>>() {});

        assertEquals(productList, productListResponse);
    }

    @Test
    void shouldThrowDuplicateProductExceptionWhenTryToCreateProductExistingOnDatabase() throws Exception {
        List<Product> products = (List<Product>) productRepository.findAll();
        System.out.println(products);

        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("Tênis");
        product.setPrice(BigDecimal.valueOf(50.00));
        product.setQuantity(5);
        productList.add(product);

        String requestBody = objectMapper.writeValueAsString(productList);

        MvcResult mvcResult = mockMvc.perform(post("/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict()).andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        ExceptionResponse exceptionResponse = objectMapper.readValue(responseContent, new TypeReference<>() {});

        Assert.assertEquals(exceptionResponse.getMessage(), "Product already exists");
    }
}
