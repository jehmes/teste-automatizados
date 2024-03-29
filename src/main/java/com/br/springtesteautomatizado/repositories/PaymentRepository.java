package com.br.springtesteautomatizado.repositories;

import com.br.springtesteautomatizado.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
