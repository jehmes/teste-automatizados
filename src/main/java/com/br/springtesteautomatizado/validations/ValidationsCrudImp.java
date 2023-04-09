package com.br.springtesteautomatizado.validations;


import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoException;
import com.br.springtesteautomatizado.exceptions.CpfAlreadyExistException;
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
	public boolean validarSeExisteCpfCadastrado(String cpf) throws CpfAlreadyExistException {
		if (userRepository.findByCpf(cpf) != null)
			throw new CpfAlreadyExistException(UserErrorsEnum.CPF_ALREADY_EXISTS.getName());
		return true;
	}

	
}
