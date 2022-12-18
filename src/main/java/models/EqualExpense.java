package models;

import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(Double amount, User paidBy, List<User> sharedBy) {
        super(amount, paidBy, sharedBy);
    }

    public EqualExpense(String name, Double amount, User paidBy, List<User> sharedBy) {
        super(name, amount, paidBy, sharedBy);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
