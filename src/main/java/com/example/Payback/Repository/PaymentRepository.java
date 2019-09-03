package com.example.Payback.Repository;

import com.example.Payback.Payment;
import org.springframework.data.repository.CrudRepository;

<<<<<<< HEAD

public interface PaymentRepository extends CrudRepository<Payment, Long> {

=======
import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findByPayerId(long id);
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8
}
