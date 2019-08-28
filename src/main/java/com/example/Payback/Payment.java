package com.example.Payback;

import javax.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cost cost;

    @Column(name = "ispaybackd")
    private boolean isPaybackd;

    @Column(name = "Payerid")
    private Long payerId;
    private int sum;

    public Payment() {
    }

    public Payment(Cost cost, boolean isPaybackd, Long payerId, int sum) {
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
