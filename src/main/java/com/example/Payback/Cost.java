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
    private String receiptUrl;
    private Date time;

    public Cost(User user, PaybackGroup group, List<Payment> payments, Double cost, String type, String receiptUrl) {
        this.user = user;
        this.group = group;
        this.payments = payments;
        this.cost = cost;
        this.type = type;
        this.receiptUrl = receiptUrl;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaybackGroup getGroup() {
        return group;
    }

    public void setGroup(PaybackGroup group) {
        this.group = group;
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

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    public Date getTime() {
        return time;
    }

}
