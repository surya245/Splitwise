package services;

import models.Expense;
import models.PercentExpense;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentSplit extends SplitService{
    public PercentSplit(Expense expense) {
        super(expense);
    }

    @Override
    protected Map<User, Double> generateShareMap() {
        Map<User,Double> share = new HashMap<>();
        User paidBy = expense.getPaidBy();
        List<User> sharedBy = expense.getSharedBy();
        List<Double> percents = ((PercentExpense)expense).getPercents();
        Double amount = expense.getAmount();
        for(int i = 0; i < sharedBy.size(); i++){
            Double shareAmt = amount * percents.get(i);
            share.put(sharedBy.get(i),shareAmt/100.00);
        }
        return share;
    }
}
