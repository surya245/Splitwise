package models;

import java.util.List;

public class ExactExpense extends Expense{
    private List<Double> amounts;

    public List<Double> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Double> amounts) {
        this.amounts = amounts;
    }

    public ExactExpense(Double amount, User paidBy, List<User> sharedBy, List<Double> amounts) {
        super(amount, paidBy, sharedBy);
        this.amounts = amounts;
    }

    public ExactExpense(String name, Double amount, User paidBy, List<User> sharedBy, List<Double> amounts) {
        super(name, amount, paidBy, sharedBy);
        this.amounts = amounts;
    }
    public boolean isValid(){
        return (super.getAmount() == amounts.stream().mapToDouble(v -> v).sum());
    }
}
