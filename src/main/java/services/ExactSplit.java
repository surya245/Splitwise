package services;

import models.ExactExpense;
import models.Expense;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExactSplit extends SplitService{
    public ExactSplit(Expense expense) {
        super(expense);
    }

    @Override
    protected Map<User, Double> generateShareMap() {
        Map<User,Double> share = new HashMap<>();
        List<User> sharedBy = expense.getSharedBy();
        User paidBy = expense.getPaidBy();
        List<Double> exactShare = ((ExactExpense)expense).getAmounts();
        for(int i = 0; i < sharedBy.size(); i++){
            share.put(sharedBy.get(i),exactShare.get(i));
        }
        return share;
    }

}
