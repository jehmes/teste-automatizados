package com.br.springtesteautomatizado.repositories;

import com.br.springtesteautomatizado.models.PixPayment;
import org.springframework.data.repository.CrudRepository;

public interface PixPaymentRepository extends CrudRepository<PixPayment, Long> {
}
