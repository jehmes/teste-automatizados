package com.br.springtesteautomatizado.repositories;

import com.br.springtesteautomatizado.models.CreditCardPayment;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardPaymentRepository extends CrudRepository<CreditCardPayment, Long> {
}
