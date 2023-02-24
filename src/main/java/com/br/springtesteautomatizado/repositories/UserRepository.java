package com.br.springtesteautomatizado.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.springtesteautomatizado.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByCpf(String cpf);

}
