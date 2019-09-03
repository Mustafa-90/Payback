package com.example.Payback;

import javax.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "Cost_id")
    private Cost cost;

    @Column(name = "ispaybackd")
    private boolean isPaybackd;

    @Column(name = "Payerid")
    private Long payerId;
    private double sum;

    public Payment() {
    }

    public Payment(Cost cost, boolean isPaybackd, long payerId, double sum) {
        this.cost = cost;
        this.isPaybackd = isPaybackd;
        this.payerId = payerId;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public boolean isPaybackd() {
        return isPaybackd;
    }

    public void setPaybackd(boolean paybackd) {
        isPaybackd = paybackd;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
