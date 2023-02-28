package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoExceptions;
import com.br.springtesteautomatizado.exceptions.ExisteCpfCadastrado;
import com.br.springtesteautomatizado.models.User;

import java.util.List;

public interface IUserService {

    public User cadastrar(User user) throws CpfInvalidoExceptions, ExisteCpfCadastrado;
    public User editar(User user) throws CpfInvalidoExceptions, ExisteCpfCadastrado;
    public List<User> obterTodosUsuarios();
}
