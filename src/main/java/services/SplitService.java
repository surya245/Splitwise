package services;

import models.BalanceSheet;
import models.Expense;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SplitService {
    Expense expense;

    public SplitService(Expense expense) {
        this.expense = expense;
    }
    protected abstract Map<User,Double> generateShareMap();

    public void splitExpense(){
        Map<User,Double> share =  this.generateShareMap();
        List<User> sharedUsers = expense.getSharedBy();
        User paidBy = expense.getPaidBy();
        BalanceSheet bs = BalanceSheet.getInstance();

        Map<User, Map<User,Double>> balanceMap = bs.getBalanceMap();
//        Populate the map for the payer with the amount that he/she owes.
        Map<User,Double> paidByMap = balanceMap.getOrDefault(paidBy,new HashMap<>());
        double prevLent = paidByMap.getOrDefault(paidBy,0.0);
        paidByMap.put(paidBy,prevLent+expense.getAmount());

        for(User user : sharedUsers){
            double prevOwe = paidByMap.getOrDefault(user,0.0);
            paidByMap.put(user,prevOwe - share.get(user));
        }
        balanceMap.put(paidBy,paidByMap);

        for(User user : sharedUsers){
//            add the amount owed by each user to the payer in their own Map.
            if(!user.equals(paidBy)){
                Map<User,Double> userMap = balanceMap.getOrDefault(user,new HashMap<>());
                userMap.put(paidBy,userMap.getOrDefault(paidBy,0.0) + share.get(user));
                userMap.put(user,userMap.getOrDefault(user,0.0)+share.get(user));
                balanceMap.put(user,userMap);
            }
        }

        bs.setBalanceMap(balanceMap);
    }
}
