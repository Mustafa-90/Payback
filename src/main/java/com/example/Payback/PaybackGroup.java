package com.example.Payback;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Paybackgroup")
public class PaybackGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    //@JoinColumn (name = "creator")
    private User creator;

    @Column (name = "Groupname")
    private String groupName;

    @Column (name = "Totalsum")
    private double totalSum;

    @OneToMany
    private List<GroupMember> groupMembers;

    public PaybackGroup () {
    }

    public PaybackGroup(User creator, String groupName, double totalSum) {
        this.creator = creator;
        this.groupName = groupName;
        this.totalSum = totalSum;
        this.groupMembers = new ArrayList<>();
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

    public void addGroupMember(GroupMember groupMember) {
        this.groupMembers.add(groupMember);
    }
}
