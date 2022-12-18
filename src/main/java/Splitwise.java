import models.*;
import services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Splitwise {
    public static void main(String[] args) {

        User u1 = new User("u1","Surya","surya@random.in","1234");

        User u2 = new User("u2","Body","bodythebuilder@karkedikhaenge.org","2345");

        User u3 = new User("u3","Kamle","pk@raees.com","9999");

        User u4 = new User("u4","Ishan","ishan@udahb.chu","4545");

        Scanner sc= new Scanner(System.in);
        UserMap userMap = UserMap.getInstance();
        PrinterService printerService = PrinterService.getInstance();


        while(true){
            String input = sc.nextLine();
            if(input.equals("exit")){
                return;
            }
            String[] inputList = input.split(" ");
            if(inputList.length == 0 ||
                    !(inputList[0].equals("SHOW") || inputList[0].equals("EXPENSE"))) {
                System.out.println("Invalid Input!!!");
            } else if(inputList.length == 1){
                printerService.printForAll();
            } else if(inputList[0].equals("SHOW")){
                User user = userMap.getMap().get(inputList[1]);
                if(user == null){
                    System.out.println("Invalid User");
                } else {
                    printerService.printForUser(user);
                }
            } else {
                createExpense(inputList);
            }
        }
    }

    private static void createExpense(String[] inputList) {
        Expense expense;
        SplitService splitService;
        Map<String,User> stringUserMap = UserMap.getInstance().getMap();
        User paidBy = stringUserMap.get(inputList[1]);
        Double amt = Double.parseDouble(inputList[2]);
        int userCount = Integer.parseInt(inputList[3]);
        List<User> sharedBy = new ArrayList<>();
        int idx = 4;
        while(idx < userCount+4){
            sharedBy.add(stringUserMap.get(inputList[idx]));
            idx++;
        }
        switch (inputList[idx]){
            case "EQUAL":
                expense = new EqualExpense(amt,paidBy,sharedBy);
                splitService = new EqualSplit(expense);
                splitService.splitExpense();
                break;
            case "EXACT":
                List<Double> amounts = new ArrayList<>();
                idx++;
                while (idx < inputList.length){
                    amounts.add(Double.parseDouble(inputList[idx]));
                    idx++;
                }
                expense = new ExactExpense(amt,paidBy,sharedBy,amounts);
                splitService = new ExactSplit(expense);
                splitService.splitExpense();
                break;
            case "PERCENT":
                List<Double> percents = new ArrayList<>();
                idx++;
                while (idx < inputList.length){
                    percents.add(Double.parseDouble(inputList[idx]));
                    idx++;
                }
                expense = new PercentExpense(amt,paidBy,sharedBy,percents);
                splitService = new PercentSplit(expense);
                splitService.splitExpense();
        }
    }
}
