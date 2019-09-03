package com.example.Payback;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Paybackgroup")
public class PaybackGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User creator;

    @Column(name = "Groupname")
    private String groupName;

    @Column(name = "Totalsum")
    private double totalSum;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "paybackGroup")
    @Column(name = "groupmember")
    private List<GroupMember> groupMembers;

    public PaybackGroup() {
    }

    public PaybackGroup(String groupName) {
        this.groupName = groupName;
        this.totalSum = 0;
        this.groupMembers = new ArrayList<>();
    }

    public PaybackGroup(long id, User creator, String groupName, double totalSum) {
        this.id = id;
        this.creator = creator;
        this.groupName = groupName;
        this.totalSum = totalSum;
    }

    public PaybackGroup(long id, String groupName, double totalSum) {
        this.id = id;
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

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

}
