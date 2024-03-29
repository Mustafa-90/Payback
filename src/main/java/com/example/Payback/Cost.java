package com.example.Payback;
import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "groupmember_id")
    private GroupMember groupMember;

    @OneToMany
    private List<PaybackGroup> paybackGroups;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "cost")
    private List<Payment> payments;

    private Double cost;
    private String type;
    private String receipt;
    private Date time;

    public Cost(GroupMember groupMember, Double cost, String type) {
        this.groupMember=groupMember;
        this.cost=cost;
        this.type=type;
        this.payments=new ArrayList<>();
    }

    public Cost(){

    }

    public Cost(GroupMember groupMember, Double cost, String type, String receipt) {
        this.groupMember = groupMember;
        this.payments = new ArrayList<>();
        this.cost = cost;
        this.type = type;
        this.receipt = receipt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupMember getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(GroupMember groupMember) {
        this.groupMember = groupMember;
    }

    public List<PaybackGroup> getPaybackGroups() {
        return paybackGroups;
    }

    public void setPaybackGroups(List<PaybackGroup> paybackGroups) {
        this.paybackGroups = paybackGroups;
    }

    public void addPaybackGroup(PaybackGroup paybackGroup) {
        this.paybackGroups.add(paybackGroup);
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void addPayment(Payment payment) {
        this.payments.add(payment);
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Date getTime() {
        return time;
    }

}
