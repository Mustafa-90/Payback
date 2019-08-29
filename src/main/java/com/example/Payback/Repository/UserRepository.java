package com.example.Payback.Repository;

import com.example.Payback.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String username);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByPhoneNr(String phoneNr);
}
