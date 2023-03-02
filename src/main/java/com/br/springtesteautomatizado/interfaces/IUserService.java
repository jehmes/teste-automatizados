package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoException;
import com.br.springtesteautomatizado.exceptions.CpfCreatedExistException;
import com.br.springtesteautomatizado.models.User;

import java.util.List;

public interface IUserService {

    public User cadastrar(User user) throws CpfInvalidoException, CpfCreatedExistException;
    public User editar(User user) throws CpfInvalidoException, CpfCreatedExistException;
    public List<User> obterTodosUsuarios();
}
