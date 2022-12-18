package services;

import models.BalanceSheet;
import models.User;
import models.UserMap;

import java.util.Map;

public class PrinterService {
    private static PrinterService printerService = null;
    BalanceSheet bs;
    private PrinterService() {
        bs = BalanceSheet.getInstance();
    }

    public static PrinterService getInstance(){

        if(printerService == null){
            printerService = new PrinterService();
        }

        return printerService;
    }
    public void print(User user,boolean all){
        Map<User,Double> userOweMap = bs.getBalanceMap().get(user);
        if(userOweMap == null){
            System.out.println("No Balances");
            return;
        }
//        for(User user2 : userOweMap.keySet()){
        for(User user2 : User.getUserList()){
            if(!user2.equals(user)) {
                double val = userOweMap.getOrDefault(user2,0.0);
                if (val < 0 ) {
                    if(all == false)
                        System.out.println(user2.getName() + " owes " +user.getName() +": " + (-1.0) * val);
                } else if(val > 0){
                    System.out.println(user.getName() + " owes " + user2.getName() + ": " + val);
                }
            }
        }
    }
    public void printForUser(User user){
        print(user,false);
    }
    public void printForAll(){
        if(bs.getBalanceMap().keySet().size() == 0){
            System.out.println("No Balances");
            return;
        }
        for(User user : bs.getBalanceMap().keySet()){
            print(user,true);
        }
    }
}
