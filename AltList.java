import java.util.ArrayList;

public class AltList {
  private ArrayList<AltNode> list = new ArrayList<AltNode>();
  private int size = 0;
  

  public void addAlt(User seller) {
    
    //tracks whether or not ticket has been added
    boolean added = false;
 
    //iterates through main list
    for(int i = 0; i<list.size();i++) {
      //checks for inferiority
      if(seller.getUserTicket().getBidPrice() - list.get(i).getHolder().getUserTicket().getBidPrice() > -.001 
          && seller.getUserTicket().getSellDate().compareTo(list.get(i).getHolder().getUserTicket().getSellDate())>=0) {
        added = true;
        seller.getUserNode().setUpper(list.get(i));
        seller.getUserNode().setSelfList(list.get(i).getUnderList());
        list.get(i).getUnderList().addAlt(seller);
           
        break;
      }
      
      //checks for superiority
      if(seller.getUserTicket().getBidPrice() - list.get(i).getHolder().getUserTicket().getBidPrice() <.001 && 
          seller.getUserTicket().getSellDate().compareTo(list.get(i).getHolder().getUserTicket().getSellDate())<=0) {
        
        //checks if already added, if not adds at point and sets added to true
        if(!added) {
          list.add(i,seller.getUserNode());
          seller.getUserNode().setSelfList(this);

          //for manipulating next user
          i++;
          added = true;
        }
        //removes and backs up to catch every potential removal
        seller.getUserNode().getUnderList().addAlt(list.get(i).getHolder());
        list.get(i).setUpper(seller.getUserNode());
        list.get(i).setSelfList(seller.getUserNode().getUnderList());
        list.remove(i);

        i--;
        continue;
     } 
      
      if(seller.getUserTicket().getBidPrice() - list.get(i).getHolder().getUserTicket().getBidPrice() <.001
          && !added) {
        list.add(i,seller.getUserNode());
        seller.getUserNode().setSelfList(this);


        added = true;
      }  
      
    }
    
    
    if(!added) {
      list.add(seller.getUserNode());
      seller.getUserNode().setSelfList(this);
    }
//    //if its the lowest and has not been added, it adds. also adds if empty arraylist
//    if(lower && !added) {
//      list.add(seller.getUserNode());
//    }
   
  }
  

  //GETTER METHOD
  public ArrayList<AltNode> getList() {
    return list;
  }



  public void terminate(User seller) {
    
    for(int i = 0; i<list.size(); i++) {
      if(list.get(i).getHolder().equals(seller)) {
        list.remove(i);
        break;
      }
    }
    for(int i =0; i< seller.getUserNode().getUnderList().getList().size(); i++) {
      seller.getUserNode().getUnderList().getList().get(i).setUpper(seller.getUserNode().getUpper());
      this.addAlt(seller.getUserNode().getUnderList().getList().get(i).getHolder());
      
    }
    
  }
  
  public void bidSwitch(User seller) {
    for(int i =0; i< seller.getUserNode().getUnderList().getList().size(); i++) {
      if(seller.getUserTicket().getBidPrice() > seller.getUserNode().getUnderList().getList().get(i).getHolder().getUserTicket().getBidPrice()) {
        this.addAlt(seller.getUserNode().getUnderList().getList().get(i).getHolder());
        seller.getUserNode().getUnderList().getList().remove(i);
        i--;
      }

    }

    for(int i = 0; i<seller.getUserNode().getSelfList().getList().size(); i++) {
      if(seller.getUserNode().getSelfList().getList().get(i).getHolder().equals(seller)) {
        seller.getUserNode().getSelfList().getList().remove(i);
        break;
      }
    }
    this.addAlt(seller);

  }
  
  public User getCheapest() {
    AltNode cheapest = list.get(0);
    for(int i = 0; i<list.size();i++) {
      if(list.get(i).getHolder().getUserTicket().getBidPrice() < cheapest.getHolder().getUserTicket().getBidPrice()) {
        cheapest = list.get(i);
      }
    }
    return cheapest.getHolder();
  } 
  
  public void printList() {
    for(int i = 0 ;i< list.size(); i++) {
      System.out.print(list.get(i).getHolder().getUserTicket() + " || ");
    }
    System.out.println();
  }

}
