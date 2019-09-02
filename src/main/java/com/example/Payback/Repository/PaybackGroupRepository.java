package com.example.Payback.Repository;

import com.example.Payback.PaybackGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaybackGroupRepository extends CrudRepository<PaybackGroup, Long> {

    Double findTotalSumById(long l);
}
