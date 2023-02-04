//kill me

public class ProjectTesters {
  
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////DATE TESTER/////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
  
  public static boolean dateTester() {
    Date base = new Date(8,23, 2);
    
    Date laterTime = new Date(9, 22, 1);
    Date laterDay = new Date(7,24,1);
    Date laterMonth = new Date(7,21,5);
    Date dayOverTime = new Date(7, 24, 2);
    Date dayTest = new Date(5, 23, 2);
    Date same = new Date(8,23,2);
        
    
    if(!(base.compareTo(laterTime) >0 && base.compareTo(laterDay) > 0 
        && base.compareTo(laterMonth) <0 && base.compareTo(dayOverTime) < 0 && base.compareTo(dayTest) >0 
        && base.compareTo(same) == 0)) {
      return false;
    }
    return true;
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////USER TESTER/////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////  
  
  public static boolean userTester() {
    User idTest = new User();
    User idTest2 = new User();
    if(idTest.getUserID() != 100 || idTest2.getUserID()!=101) {
      return false;
    }
    if(!idTest.equals(idTest)) {
      return false;
    }
    
    
    return true;
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////TICKET TESTER/////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////  
  
  public static boolean ticketTester() {
    Ticket cheapEarlyTicket = new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,4,9),10,10);
    Ticket expEarlyTicket = new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,1,9),10,20);
    Ticket cheapLateTicket = new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,10,9),10,5);
    Ticket sellDateDiff = new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,10,9), new Date(6,4,9),10,10);
    Ticket listDateDiff = new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,10,9),10,10);
    
    Ticket sameTicket = new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,4,9),10,10);
    
    if(!(cheapEarlyTicket.compareBuyNow(expEarlyTicket) <0 && cheapEarlyTicket.compareBuyNow(cheapLateTicket)>0 
        && cheapEarlyTicket.compareBuyNow(sellDateDiff) <0 && cheapEarlyTicket.compareBuyNow(sameTicket) == 0)
        && cheapEarlyTicket.compareBuyNow(listDateDiff) < 0) {
      return false;
    }
    
    
    
    
    return true;
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////INTEREST TESTER///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////  
  
  public static boolean interestTester() {
    Interest highEarlyInt = new Interest(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,4,9),50);
    Interest lowEarlyInt = new Interest(new Game("bee", new Date(12,15,8)), new Date(6,5,9), new Date(6,4,9),25);
    Interest highLateInt = new Interest(new Game("bee", new Date(12,17,8)), new Date(6,10,9), new Date(6,4,9),50);
    Interest highLateInt2 = new Interest(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,5,9),50);
    Interest same = new Interest(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,4,9),50);
    
    if(!(highEarlyInt.compareTo(lowEarlyInt) >0 && highEarlyInt.compareTo(highLateInt) >0 
        && highEarlyInt.compareTo(highLateInt2) > 0 && highEarlyInt.compareTo(same) == 0)) {

      return false;
    }
    
    return true;
  }
 
////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////ALTLIST TESTER///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
  
  public static boolean altListTester() {
    
//////TESTS ADDALT()////////////////////////////////////////////////////////////////////////////////
    AltList base = new AltList();
    User bean = new User(new Card(), new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,17,8),5,10));    
    User poo = new User(new Card(), new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,20,6),10,10));    
    User eat = new User(new Card(), new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,4,5),15,10));
    User shoe = new User(new Card(), new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,18,9),6,10));    
    User ell = new User(new Card(), new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,20,8),8,10));
    User leap = new User(new Card(), new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,8,5),20,10));    
    User made = new User(new Card(), new Ticket(new Game("bee", new Date(12,17,8)), new Date(6,5,9), new Date(6,25,8),9,10));
    
    //add to empty list
    base.addAlt(shoe);
    
    //add valid to nonempty list(3x)
    base.addAlt(ell);
    base.addAlt(poo);
    base.addAlt(eat);
    
    //add one that replaces 2
    base.addAlt(bean);

    if(base.getList().size() != 3) {
      return false;
    }
    
    //add one that gets replaced
    base.addAlt(leap);
    
    //add one that gets deep replaced
    base.addAlt(made);
    
    if(!(base.getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("17/8(6)")
        && base.getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("20/6(6)")
        && base.getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("4/5(6)")
        && bean.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("18/9(6)")
        && bean.getUserNode().getUnderList().getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("20/8(6)")
        && eat.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("8/5(6)")
        && ell.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("25/8(6)")
        && base.getList().size() ==3 && bean.getUserNode().getUnderList().getList().size() == 2
        && poo.getUserNode().getUnderList().getList().size() == 0 && leap.getUserNode().getUnderList().getList().size() == 0
        && shoe.getUserNode().getUnderList().getList().size() == 0 && made.getUserNode().getUnderList().getList().size() == 0
        && ell.getUserNode().getUnderList().getList().size() == 1 && eat.getUserNode().getUnderList().getList().size() == 1
        )) {
      return false;
    }
    
    
    
//////TESTS BIDSWITCH///////////////////////////////////////////////////////////////////////////////
    ell.getUserNode().getSelfList().bidSwitch(ell);
    if(!(base.getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("17/8(6)")
        && base.getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("20/6(6)")
        && base.getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("4/5(6)")
        && bean.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("18/9(6)")
        && bean.getUserNode().getUnderList().getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("20/8(6)")
        && eat.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("8/5(6)")
        && ell.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("25/8(6)"))) {
      return false;
    }
    
    ell.getUserTicket().setBidPrice(10);
    ell.getUserNode().getSelfList().bidSwitch(ell);
    if(!(base.getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("17/8(6)")
        && base.getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("20/6(6)")
        && base.getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("4/5(6)")
        && bean.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("18/9(6)")
        && bean.getUserNode().getUnderList().getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("20/8(6)")
        && eat.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("8/5(6)")
        && bean.getUserNode().getUnderList().getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("25/8(6)")
        && base.getList().size() == 3 && shoe.getUserNode().getUnderList().getList().size() == 0 
        && made.getUserNode().getUnderList().getList().size() == 0 && poo.getUserNode().getUnderList().getList().size() == 0
        && bean.getUserNode().getUnderList().getList().size() == 3 
        && ell.getUserNode().getUnderList().getList().size() == 0
        && eat.getUserNode().getUnderList().getList().size() == 1 && leap.getUserNode().getUnderList().getList().size() == 0
        )) {
      return false;
    }
    
    bean.getUserTicket().setBidPrice(10);
    bean.getUserNode().getSelfList().bidSwitch(bean);
    if(!(base.getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("20/6(6)")
        && base.getList().get(3).getHolder().getUserTicket().getSellDate().toString().equals("4/5(6)")
        && base.getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("18/9(6)")
        && base.getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("25/8(6)")
        && bean.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("20/8(6)")
        && poo.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("17/8(6)")
        && eat.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("8/5(6)")
        && base.getList().size() == 4 && shoe.getUserNode().getUnderList().getList().size() == 0 
        && made.getUserNode().getUnderList().getList().size() == 0 && poo.getUserNode().getUnderList().getList().size() == 1
        && bean.getUserNode().getUnderList().getList().size() == 1 && ell.getUserNode().getUnderList().getList().size() == 0
        && eat.getUserNode().getUnderList().getList().size() == 1 && leap.getUserNode().getUnderList().getList().size() == 0)) {
      
      return false;
    }
    
    
//    
//    for(int i = 0; i<5;i++) {
//      System.out.print(base.getList().get(i).getHolder().getUserTicket().getSellDate() + "  " );
//    }
//    System.out.println();
//    System.out.println(poo.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate());
    
//////TERMINATE TEST////////////////////////////////////////////////////////////////////////////////
    bean.getUserNode().getSelfList().terminate(bean);
    if(!(base.getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("20/6(6)")
        && base.getList().get(3).getHolder().getUserTicket().getSellDate().toString().equals("4/5(6)")
        && base.getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("18/9(6)")
        && base.getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("25/8(6)")
        && poo.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("20/8(6)")
        && eat.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("8/5(6)")
        && base.getList().size() == 4 && shoe.getUserNode().getUnderList().getList().size() == 0 
        && made.getUserNode().getUnderList().getList().size() == 0 && poo.getUserNode().getUnderList().getList().size() == 1
        && ell.getUserNode().getUnderList().getList().size() == 0 && eat.getUserNode().getUnderList().getList().size() == 1
        && leap.getUserNode().getUnderList().getList().size() == 0)) 
        {
      
      return false;
    }
    
    leap.getUserNode().getSelfList().terminate(leap);
    if(!(base.getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("20/6(6)")
        && base.getList().get(3).getHolder().getUserTicket().getSellDate().toString().equals("4/5(6)")
        && base.getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("18/9(6)")
        && base.getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("25/8(6)")
        && poo.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("20/8(6)")
        && base.getList().size() == 4 && shoe.getUserNode().getUnderList().getList().size() == 0 
        && made.getUserNode().getUnderList().getList().size() == 0 && poo.getUserNode().getUnderList().getList().size() == 1
        && ell.getUserNode().getUnderList().getList().size() == 0 && eat.getUserNode().getUnderList().getList().size() == 0)) 
        {
      
      return false;
    }
    
    
    
    return true;
  }
  
  public static boolean gameTester() {
    //game
    Game bee = new Game("bee", new Date(12,17,8));
    
    //sellers
    User bean = new User(new Card(), new Ticket(bee, new Date(6,5,9), new Date(6,17,8),5,32));    
    User poo = new User(new Card(), new Ticket(bee, new Date(6,5,9), new Date(6,20,6),10,33));    
    User eat = new User(new Card(), new Ticket(bee, new Date(6,5,9), new Date(6,4,5),15,30));
    User shoe = new User(new Card(), new Ticket(bee, new Date(6,5,9), new Date(6,18,9),6,29));    
    User ell = new User(new Card(), new Ticket(bee, new Date(6,5,9), new Date(6,20,8),8,25));
    User leap = new User(new Card(), new Ticket(bee, new Date(6,5,9), new Date(6,8,5),20,28));    
    User made = new User(new Card(), new Ticket(bee, new Date(6,5,9), new Date(6,25,8),9,23));
    
    bee.addSeller(bean);
    bee.addSeller(leap);
    bee.addSeller(poo);
    bee.addSeller(eat);
    bee.addSeller(shoe);
    bee.addSeller(made);
    bee.addSeller(ell);
    
    //buyers
    User watt = new User(new Card(), new Interest(bee, new Date(6,5,9), new Date(6,5,9),15));
    User ahm = new User(new Card(), new Interest(bee, new Date(6,5,9), new Date(6,5,9),12));
    User she = new User(new Card(), new Interest(bee, new Date(6,5,9), new Date(6,5,9),10));
    User peep = new User(new Card(), new Interest(bee, new Date(6,5,9), new Date(6,5,9),3));
    User dee = new User(new Card(), new Interest(bee, new Date(6,5,9), new Date(6,5,9),4));
    User elmo = new User(new Card(), new Interest(bee, new Date(6,5,9), new Date(6,5,9),7));
    User ret = new User(new Card(), new Interest(bee, new Date(6,5,9), new Date(6,5,9),5));
    
    bee.addBuyer(ret);
    bee.addBuyer(peep);
    bee.addBuyer(dee);
    bee.addBuyer(watt);
    bee.addBuyer(ahm);
    bee.addBuyer(elmo);
    bee.addBuyer(she);
    
    //test that the lists are proper
    if(!(bee.getTopBuyNow().equals(made) && bee.getTopSellNow().equals(watt))) {
      return false;
    }
    
    //tests bidList
    if(!(bee.getBidList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("17/8(6)")
        && bee.getBidList().getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("20/6(6)")
        && bee.getBidList().getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("4/5(6)")
        && bean.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("18/9(6)")
        && bean.getUserNode().getUnderList().getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("20/8(6)")
        && eat.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("8/5(6)")
        && ell.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("25/8(6)")
        && bee.getBidList().getList().size() ==3 && bean.getUserNode().getUnderList().getList().size() == 2
        && poo.getUserNode().getUnderList().getList().size() == 0 && leap.getUserNode().getUnderList().getList().size() == 0
        && shoe.getUserNode().getUnderList().getList().size() == 0 && made.getUserNode().getUnderList().getList().size() == 0
        && ell.getUserNode().getUnderList().getList().size() == 1 && eat.getUserNode().getUnderList().getList().size() == 1
        )) {
      return false;
    }
    
    User buyer1 = new User(new Card());
    User buyer2 = new User(new Card());
    User buyer3 = new User(new Card());
    User buyer4 = new User(new Card());
    User buyer5 = new User(new Card());
    User buyer6 = new User(new Card());
    
    //tests bids
    
    bee.bids(buyer1, 13,ell);
    if(!(bee.getBidList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("17/8(6)")
        && bee.getBidList().getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("20/6(6)")
        && bee.getBidList().getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("4/5(6)")
        && bean.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("18/9(6)")
        && bean.getUserNode().getUnderList().getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("20/8(6)")
        && eat.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("8/5(6)")
        && bean.getUserNode().getUnderList().getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("25/8(6)")
        && bee.getBidList().getList().size() == 3 && shoe.getUserNode().getUnderList().getList().size() == 0 
        && made.getUserNode().getUnderList().getList().size() == 0 && poo.getUserNode().getUnderList().getList().size() == 0
        && bean.getUserNode().getUnderList().getList().size() == 3 
        && ell.getUserNode().getUnderList().getList().size() == 0
        && eat.getUserNode().getUnderList().getList().size() == 1 && leap.getUserNode().getUnderList().getList().size() == 0
        )) {
      return false;
    }
    
   bee.bids(buyer2, 13,bean);

    if(!(bee.getBidList().getList().get(2).getHolder().getUserTicket().getSellDate().toString().equals("20/6(6)")
        && bee.getBidList().getList().get(3).getHolder().getUserTicket().getSellDate().toString().equals("4/5(6)")
        && bee.getBidList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("18/9(6)")
        && bee.getBidList().getList().get(1).getHolder().getUserTicket().getSellDate().toString().equals("25/8(6)")
        && bean.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("20/8(6)")
        && poo.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("17/8(6)")
        && eat.getUserNode().getUnderList().getList().get(0).getHolder().getUserTicket().getSellDate().toString().equals("8/5(6)")
        && bee.getBidList().getList().size() == 4 && shoe.getUserNode().getUnderList().getList().size() == 0 
        && made.getUserNode().getUnderList().getList().size() == 0 && poo.getUserNode().getUnderList().getList().size() == 1
        && bean.getUserNode().getUnderList().getList().size() == 1 
        && ell.getUserNode().getUnderList().getList().size() == 0
        && eat.getUserNode().getUnderList().getList().size() == 1 && leap.getUserNode().getUnderList().getList().size() == 0)) {
     
      return false;
    }
    
    if(!(bean.getUserTicket().getBidder().equals(buyer2) && ell.getUserTicket().getBidder().equals(buyer1))) {
      return false;
    }
    
    //test basic buynow and sellnow ticket transfer/list-switch, along with consistency among removals
    Ticket held = made.getUserTicket();
    bee.buysNow(buyer3);
    if(!(
        Math.abs(buyer3.getUserCard().getBalance() +23) <.001 && Math.abs(made.getUserCard().getBalance() -23) <.001
        && buyer3.getUserTicket().compareBuyNow(held) == 0 
        && made.getUserTicket() == null 
        && !buyer3.getUserTicket().isSelling()
        && bee.getTopBuyNow().equals(ell)
        && bee.getBidList().getList().size() == 3
        )) {
      return false;
    }
    
    held = ell.getUserTicket();
    bee.sellsNow(ell);
    if(!(
        Math.abs(watt.getUserCard().getBalance() +15) <.001 && Math.abs(ell.getUserCard().getBalance() -15) <.001
        && watt.getUserTicket().compareBuyNow(held) == 0 
        && ell.getUserTicket() == null 
        && !buyer3.getUserTicket().isSelling()
        && bee.getTopBuyNow().equals(leap)
        && bee.getTopSellNow().equals(ahm)
        && bee.getBidList().getList().size() == 3
        && bean.getUserNode().getUnderList().getList().size() == 0
        )) {
      return false;
    }
    

    
    //test auction finish/moneymaker
    held = bean.getUserTicket();
    bee.finishAuction(bean);
    if(!(
        Math.abs(buyer2.getUserCard().getBalance() +13) <.001 && Math.abs(bean.getUserCard().getBalance() -13) <.001
        && buyer2.getUserTicket().equals(held)
        && bean.getUserTicket() == null
        && !buyer2.getUserTicket().isSelling()
        && bee.getBidList().getList().size() == 3
        && poo.getUserNode().getUnderList().getList().size() == 0
        && Math.abs(Card.getMasterCardBalance()) <.001
        )) {
      return false;
    }
    

    held = shoe.getUserTicket();
    bee.finishAuction(shoe);
    if(!(
        Math.abs(ahm.getUserCard().getBalance() +12) <.001 && Math.abs(shoe.getUserCard().getBalance() -6) <.001
        && buyer4.getUserTicket() ==null
        && ahm.getUserTicket().equals(held)
        && shoe.getUserTicket() == null
        && !ahm.getUserTicket().isSelling()
        && bee.getBidList().getList().size() == 2
        && Math.abs(Card.getMasterCardBalance() -6) <.001
        )) {
      return false;
    }   
    
    bee.bids(buyer4, 4, poo);
    held = poo.getUserTicket();
    bee.finishAuction(poo);
    if(!(
        Math.abs(she.getUserCard().getBalance() +10) <.001 && Math.abs(poo.getUserCard().getBalance() -4) <.001
        && buyer4.getUserTicket() ==null
        && she.getUserTicket().equals(held)
        && poo.getUserTicket() == null
        && !she.getUserTicket().isSelling()
        && bee.getBidList().getList().size() == 1
        && Math.abs(Card.getMasterCardBalance() -12) <.001
        )) {
      return false;
    }
    
    
    
    
    //test sellnow with nonlisters
    
    
    
    return true;
  }
  
  
  
  public static boolean runAllTests() {
    return dateTester() 
        && userTester()
        && ticketTester()
        && interestTester()
        && altListTester()
        && gameTester();
  }
  
  
  public static void main(String[] args) {
    System.out.println(runAllTests());
    
  }

}
