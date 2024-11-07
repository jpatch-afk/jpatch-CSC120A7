import java.util.ArrayList;

public class House extends Building{
  private ArrayList<String> residents = new ArrayList<String>();
  private boolean hasDiningRoom;
  private boolean hasElevator; 
  private String name; 
  private String address; 
  private int nFloors; 

  /**
   * Constructor 
   * @param hasDiningRoom
   * @param name
   * @param address
   * @param nFloors
   */
  public House(boolean hasDiningRoom, boolean hasElevator, String name, String address, int nFloors) {
    super(name, address, nFloors);
    System.out.println("You have built a house: 🏠");
    this.hasDiningRoom = hasDiningRoom;
    this.hasElevator = hasElevator; 
    this.name = name; 
    this.address = address;
    this.nFloors = nFloors; 
    residents.add("Sophia Smith"); //the ArrayList is null otherwise and returns an error 
  }

  /**
   * Returns whether the house has a dining room 
   * @return boolean; has a dining room or not
   */
  public boolean hasADiningRoom(){
    if (hasDiningRoom) {
      System.out.println("This house has a dining hall!");
      return true; 
    }
    else {
      System.out.println("This house does not have a dining hall.");
      return false; 
    }
  }

  /**
   * Checks the contents of the house
   * @param content
   * @return
   */
  public boolean checkContents(String content) {
    if (content == "dining") {
      if (this.hasDiningRoom) {
        System.out.println("This house has a dining hall!");
        return true; 
      }
      else {
        System.out.println("This house does not have a dining hall.");
        return false; 
      }
    }
    else if (content == "residents") {
      if (residents.size() > 0) {
        System.out.println("This house has " + residents.size()+ " resident(s) living in it.");
        return true;  
      }
      else {
        System.out.println("There are 0 residents in this house.");
        return false; 
      }
    }
    else {
      throw new RuntimeException("You can either check whether the house has a dining room or its number of residents. Please specify dining or residents.");
    }
  }

  /**
   * Moves a person in or out of a house 
   * @param action
   * @return
   */
  public String moveAround(String action, String name) {
    if (action == "in") {
       if (!residents.contains(name)) {
          residents.add(name);
          System.out.println( name + " has successfully moved in!");
          return name;
        }
      else {
        System.out.println(name + " already lives here, please try again.");
        return name; 
      }
    }
    if (action == "out") {
      if (residents.contains(name)) {
          residents.remove(name); 
          System.out.println(name + " has successfully moved out!");
          return name; 
        }
        else {
          System.out.println(name + " does not live here, please try another house.");
          return name; 
        }
    }
    else {
      throw new RuntimeException("You can only move in or out. Please specifiy in or out.");
    }
  }

  /**
   * Shows the options of the user
   */
  public void showOptions() {
    System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goToFloor(n) \n + moveAround(action, name) \n + checkContents(content) \n + getBuilding()");
  }

  /**
   * Allows the user to move between floors; checks whether the house has a elevator to move multiple floors at a time
   * @param int 
   */
  public void goToFloor(int floorNum) {
    if (this.activeFloor == -1) {
      throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
    }
    if (floorNum < 1 || floorNum > this.nFloors) {
      throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
    }
    if (hasElevator) {
      System.out.println("You are now on floor #" + floorNum + " of " + this.name);
      this.activeFloor = floorNum;
    }
    else if ((floorNum - activeFloor) > 1 || (floorNum - activeFloor) < -1) {
      throw new RuntimeException("You cannot go down multiple stairs at once, there is no elevator.");
    }
    else {
      System.out.println("You are now on floor #" + floorNum + " of " + this.name);
      this.activeFloor = floorNum;
    }
  } 

  /**
   * Allows the user to enter the house
   */
  public Building enter() {
    if (activeFloor != -1) {
        throw new RuntimeException("You are already inside this Building.");
    }
    this.activeFloor = 1;
    System.out.println("You are now inside " + this.name + " on the ground floor.");
    return this; // Return a pointer to the current building
  }

  /**
   * Allows the user to exit the house
   */
  public Building exit() {
    if (this.activeFloor == -1) {
        throw new RuntimeException("You are not inside this Building. Must call enter() before exit().");
    }
    if (this.activeFloor > 1) {
        throw new RuntimeException("You have fallen out a window from floor #" +this.activeFloor + "!");
    }
    System.out.println("You have left " + this.name + ".");
    this.activeFloor = -1; // We're leaving the building, so we no longer have a valid active floor
    return null; // We're outside now, so the building is null
  }

  /**
    * Accessor to the building parameters
    */
    public void getBuilding() { //makes sure that the attributes of the building class are used 
      System.out.println("Building name: " + name + "\nBuilding Address: " + address + "\nNumber of Floors: " +nFloors);
  }

  public static void main(String[] args) {
    House myHouse = new House(false, false, "Haven", "110 Elm Street", 3);
    myHouse.moveAround("in", "Taylor Swift");
    myHouse.checkContents("dining"); 
  }
}