package com.example.Payback;
import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "Paybackgroup")
public class PaybackGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "creator")
    private User creator;

    @Column (name = "Groupname")
    private String groupName;

    @Column (name = "Totalsum")
    private double totalSum;

    @ManyToMany
    private List<User> users;

    PaybackGroup () {
    }

    public PaybackGroup(User creator, String groupName, double totalSum) {
        this.creator = creator;
        this.groupName = groupName;
        this.totalSum = totalSum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }
}
