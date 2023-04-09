package com.br.springtesteautomatizado.integration;

import com.br.springtesteautomatizado.dtos.UserDto;
import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.exceptions.UserNotFoundException;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.services.UserServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTests {

    private static MockHttpServletRequest mockRequest;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${sql.script.create.user1}")
    private String sqlAddUser1;
    @Value("${sql.script.create.user2}")
    private String sqlAddUser2;
    @Value("${sql.script.delete.users}")
    private String sqlDeleteUsers;
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserDto userDto;
    private String payload;
    private final static Long USER_ID = 10L;
    @BeforeAll
    static void beforeAll() {
        mockRequest = new MockHttpServletRequest();
        mockRequest.setRequestURI("/users/" + USER_ID);
        mockRequest.setParameter("page", "0");
        mockRequest.setParameter("size", "10");
        mockRequest.setParameter("sort", "name,DESC");
    }

    @BeforeEach
    void beforeEach() throws JsonProcessingException {
        jdbcTemplate.execute(sqlAddUser1);
        jdbcTemplate.execute(sqlAddUser2);
        userDto = new UserDto("Jehmes", "12312312312", 27);
        payload = objectMapper.writeValueAsString(userDto);
    }

    @AfterEach
    void afterEach() {
        jdbcTemplate.execute(sqlDeleteUsers);
    }

    @Test
    void shouldCreateAValidUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andReturn();

        User userResponse = jsonResponseToObject(mvcResult);

        User userCreatedFromDb = userServiceImp.findById(userResponse.getId());
        assertEquals(userResponse, userCreatedFromDb);
    }

    @Test
    void shouldGetOneUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(Objects.requireNonNull(mockRequest.getRequestURI(), "Request can't be null")))
                .andExpect(status().isOk()).andReturn();

        User userResponse = jsonResponseToObject(mvcResult);

        assertEquals("12345678900", userResponse.getCpf());
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/users")
                        .param("page", mockRequest.getParameterValues("page"))
                        .param("size", mockRequest.getParameterValues("size"))
                        .param("sort", mockRequest.getParameterValues("sort")))
                .andExpect(status().isOk()).andReturn();

        Page<User> pageResponse = jsonResponseToPage(mvcResult);

        assertEquals(2, pageResponse.getContent().size());
        assertEquals(0, pageResponse.getPageable().getPageNumber());
        assertEquals(10, pageResponse.getPageable().getPageSize());
        assertEquals(2, pageResponse.getTotalElements());
    }

    @Test
    void shouldDeleteOneUserById() throws Exception {
        mockMvc.perform(delete(Objects.requireNonNull(mockRequest.getRequestURI())))
                .andExpect(status().isOk()).andReturn();

        Throwable exception = assertThrows(UserNotFoundException.class, () -> userServiceImp.findById(USER_ID));

        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
        Page<User> pageUsersList = userServiceImp.getAll(pageable);

        assertEquals(exception.getMessage(), UserErrorsEnum.USER_NOT_FOUND.getName());
        assertEquals(1, pageUsersList.getContent().size());
    }

    private User jsonResponseToObject(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String responseAsString = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(responseAsString, new TypeReference<>() {});
    }
    private Page<User> jsonResponseToPage(MvcResult mvcResult) throws IOException {
        String responseAsString = mvcResult.getResponse().getContentAsString();
        JsonNode rootNode = objectMapper.readTree(responseAsString);
        int page = rootNode.get("number").asInt();
        int size = rootNode.get("size").asInt();
        int totalElements = rootNode.get("totalElements").asInt();

        List<User> users = objectMapper.readValue(
                rootNode.get("content").traverse(),
                new TypeReference<>() {});

        return new PageImpl<>(users, PageRequest.of(page, size), totalElements);
    }
}
