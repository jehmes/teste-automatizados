package com.br.validations;


import org.springframework.stereotype.Service;

import com.br.exceptions.CpfInvalidoExceptions;
import com.br.exceptions.ExisteCpfCadastrado;
import com.br.repositories.UserRepository;

@Service
public class ValidationsCrudImp implements IValidationsCrud {
	
	private final UserRepository userRepository;
	
	public ValidationsCrudImp (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean validarCpf(String cpf) throws CpfInvalidoExceptions {
		if(cpf.length() < 11)
			throw new CpfInvalidoExceptions();
		return true;
	}

	@Override
	public boolean validarSeExisteCpfCadastrado(String cpf) throws ExisteCpfCadastrado {
		if (userRepository.findByCpf(cpf) != null)
			throw new ExisteCpfCadastrado();
		return true;
	}

	
}
