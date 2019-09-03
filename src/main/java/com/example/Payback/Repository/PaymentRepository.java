package com.example.Payback.Repository;

import com.example.Payback.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

<<<<<<< HEAD

public interface PaymentRepository extends CrudRepository<Payment, Long> {


=======
public interface PaymentRepository extends CrudRepository<Payment, Long> {

>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8
    List<Payment> findByPayerId(long id);
}
