package com.example.Payback.Repository;

import com.example.Payback.Payment;
import org.springframework.data.repository.CrudRepository;

<<<<<<< HEAD

public interface PaymentRepository extends CrudRepository<Payment, Long> {

=======
import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findByPayerId(long id);
>>>>>>> 63d42ee1251e69ec9cd5b7352846992101fe5898
}
