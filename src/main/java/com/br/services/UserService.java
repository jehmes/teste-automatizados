package com.br.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.exceptions.CpfInvalidoExceptions;
import com.br.exceptions.ExisteCpfCadastrado;
import com.br.models.User;
import com.br.repositories.UserRepository;
import com.br.validations.IValidationsCrud;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final IValidationsCrud validationsCrud;

	@Autowired
	public UserService(UserRepository userRepository, IValidationsCrud validationsCrud) {
		this.userRepository = userRepository;
		this.validationsCrud = validationsCrud;
	}
	
	
	public User cadastrar(User user) throws CpfInvalidoExceptions, ExisteCpfCadastrado {
		validationsCrud.validarCpf(user.getCpf());
		validationsCrud.validarSeExisteCpfCadastrado(user.getCpf());

		userRepository.save(user);

		return user;
	}

}
