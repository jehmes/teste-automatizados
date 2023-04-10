package com.br.springtesteautomatizado.validations;

import com.br.springtesteautomatizado.exceptions.InvalidCpfException;
import com.br.springtesteautomatizado.exceptions.CpfAlreadyExistException;

public interface IValidationsCrud {

	boolean validarCpf(String cpf) throws InvalidCpfException;
	boolean validarSeExisteCpfCadastrado(String cpf, Long id) throws CpfAlreadyExistException;
}
