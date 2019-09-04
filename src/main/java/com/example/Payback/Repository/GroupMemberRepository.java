package com.example.Payback.Repository;

import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMemberRepository extends CrudRepository <GroupMember, Long > {
    List<GroupMember> findByPaybackGroupId(long id);
    List <GroupMember> findByUserId(long id);
}
