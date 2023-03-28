package com.br.springtesteautomatizado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.springtesteautomatizado.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByCpf(String cpf);

}
