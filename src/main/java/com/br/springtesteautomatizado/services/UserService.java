package com.br.springtesteautomatizado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoExceptions;
import com.br.springtesteautomatizado.exceptions.ExisteCpfCadastrado;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.UserRepository;
import com.br.springtesteautomatizado.validations.IValidationsCrud;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IValidationsCrud validationsCrud;

	
	public User cadastrar(User user) throws CpfInvalidoExceptions, ExisteCpfCadastrado {
		validationsCrud.validarCpf(user.getCpf());
		validationsCrud.validarSeExisteCpfCadastrado(user.getCpf());

		userRepository.save(user);

		return user;
	}

}
