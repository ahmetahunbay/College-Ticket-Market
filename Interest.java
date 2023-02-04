
public class Interest implements Comparable<Interest>{
  private Game gameInterest; //game of buy order
  private double sellNow;//sellNow price
  private Date listDate;//date of listing
  private Date expirationDate;//expiration date of sell order
  
  //CONSTRUCTOR
  
  public Interest(Game gameInterest, Date listDate, Date expirationDate, double sellNow) {
    this.gameInterest = gameInterest;
    this.sellNow = sellNow;
    this.listDate = listDate;
    this.expirationDate = expirationDate;
  }

  //GETTERS AND SETTERS
  public Game getGameInterest() {
    return gameInterest;
  }

  public double getSellNow() {
    return sellNow;
  }  
  
  public Date getListDate() {
    return listDate;
  }
  
  public Date getExpirationDate() {
    return expirationDate;
  }

  //used for max-heap
  @Override
  public int compareTo(Interest u) {
    if(Math.abs(this.getSellNow() - u.getSellNow()) > .001) {
      if(this.getSellNow() < u.getSellNow()) {
        return -1;
      }
      if(this.getSellNow()>u.getSellNow()) {
        return 1;
      }
    }
    
    if(this.getListDate().compareTo(u.getListDate()) < 0) {

      return 1;
      
    }
    if(this.getListDate().compareTo(u.getListDate()) > 0) {
      return -1;
    }
    
    if(this.getExpirationDate().compareTo(u.getExpirationDate()) < 0) {

      return 1;
    }
    if(this.getExpirationDate().compareTo(u.getExpirationDate()) > 0) {
      return -1;
    }
    
    
    return 0;
  }
  
  @Override
  public String toString() {
    return "buying for: " + sellNow + " by " + expirationDate;
  }
  
}
