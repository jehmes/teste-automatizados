package com.br.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByCpf(String cpf);

}
