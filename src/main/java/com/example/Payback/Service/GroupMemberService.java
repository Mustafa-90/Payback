package com.example.Payback.Service;

import com.example.Payback.Repository.GroupMemberRepository;
import com.example.Payback.Repository.UserRepository;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupMemberService {
    @Autowired
    GroupMemberRepository groupMemberRepository;
    @Autowired
    UserRepository userRepository;


}
