import java.util.ArrayList;

public class User {
  private static ArrayList<User> userList = new ArrayList<User>();
  
  private AltNode userNode = new AltNode(this, new AltList(), null);
  private Ticket userTicket; //holds user's ticket
  private Card userCard; //holds user's credit card
  private Interest interest; //holds user's buy order information
  private final int userID; //unique for each user
  private static int nextID = 100; //helps find the next ID
  
  //CONSTRUCTORS
  public User() {

    userID = nextID;
    nextID++;
    userList.add(this);
  }
  
  public User(Card card) {
    userCard = card;
    userID = nextID;
    nextID++;
    userList.add(this);
  }
  
  public User(Card card, Interest interest) {
    userID = nextID;
    nextID++;
    this.userCard = card;
    this.interest = interest;
    userList.add(this);
  }
  
  public User(Card card, Ticket userTicket) {
    userID = nextID;
    nextID++;
    this.userCard = card;
    this.userTicket = userTicket;
    userList.add(this);
  }

  //GETTERS AND SETTERS
  
  public Ticket getUserTicket() {
    return userTicket;
  }

  public void setUserTicket(Ticket userTicket) {
    this.userTicket = userTicket;
  }

  public Card getUserCard() {
    return userCard;
  }

  public void setUserCard(Card userCard) {
    this.userCard = userCard;
  }

  public Interest getInterest() {
    return interest;
  }

  public void setInterest(Interest interest) {
    this.interest = interest;
  }

  public int getUserID() {
    return userID;
  }
  
  public ArrayList<User> getUserList(){
    return userList;
  }
  
  public AltNode getUserNode() {
    return userNode;
  }

  public void setUserNode(AltNode userNode) {
    this.userNode = userNode;
  }

  //equals test for userID
  @Override
  public boolean equals(Object o) {
    if(!(o instanceof User)) {
      return false;
    }
    if(((User)o).getUserID() == this.userID) {
      return true;
    }
    return false;
    
  }
  
  @Override
  public String toString() {
    if(userTicket!=null) {
      return "user: " + userID + "/seller: " + userTicket;
    }
    if(interest!=null) {
      return "user: " + userID + "/buyer: " + interest;
    }
    return "user: "+ userID +"/inactive";
  }
  
  
  
  
}
