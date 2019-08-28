package com.example.Payback;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String username);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByPhoneNr(String phoneNr);
}
