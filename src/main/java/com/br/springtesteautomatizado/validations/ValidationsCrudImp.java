package com.br.springtesteautomatizado.validations;


import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.springtesteautomatizado.exceptions.InvalidCpfException;
import com.br.springtesteautomatizado.exceptions.CpfAlreadyExistException;
import com.br.springtesteautomatizado.repositories.UserRepository;

@Component
public class ValidationsCrudImp implements IValidationsCrud {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public boolean validarCpf(String cpf) throws InvalidCpfException {
		if(cpf.length() < 11)
			throw new InvalidCpfException();
		return true;
	}

	@Override
	public boolean validarSeExisteCpfCadastrado(String cpf, Long id) throws CpfAlreadyExistException {
		User userFromDb = userRepository.findByCpf(cpf);
		if (userFromDb != null && !userFromDb.getId().equals(id))
			throw new CpfAlreadyExistException(UserErrorsEnum.CPF_ALREADY_EXISTS.getName());
		return true;
	}

	
}
