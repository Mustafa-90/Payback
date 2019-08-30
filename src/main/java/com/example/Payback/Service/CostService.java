package com.example.Payback.Service;

import com.example.Payback.Repository.CostRepository;
import com.example.Payback.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    CostRepository costRepository;


}
