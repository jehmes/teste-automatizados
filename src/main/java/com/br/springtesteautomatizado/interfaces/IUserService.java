package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoException;
import com.br.springtesteautomatizado.exceptions.CpfAlreadyExistException;
import com.br.springtesteautomatizado.exceptions.UserNotFoundException;
import com.br.springtesteautomatizado.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    public User create(User user) throws CpfInvalidoException, CpfAlreadyExistException;
    public User editar(User user) throws CpfInvalidoException, CpfAlreadyExistException;
    public List<User> obterTodosUsuarios();
    User findById(Long id) throws UserNotFoundException;
    Page<User> getAll(Pageable page);
    void deleteById(Long id);
}
