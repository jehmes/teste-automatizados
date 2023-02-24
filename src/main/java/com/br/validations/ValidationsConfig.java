package com.br.validations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.repositories.UserRepository;

@Configuration
public class ValidationsConfig {

	private final UserRepository userRepository;

    public ValidationsConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
    @Bean
    public ValidationsCrudImp validationsCrudImp() {
        return new ValidationsCrudImp(userRepository);
    }
}
