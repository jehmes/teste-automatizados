package com.br.springtesteautomatizado.validations;

import org.springframework.stereotype.Component;

import com.br.springtesteautomatizado.exceptions.CpfInvalidoExceptions;
import com.br.springtesteautomatizado.exceptions.ExisteCpfCadastrado;

@Component
public interface IValidationsCrud {

	boolean validarCpf(String cpf) throws CpfInvalidoExceptions;
	boolean validarSeExisteCpfCadastrado(String cpf) throws ExisteCpfCadastrado;
}
