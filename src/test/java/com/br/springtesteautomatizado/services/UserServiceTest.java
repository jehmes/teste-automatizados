package com.br.springtesteautomatizado.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoExceptions;
import com.br.springtesteautomatizado.exceptions.ExisteCpfCadastrado;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void cadastrarUsuario_RetornaUsuarios() throws CpfInvalidoExceptions, ExisteCpfCadastrado {
        // cenario ou Arrange
        User user = new User("Thales", "11278342400", 26);
        User user1 = new User("Thales", "11278342401", 26);

        // acao ou Action
        User userCadastrado = service.cadastrar(user);

        // verificao ou Assert
        Assert.assertEquals(user, userCadastrado);
//		Mockito.verify(null)
    }

    @Test
    public void cadastrarUsuario_LancaExceptionDeCpfInvalido() throws ExisteCpfCadastrado {
        // cenario ou Arrange
        User user = new User("Thales", "1127842400", 26);

        // acao ou Action
        try {
            User userCadastrado = service.cadastrar(user);
            Assert.fail("Deveria lançar exceção com cpf inválido");
        } catch (CpfInvalidoExceptions e) {
            // verificao ou assert
            Assert.assertEquals(e.getClass(), CpfInvalidoExceptions.class);
        }
    }

    @Test
    public void cadastrarUsuario_LancaExceptionDeCpfJaCadastrado() throws CpfInvalidoExceptions {
        // cenario ou Arrange
        User user = new User("Thales", "11278432400", 26);
        Mockito.when(userRepository.findByCpf("11278432400")).thenReturn(user);

        // acao ou Action
        try {
            User userCadastrado = service.cadastrar(user);
            Assert.fail("Deveria lançar exceção de cpf ja cadastrado no banco");
        } catch (ExisteCpfCadastrado e) {
            // verificao ou Assert
            Assert.assertEquals(e.getClass(), ExisteCpfCadastrado.class);
        }
    }

}
