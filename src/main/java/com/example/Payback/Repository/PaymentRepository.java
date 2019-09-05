package com.example.Payback.Repository;

import com.example.Payback.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findByPayerId(long id);

    void deleteByCostId(long id);

    void deleteByIsPaybackd(boolean b);
}
