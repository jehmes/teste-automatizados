package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoException;
import com.br.springtesteautomatizado.exceptions.CpfCreatedExistException;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.UserRepository;
import com.br.springtesteautomatizado.validations.IValidationsCrud;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
public class UserServiceTest {

    @Autowired
    private UserServiceImp userServiceImp;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private IValidationsCrud validationsCrud;

    private User user;
    private List<User> userList;
    @Before
    public void setUp() {
        User user1 = new User("Jehmes", "11278342401", 26);
        user = new User("Thales", "22314869488", 27);

        userList = Arrays.asList(
                user,
                user1);
    }

    @Test
    public void createAValidUser() throws CpfInvalidoException, CpfCreatedExistException {
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // acao ou Action
        User userCadastrado = userServiceImp.cadastrar(user);

        // verificao ou Assert
        Assert.assertEquals(user, userCadastrado);
        verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }

    @Test
    public void throwsCpfInvalidWhenTryCreateAUser() throws CpfCreatedExistException, CpfInvalidoException {
        // cenario ou Arrange
        user.setCpf("123");
        when(validationsCrud.validarCpf(user.getCpf())).thenThrow(CpfInvalidoException.class);
        // acao ou Action
        try {
            userServiceImp.cadastrar(user);
            Assert.fail("Deveria lançar exceção com cpf inválido");
        } catch (CpfInvalidoException e) {
            // verificao ou assert
            Assert.assertEquals(e.getClass(), CpfInvalidoException.class);
        }
    }

    @Test
    public void throwsCpfAlreadyRegisteredWhenTryCreateAUser() throws CpfInvalidoException, CpfCreatedExistException {
        // cenario ou Arrange
        Mockito.when(userRepository.findByCpf("22314869488")).thenReturn(user);
        when(validationsCrud.validarSeExisteCpfCadastrado(user.getCpf())).thenThrow(CpfCreatedExistException.class);
        // acao ou Action
        try {
            userServiceImp.cadastrar(user);
            Assert.fail("Deveria lançar exceção de cpf ja cadastrado no banco");
        } catch (CpfCreatedExistException e) {
            // verificao ou Assert
            Assert.assertEquals(e.getClass(), CpfCreatedExistException.class);
        }
    }

    @Test
    public void getAllUser() {
        Mockito.when(userServiceImp.obterTodosUsuarios()).thenReturn(userList);

        //Action
        List<User> allUsers = userServiceImp.obterTodosUsuarios();

        //Assert
        Assert.assertEquals(userList, allUsers);
        verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void editUserWithValidCpf() throws CpfCreatedExistException, CpfInvalidoException {
        userServiceImp.editar(user);

        verify(validationsCrud).validarCpf(user.getCpf());
        verify(validationsCrud).validarSeExisteCpfCadastrado(user.getCpf());
    }
}
