import java.util.ArrayList;
public class Game {
  private final String gameName; //name of the game
  private final Date gameDate; //date of game
  private double totalBid; //bid price of game
  private double totalBuyNow; //buy now price of game
  private double totalSellNow; //sell now price of game
//  private User[] bidList; //list used as min-heap for bid ticket
//  private int bidSize = 0;//size of list
  private User[] buyNowList;//list used as min-heap for buy now ticket
  private int buyNowSize = 0;//size of list
  private User[] askList; //list used as max-heap for sell now price
  private int askListSize = 0; //size of list
//  private ArrayList<User> alternates;//list for alternate bid deals TODO: create the new data structure!!!
  
  private AltList alternates = new AltList();
  
  //CONSTRUCTOR
  
  public Game(String name, Date date) {
    this.gameName = name;
    this.gameDate = date;
    //TODO find a better way to determine sizes- shadow array?
//    bidList = new User[1000];
    buyNowList = new User[1000];
    askList = new User[1000];
  //  alternates = new ArrayList<User>();
    
  }

  public Date getGameDate() {
    return gameDate;
  }
  
  //adds seller to bid and buynow lists TODO: also add to alternates
  public void addSeller(User seller) {
    seller.getUserTicket().setSelling(true);
    
    alternates.addAlt(seller);
    
//    bidList[bidSize] = seller;
//    bidSize++;
//    percolateBidUp(bidSize-1);
    
    buyNowList[buyNowSize] = seller;
    buyNowSize++;
    percolateBuyNowUp(buyNowSize-1);

  }
  
  //adds buyer to sellnow list 
  public void addBuyer(User buyer) {
    askList[askListSize] = buyer;
    askListSize++;
    percolateAskUp(askListSize-1);
  }
  
  //PERCOLATIONS FOR 3 LISTS TODO: perhaps find a more efficient way to do this- perhaps more parameters
  
//  private void percolateBidUp(int i) {
//    int parent = (i - 1)/2;
//    User temp;
//    try {
//      while(bidList[parent].getUserTicket().compareBid(bidList[i].getUserTicket())<0) {
//        temp = bidList[i];
//        bidList[i] = bidList[parent];
//        bidList[parent] = temp;
//        i = parent;
//        parent = (i-1)/2;
//      }
//    }catch(NullPointerException e) {
//      
//    }
//    
//  }
  
  private void percolateBuyNowUp(int i) {
    int parent = (i - 1)/2;
    User temp;
    try {
      while(buyNowList[parent].getUserTicket().compareBuyNow(buyNowList[i].getUserTicket())>0) {
        temp = buyNowList[i];
        buyNowList[i] = buyNowList[parent];
        buyNowList[parent] = temp;
        i = parent;
        parent = (i-1)/2;
      }
    }catch(NullPointerException e) {
      
    }
    
  }
  
  private void percolateAskUp(int i) {
    int parent = (i - 1)/2;
    User temp;
    try {
      while(askList[parent].getInterest().compareTo(askList[i].getInterest())<0) {
        temp = askList[i];
        askList[i] = askList[parent];
        askList[parent] = temp;
        i = parent;
        parent = (i-1)/2;
      }
    }catch(NullPointerException e) {
      
    }
    
  }
  
  //GETTER METHODS FOR 3 MAIN DISPLAYED PRICES TODO: must add alternates
//  public User getTotalBid() {
//    //check if empty
//    if(bidSize!= 0) {
//    return bidList[0];
//    }
//    return null;
//  }
//  
//  public User getTotalBuyNow() {
//    //check if empty
//    if(buyNowSize!= 0) {
//    return buyNowList[0];
//    }
//    return null;
//  }
//  
//  public User getTotalSellNow() {
//    //check if empty
//    if(askListSize!= 0) {
//    return askList[0];
//    }
//    return null;
//  }
  
  //buyer buys cheapest ticket from buy now list
  public void buysNow(User buyer) {
    //money exchange
    buyer.getUserCard().changeBalance(-buyNowList[0].getUserTicket().getBuyNowPrice());
    buyNowList[0].getUserCard().changeBalance(buyNowList[0].getUserTicket().getBuyNowPrice());
    
    //bidList termination
    buyNowList[0].getUserNode().getSelfList().terminate(buyNowList[0]);
    
    //ticket exchange    
    buyNowList[0].getUserTicket().setSelling(false);
    buyer.setUserTicket(buyNowList[0].getUserTicket());
    buyNowList[0].setUserTicket(null);
    
    //adjust buyNow
    buyNowList[0] = buyNowList[buyNowSize-1];
    buyNowList[buyNowSize-1] = null;
    buyNowSize--;
    buysNowPercDown(0);
    

    
    
  }
  
  //buyNowList downward percolation method
  private void buysNowPercDown(int i) {
    try {
      //compares ticket to children( OR saves from unnecessary nullpointerexception)
      while(buyNowList[i].getUserTicket().compareBuyNow(buyNowList[2*i + 1].getUserTicket())>0 ||
          buyNowList[i].getUserTicket().compareBuyNow(buyNowList[2*i + 2].getUserTicket())>0) {
        //checks if right child is nonexistent or left is less
        if(buyNowList[2*i + 2] == null 
            || buyNowList[2*i + 1].getUserTicket().compareBuyNow(buyNowList[2*i + 2].getUserTicket()) <0) {
          User temp = buyNowList[i];
          buyNowList[i] = buyNowList[2*i + 1];
          buyNowList[2*i + 1] = temp;
          i = 2*i + 1;
          //else switches right
        } else {
          User temp = buyNowList[i];
          buyNowList[i] = buyNowList[2*i + 2];
          buyNowList[2*i + 2] = temp;
          i = 2*i + 2;
        }
      }
    //if both are null exits
    } catch(NullPointerException e) {
      
    }
    
  }
  
  //user bids on cheapest ticket in bidList
  public void bids(User bidder, double amount, User seller) {
    
    
    seller.getUserTicket().setBidPaid(amount);
    //TODO: figure out BS with bidPrice
    seller.getUserTicket().setBidPrice(amount + .5);
    alternates.bidSwitch(seller);
    
    
    seller.getUserTicket().setBidder(bidder);
    
    
//    bidList[0].getUserTicket().setBidPrice(amount);
//    
//    bidPercDown(0);
    
    
    
  }
  
  //downward percolation for bidList
//  private void bidPercDown(int i){
//    try {
//      //compares ticket to children( OR saves from unnecessary nullpointerexception)
//      while(bidList[i].getUserTicket().compareBid(bidList[2*i + 1].getUserTicket())>0 ||
//          bidList[i].getUserTicket().compareBid(bidList[2*i + 2].getUserTicket())>0) {
//      //checks if right child is nonexistent or left is less
//        if(bidList[2*i + 2] == null 
//            || bidList[2*i + 1].getUserTicket().compareBid(bidList[2*i + 2].getUserTicket()) <0) {
//          User temp = bidList[i];
//          bidList[i] = bidList[2*i + 1];
//          bidList[2*i + 1] = temp;
//          i = 2*i + 1;
//        //else switches right
//        } else {
//          User temp = bidList[i];
//          bidList[i] = bidList[2*i + 2];
//          bidList[2*i + 2] = temp;
//          i = 2*i + 2;
//        }
//      }
//      //if both are null exits
//      } catch(NullPointerException e) {
//        
//      }
//    
//  }
  
  //executes an ask order
  public void sellsNow(User seller){
    //in case the ticket is already listed
    if(seller.getUserTicket().isSelling()) {
      //TODO remove from bid,buynow, and alternate lists
      for(int i = 0; i< buyNowSize; i++) {
        if(buyNowList[i].equals(seller)) {
          buyNowList[i] = buyNowList[buyNowSize -1];
          buyNowList[buyNowSize-1] = null;
          buyNowSize--;
          buysNowPercDown(i);
          break;
        }
      }
      
      seller.getUserNode().getSelfList().terminate(seller);
      
      
      
    
    }
      //ticket exchange
      askList[0].setUserTicket(seller.getUserTicket());
      seller.setUserTicket(null);
      
      //balance change
      seller.getUserCard().changeBalance(askList[0].getInterest().getSellNow());
      askList[0].getUserCard().changeBalance(-askList[0].getInterest().getSellNow());
      
      //updates user interest/asklist
      askList[0].setInterest(null);
      askList[0] = askList[askListSize-1];
      askList[askListSize-1] = null;
      askListSize--;
      askListPercDown(0);
      
    
    
  }
  
  //percolates down asklist
  private void askListPercDown(int i) {
    try {
    //compares ticket to children( OR saves from unnecessary nullpointerexception)
      while(askList[i].getInterest().compareTo(askList[2*i + 1].getInterest())<0 ||
          askList[i].getInterest().compareTo(askList[2*i + 2].getInterest())<0) {
        //checks if right child is nonexistent or left is less
        if(askList[2*i + 2] == null 
            || askList[2*i + 1].getInterest().compareTo(askList[2*i + 2].getInterest()) >0) {
          User temp = askList[i];
          askList[i] = askList[2*i + 1];
          askList[2*i + 1] = temp;
          i = 2*i + 1;
        //else switches right
        } else {
          User temp = askList[i];
          askList[i] = askList[2*i + 2];
          askList[2*i + 2] = temp;
          i = 2*i + 2;
        }
      }
      //if both are null exits
      } catch(NullPointerException e) {
        
      }
    
  }
  
  
  //executes when a bid reaches its expiration date
  //TODO: add similar method for buyNow/sellNow expiration
  public void finishAuction(User seller) {  
    
    //MONEY MAKER
    
    //checks if sellnow price is greater than bidPrice(bidPaid could lead to some unfairness)
    if(askList[0].getInterest().getSellNow() >= seller.getUserTicket().getBidPrice()) {

      
      //difference between sellNow and bidPaid(that I keep)
      double spread = askList[0].getInterest().getSellNow() - seller.getUserTicket().getBidPaid();
      
      //change balances
      Card.changeMaster(spread);
      seller.getUserCard().changeBalance(seller.getUserTicket().getBidPaid());
      askList[0].getUserCard().changeBalance(-askList[0].getInterest().getSellNow());
             
      //change intentions/tickets
      seller.getUserTicket().setSelling(false);
      askList[0].setUserTicket(seller.getUserTicket());
      seller.setUserTicket(null);
      askList[0].setInterest(null);
      
      //perc from asklist and bidlist
      askList[0] = askList[askListSize-1];
      askList[askListSize-1] = null;
      askListSize--;
      askListPercDown(0);
      
      seller.getUserNode().getSelfList().terminate(seller);        
      
      //breaks
      return;
    }
    
    //checks that someone has bid on ticket
    if(seller.getUserTicket().getBidder() != null) {
      
      //removes from buysNowList
      for(int i = 0; i< buyNowSize; i++) {
        if(buyNowList[i].equals(seller)) {
          buyNowList[i] = buyNowList[buyNowSize -1];
          buyNowList[buyNowSize-1] = null;
          buyNowSize--;
          buysNowPercDown(i);
          break;
        }
      }    
      
      //ticket transaction
      User buyer = seller.getUserTicket().getBidder();
      seller.getUserTicket().setSelling(false);
      buyer.setUserTicket(seller.getUserTicket());   
      
      //money transaction
      buyer.getUserCard().changeBalance(-seller.getUserTicket().getBidPaid());
      seller.getUserCard().changeBalance(seller.getUserTicket().getBidPaid());
      
      //nullifies interest/ticket- interest may be unnecessary
      seller.setUserTicket(null);
      buyer.setInterest(null);

    }
    
    //otherwise just a flat termination
    seller.getUserNode().getSelfList().terminate(seller);
  }
  
  
  //terminates buysNow
  public void buysNowTermination(User seller) {
    for(int i = 0; i< buyNowSize; i++) {
      if(buyNowList[i].equals(seller)) {
        buyNowList[i] = buyNowList[buyNowSize -1];
        buyNowList[buyNowSize-1] = null;
        buyNowSize--;
        buysNowPercDown(i);
        break;
      }
    }
  }
  
  //terminates ask
  public void sellNowTermination(User buyer) {
    for(int i = 0; i< askListSize; i++) {
      if(askList[i].equals(buyer)) {
        askList[i] = askList[askListSize -1];
        askList[askListSize-1] = null;
        askListSize--;
        askListPercDown(i);
        break;
      }
    }
  }
  

  
  
  //sort bid by date
  
  
  
  //getter for buyNow User
  public User getTopBuyNow() {
    return buyNowList[0];
  }
  
  //getter for sellNow User
  public User getTopSellNow() {
    return askList[0];
  }
  
  //getter for bidList
  public AltList getBidList() {
    return alternates;
  }
  

  
}
