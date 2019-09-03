package com.example.Payback;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Groupmember")
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "group_id")
    private PaybackGroup paybackGroup;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "groupMember")
    private List<Cost> costs;

    public GroupMember(User user, PaybackGroup paybackGroup) {
        this.user = user;
        this.paybackGroup = paybackGroup;
        this.costs = new ArrayList<>();
    }

    public GroupMember() {
        this.costs = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaybackGroup getPaybackGroup() {
        return paybackGroup;
    }

    public void setPaybackGroup(PaybackGroup paybackGroup) {
        this.paybackGroup = paybackGroup;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    public void addCost(Cost cost) {
        this.costs.add(cost);
    }
}
