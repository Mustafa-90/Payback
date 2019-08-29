package com.example.Payback.Repository;

import com.example.Payback.PaybackGroup;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<PaybackGroup, Long> {
}
