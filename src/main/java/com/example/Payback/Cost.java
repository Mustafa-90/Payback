package com.example.Payback;
import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Entity
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private PaybackGroup group;

    @OneToMany
    private List<Payment> payments;

    private Double cost;
    private String type;
    private Image receipt;
    private Date time;

    public Cost(List<User> users, List<PaybackGroup> groups, List<Payment> payments, Double cost, String type, Image receipt) {
        this.users = users;
        this.groups = groups;
        this.payments = payments;
        this.cost = cost;
        this.type = type;
        this.receipt = receipt;
        this.time = new Date(System.currentTimeMillis());
    }

    public Cost() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<PaybackGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<PaybackGroup> groups) {
        this.groups = groups;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
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

    public Image getReceipt() {
        return receipt;
    }

    public void setReceipt(Image receipt) {
        this.receipt = receipt;
    }

    public Date getTime() {
        return time;
    }

}
