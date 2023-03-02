package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoException;
import com.br.springtesteautomatizado.exceptions.CpfCreatedExistException;
import com.br.springtesteautomatizado.interfaces.IUserService;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.UserRepository;
import com.br.springtesteautomatizado.validations.IValidationsCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IValidationsCrud validationsCrud;

    @Override
    public User cadastrar(User user) throws CpfInvalidoException, CpfCreatedExistException {
        validationsCrud.validarCpf(user.getCpf());
        validationsCrud.validarSeExisteCpfCadastrado(user.getCpf());

        return userRepository.save(user);
    }
    @Override
    public User editar(User user) throws CpfInvalidoException, CpfCreatedExistException {
        validationsCrud.validarCpf(user.getCpf());
        validationsCrud.validarSeExisteCpfCadastrado(user.getCpf());

        return userRepository.save(user);
    }
    @Override
    public List<User> obterTodosUsuarios() {
        return (List<User>) userRepository.findAll();
    };


}
