package com.br.springtesteautomatizado.validations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoException;
import com.br.springtesteautomatizado.exceptions.CpfCreatedExistException;
import com.br.springtesteautomatizado.repositories.UserRepository;

@Component
public class ValidationsCrudImp implements IValidationsCrud {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public boolean validarCpf(String cpf) throws CpfInvalidoException {
		if(cpf.length() < 11)
			throw new CpfInvalidoException();
		return true;
	}

	@Override
	public boolean validarSeExisteCpfCadastrado(String cpf) throws CpfCreatedExistException {
		if (userRepository.findByCpf(cpf) != null)
			throw new CpfCreatedExistException();
		return true;
	}

	
}
