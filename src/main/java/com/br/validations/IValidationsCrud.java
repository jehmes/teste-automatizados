package com.br.validations;

import org.springframework.stereotype.Component;

import com.br.exceptions.CpfInvalidoExceptions;
import com.br.exceptions.ExisteCpfCadastrado;

@Component
public interface IValidationsCrud {

	boolean validarCpf(String cpf) throws CpfInvalidoExceptions;
	boolean validarSeExisteCpfCadastrado(String cpf) throws ExisteCpfCadastrado;
}
