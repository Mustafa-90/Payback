package com.example.Payback.Repository;

import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<PaybackGroup, Long> {
    PaybackGroup findTopByOrderByIdDesc();
}
