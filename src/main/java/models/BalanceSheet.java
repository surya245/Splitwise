package models;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private static BalanceSheet balanceSheet;

    private Map<User, Map<User,Double>> balanceMap;

    public static BalanceSheet getInstance(){
        if(balanceSheet == null){
            balanceSheet = new BalanceSheet();
        }
        return balanceSheet;
    }

    private BalanceSheet(){
        balanceMap = new HashMap<>();
    }
    public Map<User, Map<User, Double>> getBalanceMap() {
        return balanceMap;
    }
    public void setBalanceMap(Map<User, Map<User, Double>> balanceMap) {
        this.balanceMap = balanceMap;
    }
}
