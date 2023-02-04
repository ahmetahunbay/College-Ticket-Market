
//self explanatory

public class Date implements Comparable<Date> {
  
  private int time;
  private int day;
  private int month;
  
  public Date(int time, int day, int month) {
    if(time>23 || time<0) {
      throw new IllegalArgumentException();
    }
    this.time = time;
    this.day = day;
    this.month = month;

  }
  
  @Override
  public int compareTo(Date d) { 
    if(this.month < d.month) {
      return -1;
    }
    if(this.month > d.month) {
      return 1;
    }
    if(this.day < d.day) {
      return -1;
    }
    if(this.day > d.day) {
      return 1;
    }
    if(this.time < d.time) {
      return -1;
    }
    if(this.time > d.time) {
      return 1;
    }
    return 0;
  }
  
  @Override
  public String toString() {
    return day + "/" + month + "(" + time +")";
  }
}
