package com.example.Payback.Repository;

import com.example.Payback.Cost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CostRepository extends CrudRepository<Cost, Long> {

    List<Cost> findByGroupMemberId(long id);

}
