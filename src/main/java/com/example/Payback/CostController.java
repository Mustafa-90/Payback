package com.example.Payback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CostController {

        @Autowired
        CostRepository costRepository;


}
