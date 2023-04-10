package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.exceptions.InvalidCpfException;
import com.br.springtesteautomatizado.exceptions.CpfAlreadyExistException;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.UserRepository;
import com.br.springtesteautomatizado.validations.IValidationsCrud;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImpTests {

    @Autowired
    private UserServiceImp userServiceImp;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private IValidationsCrud validationsCrud;

    private User user;
    private List<User> userList;
    @BeforeEach
    public void setUp() {
        User user1 = new User("Jehmes", "11278342401", 26);
        user = new User("Thales", "22314869488", 27);

        userList = Arrays.asList(
                user,
                user1);
    }

    @Test
    void shouldCreateAValidUser() throws InvalidCpfException, CpfAlreadyExistException {
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // acao ou Action
        User userCadastrado = userServiceImp.save(user);

        // verificao ou Assert
        Assert.assertEquals(user, userCadastrado);
        verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }

    @Test
    void shouldThrowsCpfInvalidWhenTryCreateAInvalidUser() throws CpfAlreadyExistException, InvalidCpfException {
        // cenario ou Arrange
        user.setCpf("123");
        when(validationsCrud.validarCpf(user.getCpf())).thenThrow(InvalidCpfException.class);
        // acao ou Action
        try {
            userServiceImp.save(user);
            Assert.fail("Deveria lançar exceção com cpf inválido");
        } catch (InvalidCpfException e) {
            // verificao ou assert
            Assert.assertEquals(e.getClass(), InvalidCpfException.class);
        }
    }

    @Test
    void shouldThrowCpfAlreadyRegisteredWhenTryCreateAInvalidUser() throws InvalidCpfException, CpfAlreadyExistException {
        // cenario ou Arrange
        Mockito.when(userRepository.findByCpf("22314869488")).thenReturn(user);
        when(validationsCrud.validarSeExisteCpfCadastrado(user.getCpf(), user.getId())).thenThrow(CpfAlreadyExistException.class);
        // acao ou Action
        try {
            userServiceImp.save(user);
            Assert.fail("Deveria lançar exceção de cpf ja cadastrado no banco");
        } catch (CpfAlreadyExistException e) {
            // verificao ou Assert
            Assert.assertEquals(e.getClass(), CpfAlreadyExistException.class);
        }
    }

    @Test
    void shouldReturnAllUsersFromDb() {
        Mockito.when(userServiceImp.obterTodosUsuarios()).thenReturn(userList);

        //Action
        List<User> allUsers = userServiceImp.obterTodosUsuarios();

        //Assert
        Assert.assertEquals(userList, allUsers);
        verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void shouldEditAValidUser() throws CpfAlreadyExistException, InvalidCpfException {
        userServiceImp.save(user);

        verify(validationsCrud).validarCpf(user.getCpf());
        verify(validationsCrud).validarSeExisteCpfCadastrado(user.getCpf(), user.getId());
    }
}
