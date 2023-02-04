
public class Ticket {
  private User bidder; // used to determine who gets the ticket upon expiration 
  //TODO: maybe change bidder to list to hedge against failed credit cards?
   
  //TODO: confused to have an instance of game in ticket, use Spring Framework or String instead of Game
  private Game ticketGame; //determines game of this ticket
  private boolean isValid = true; //placeholder that determines whether a ticket is valid or not
  //TODO: actually find a way to determine validity of tickets
  private boolean isSelling = false; //determines whether a ticket is for sale
  private double bidPrice; //current bid price
  private double bidPaid;//amount paid for ticket
  private double buyNowPrice; //instant buy now price
  private Date sellDate; //date that a ticket's bid/buy now expires
  private Date listDate; //date a ticket was listed(used as a tie breaker in compare methods)

  
  //CONSTRUCTOR
  
  public Ticket(Game ticketGame, Date listDate, Date sellDate, double bid, double buyNow) {
    //bid/buynow price must be a positive number
    if(bid<0) {
      throw new IllegalArgumentException();
    }
    if(buyNow<0) {
      throw new IllegalArgumentException();
    }
    this.listDate = listDate;
    this.ticketGame = ticketGame;
    this.sellDate = sellDate;
    this.bidPrice = bid;
    this.bidPaid = bid;
    this.buyNowPrice = buyNow;
    
  }

  //GETTERS AND SETTERS
  
  public boolean isSelling() {
    return isSelling;
  }

  public void setSelling(boolean isSelling) {
    this.isSelling = isSelling;
  }

  public double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Game getTicketGame() {
    return ticketGame;
  }

  public boolean isValid() {
    return isValid;
  }

  public double getBuyNowPrice() {
    return buyNowPrice;
  }

  public Date getSellDate() {
    return sellDate;
  }
  

  public Date getListDate() {
    return listDate;
  }
  
  public User getBidder() {
    return bidder;
  }
  
  public void setBidder(User bidder) {
    this.bidder = bidder;
  }

  public double getBidPaid() {
    return bidPaid;
  }

  public void setBidPaid(double bidPaid) {
    this.bidPaid = bidPaid;
  }

  //TODO: may be a way to use one method instead of the following two- perhaps extra parameters?
  //compares bid prices by price, expiration date, and list date
  public int compareBid(Ticket t) {
    if(Math.abs(t.getBidPrice() - this.getBidPrice()) > .001) {
      if(this.getBidPrice() > t.getBidPrice()) {
        return 1;
      }
      if(this.getBidPrice() < t.getBidPrice()) {
        return -1;
      }
    }
      
    if(this.getListDate().compareTo(t.getListDate()) >0) {
      return 1;
    }
    if(this.getListDate().compareTo(t.getListDate()) <0) {
      return -1;
    }
    
    if(this.getSellDate().compareTo(t.getSellDate()) >0) {
      return 1;
    }
    
    if(this.getSellDate().compareTo(t.getSellDate()) <0) {
      return -1;
    }

    //will rarely return 0
    return 0;
    
  }
  
  //compares buy now prices
  public int compareBuyNow(Ticket t) {
    if(Math.abs(t.getBuyNowPrice() - this.getBuyNowPrice()) > .001) {
      if(this.getBuyNowPrice() > t.getBuyNowPrice()) {
        return 1;
      }
      if(this.getBuyNowPrice() < t.getBuyNowPrice()) {
        return -1;
      }
    }   
    if(this.getSellDate().compareTo(t.getSellDate()) >0) {
      return 1;
    }
    
    if(this.getSellDate().compareTo(t.getSellDate()) <0) {
      return -1;
    }
    
    if(this.getListDate().compareTo(t.getListDate()) >0) {
      return 1;
    }
    if(this.getListDate().compareTo(t.getListDate()) <0) {
      return -1;
    }
    //will rarely return 0
    return 0;
    
  }
  
  @Override
  public String toString() {
    return "bid: " + bidPrice + ", buy now: " + buyNowPrice + ", sellDate: " + sellDate;
  }
  

  
    

}
