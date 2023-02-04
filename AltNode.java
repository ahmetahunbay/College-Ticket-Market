import java.util.ArrayList;

public class AltNode {
  private User holder;
  private AltList selfList;
  private AltList underList;
  private AltNode upper;
  
  //CONSTRUCTOR
  public AltNode(User holder, AltList underList, AltNode upper) {
    this.holder = holder;
    this.underList = underList;
    this.upper = upper;
  }
  
  //GETTERS AND SETTERS
  public User getHolder() {
    return holder;
  }
  public AltList getSelfList() {
    return selfList;
  }

  public AltList getUnderList() {
    return underList;
  }
  public AltNode getUpper() {
    return upper;
  }
  public void setUpper(AltNode upper) {
    this.upper = upper;
  }

  public void setSelfList(AltList selfList) {
    this.selfList = selfList;
  }
  


  
}
