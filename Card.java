
public class Card {
  private boolean isValid = true; //card defaults to valid TODO: find a way to check if card is valid
  private double balance; //balance of card
  private static double masterCardBalance; //balance of MY card
  
  
  //self explanatory:
  public Card() {
    balance = 0;
  }  
  public Card(double balance) {
    this.balance = balance;
  }
  public double getBalance() {
    return balance;
  }
  public void changeBalance(double change) {
    balance = balance + change;
  }
  public static double getMasterCardBalance() {
    return masterCardBalance;
  }
  public static void changeMaster(double masterCardBalance) {
    Card.masterCardBalance = Card.masterCardBalance + masterCardBalance;
  }
  
}
