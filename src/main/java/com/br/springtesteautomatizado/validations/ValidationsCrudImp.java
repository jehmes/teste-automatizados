package com.br.springtesteautomatizado.validations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoExceptions;
import com.br.springtesteautomatizado.exceptions.ExisteCpfCadastrado;
import com.br.springtesteautomatizado.repositories.UserRepository;

@Service
public class ValidationsCrudImp implements IValidationsCrud {
	
	@Autowired
    private UserRepository userRepository;

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
