import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.net.HttpURLConnection;

class Main {

  public static String userName;
  
  public static void main(String[] args) {

    Scanner stringScanner = new Scanner (System.in);
    Scanner intScanner = new Scanner (System.in);
    ArrayList <String> stockList = new ArrayList<String>();
    ArrayList <Integer> stockShares = new ArrayList<Integer>();
    Stock stockObject = new Stock();
    Account accountObject = new Account();

    try{

      System.out.println("Welcome to MarketBull, a virtual stock market program! \nYou will be able to buy and sell shares of stock here! \nYou have been given $10,000 to start your journey on the stock market!");

      setAccount(stringScanner);

      while (true) {

        System.out.println("\nMain Menu: \n\t1. View Stock List \n\t2. Add Stock \n\t3. Buy Stock");
        System.out.println("\nMake your selection:");
        int menuChoice = intScanner.nextInt();
  
        switch (menuChoice) {

          case 1:
            if (stockList.size() == 0) {

              System.out.println("You have no saved stocks! Please add stocks to the list in the main menu.");
            } else {

              Timer timer = new Timer();
              TimerTask task = new TimerTask() {
                public void run() {
                  System.out.print("\033c");
                
                  System.out.println("\nHere are all your saved stocks:");
                  for (int stockIter = 0; stockIter < stockList.size(); stockIter++) {
                    System.out.println(stockIter+1 + ". " + stockList.get(stockIter) +  "  |  " + stockObject.stockPrice(stockList.get(stockIter)) + "  |  " + stockShares.get(stockIter) + " shares");
                    
                  }
                }
              };
        
            timer.schedule(task,0,5000);
              
            }

              
          case 2:
            System.out.println("\nWhat stock would you like to add to the watchlist: ");
            String stockAdd = stringScanner.nextLine();
            stockList.add(stockAdd.toUpperCase());
            stockShares.add(0);
            
            break;

          case 3:
            
            System.out.println("The Current Price of Microsoft Inc. is: ");
            stockObject.stockPrice("MSFT");
            break;

            
        }
      }
    
      
    }catch (Exception e) {}
  }

  public static void setAccount(Scanner stringScanner) {
    
    System.out.println("\nWhat is your name?: ");
    userName = stringScanner.nextLine();
    System.out.println("\nHello " + userName + "!" + "\nYour account was successfully created!");
    
  }

}