package com.br.springtesteautomatizado.validations;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoException;
import com.br.springtesteautomatizado.exceptions.CpfCreatedExistException;

public interface IValidationsCrud {

	boolean validarCpf(String cpf) throws CpfInvalidoException;
	boolean validarSeExisteCpfCadastrado(String cpf) throws CpfCreatedExistException;
}
