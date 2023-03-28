package com.br.springtesteautomatizado.repositories;

import com.br.springtesteautomatizado.models.PixPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PixPaymentRepository extends JpaRepository<PixPayment, Long> {
}
