package services;

import models.BalanceSheet;
import models.Expense;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplit extends SplitService{
    public EqualSplit(Expense expense) {
        super(expense);
    }

    @Override
    protected Map<User, Double> generateShareMap() {
        List<User> sharedBy = expense.getSharedBy();
        int userCount = sharedBy.size();
        double splitAmount = Math.round(expense.getAmount() * 100 / userCount) /100;
        Map<User,Double> shareMap = new HashMap<>();

        shareMap.put(sharedBy.get(0),expense.getAmount() - splitAmount*(userCount - 1));

        for(int i = 1; i < userCount; i++){
            shareMap.put(sharedBy.get(i),splitAmount);
        }

        return shareMap;
    }
}
