package models;

import java.util.List;

public abstract class Expense {

    private String name = "";
    private Double amount;
    private User paidBy;
    private List<User> sharedBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<User> getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(List<User> sharedBy) {
        this.sharedBy = sharedBy;
    }



    public Expense(Double amount, User paidBy, List<User> sharedBy) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.sharedBy = sharedBy;
    }

    public Expense(String name, Double amount, User paidBy, List<User> sharedBy) {
        this.name = name;
        this.amount = amount;
        this.paidBy = paidBy;
        this.sharedBy = sharedBy;
    }
    public abstract boolean isValid();


}
