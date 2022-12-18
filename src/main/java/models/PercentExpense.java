package models;

import java.util.List;

public class PercentExpense extends Expense{
    private List<Double> percents;

    public List<Double> getPercents() {
        return percents;
    }

    public void setPercents(List<Double> percents) {
        this.percents = percents;
    }

    public PercentExpense(Double amount, User paidBy, List<User> sharedBy, List<Double> percents) {
        super(amount, paidBy, sharedBy);
        this.percents = percents;
    }

    public PercentExpense(String name, Double amount, User paidBy, List<User> sharedBy, List<Double> percents) {
        super(name, amount, paidBy, sharedBy);
        this.percents = percents;
    }

    @Override
    public boolean isValid() {
        return (percents.stream().mapToDouble(p -> p).sum() == 100);
    }
}
