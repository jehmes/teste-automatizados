package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoExceptions;
import com.br.springtesteautomatizado.exceptions.ExisteCpfCadastrado;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.UserRepository;
import com.br.springtesteautomatizado.validations.IValidationsCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IValidationsCrud validationsCrud;


	public User cadastrar(User user) throws CpfInvalidoExceptions, ExisteCpfCadastrado {
		validationsCrud.validarCpf(user.getCpf());
		validationsCrud.validarSeExisteCpfCadastrado(user.getCpf());

		return userRepository.save(user);
	}

	public User editar(User user) throws CpfInvalidoExceptions, ExisteCpfCadastrado {
		validationsCrud.validarCpf(user.getCpf());
		validationsCrud.validarSeExisteCpfCadastrado(user.getCpf());

		return userRepository.save(user);
	}

	public List<User> obterTodosUsuarios() {
		return (List<User>) userRepository.findAll();
	};

}
