package com.br.springtesteautomatizado.repositories;

import com.br.springtesteautomatizado.models.PaymentProof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentProofRepository extends JpaRepository<PaymentProof, Long> {
}
