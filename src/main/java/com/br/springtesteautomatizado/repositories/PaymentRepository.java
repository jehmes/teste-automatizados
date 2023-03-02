package com.br.springtesteautomatizado.repositories;

import com.br.springtesteautomatizado.models.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
