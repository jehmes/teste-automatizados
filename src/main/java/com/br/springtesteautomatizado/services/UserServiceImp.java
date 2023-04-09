package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.exceptions.CpfInvalidoException;
import com.br.springtesteautomatizado.exceptions.CpfAlreadyExistException;
import com.br.springtesteautomatizado.exceptions.UserNotFoundException;
import com.br.springtesteautomatizado.interfaces.IUserService;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.UserRepository;
import com.br.springtesteautomatizado.validations.IValidationsCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IValidationsCrud validationsCrud;

    @Override
    public User create(User user) throws CpfInvalidoException, CpfAlreadyExistException {
        validationsCrud.validarCpf(user.getCpf());
        validationsCrud.validarSeExisteCpfCadastrado(user.getCpf());

        return userRepository.save(user);
    }

    @Override
    public User editar(User user) throws CpfInvalidoException, CpfAlreadyExistException {
        validationsCrud.validarCpf(user.getCpf());
        validationsCrud.validarSeExisteCpfCadastrado(user.getCpf());

        return userRepository.save(user);
    }

    @Override
    public List<User> obterTodosUsuarios() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) throw new UserNotFoundException(UserErrorsEnum.USER_NOT_FOUND.getName());
        return userOptional.get();
    }

    @Override
    public Page<User> getAll(Pageable page) {
        return userRepository.findAll(page);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
