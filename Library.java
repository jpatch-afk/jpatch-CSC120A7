import java.util.Hashtable;

public class Library extends Building {
  private Hashtable<String, Boolean> collection = new Hashtable<String, Boolean>();
  private String name; 
  private String address; 
  private int nFloors; 
    /**
     * Constructor
     * @param name
     * @param address
     * @param nFloors
     */
    public Library(String name, String address, int nFloors) {
      super(name, address, nFloors);
      System.out.println("You have built a library: 📖");
      this.name = name;
      this.address = address;
      this.nFloors = nFloors; 
      collection.put("War and Peace", true); //initializing a book in the collection, returns null otherwise
    }
    
  /**
   * Checks a book out or returns a book
   * @param action
   * @param title
   */
  public void bookMovement(String action, String title) {
    if (action == "check") {
      if (collection.containsKey(title)) {
        collection.put(title, false); 
        System.out.println(title + " successfully checked out!");
      }
      else {
        System.out.println(title + " doesn't exist in the collection, please try again.");
      }
    }
    if (action == "return") {
      if (collection.containsKey(title) && collection.containsValue(false)) {
        collection.put(title, true); 
        System.out.println(title + " returned successfully!");
      }
      else if (collection.containsKey(title) && collection.containsValue(true)) {
        System.out.println(title + " hasn't been checked out, please try another title.");
      }
      else {
        System.out.println(title + " does not exist in the collection, please try again.");
      }
    }
    else {
      throw new RuntimeException("You can either check out or return a book. Please specify check or return.");
    }
  }

  /**
   * Checks whether a book is included within the collection
   * @param title
   * @return true or false depending on whether the title lives in the collection or not
   */
  public boolean containsTitle(String title) {
    if (collection.containsKey(title)) {
      System.out.println(title +" is in the collection!");
      return true; 
    }
    else {
      System.out.println(title + " does not exist in the collection.");
      return false; 
    }
  }

  /**
   * Prints the title and checked-out status of the titles in the collection 
   */
  public void printCollection() {
    System.out.println("Titles in Collection:");
    for(String i: collection.keySet()) {
      System.out.println(i);
      if (collection.get(i).equals(true)) {
        System.out.println("Checked in.");
      }
      else {
        System.out.println("Checked out.");
      }
    }
  }

  /**
   * Adds or removes a title from the collection 
   * @param action
   * @param title
   * @return
   */
  public String editCollection(String action, String title) {
    if (action == "add") {
      if (!collection.containsKey(title)) {
        collection.put(title, true); 
        System.out.println(title + " placed successfully!");
        return title;
      }
      else {
        System.out.println(title + " is already in the collection!");
        return title;
      }
    }
    else if (action == "remove") {
      if (collection.containsKey(title)){
        collection.remove(title); 
        System.out.println(title + " successfully removed!");
        return title; 
      }
      else {
        System.out.println(title + " does not exist in the collection, please try again.");
        return title;
      }
    }
    else {
      throw new RuntimeException("You can either add or remove a title to/from the collection. Please specify add or remove.");
    }
  }

  /**
   * Shows the options for the user in a library 
   */
  public void showOptions() {
    System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goToFloor(n) \n + editCollection(action, title) \n + bookMovement(action, title) \n + printCollection() \n + containsTitle() \n + getBuilding()");
  }

  /**
   * Allows the user to move between multiple floors, most libraries have an elevator
   * @param int
   */
  public void goToFloor(int floorNum) {
    if (this.activeFloor == -1) {
      throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
    }
    if (floorNum < 1 || floorNum > this.nFloors) {
      throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
    }
    System.out.println("You are now on floor #" + floorNum + " of " + this.name);
    this.activeFloor = floorNum;
  }

  /**
   * Allows the user to enter the building
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
   * Allows the user to exit the building
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
    Library newLibrary = new Library("Neilson Library", "Address", 4);
    newLibrary.editCollection("add","Pride & Prejudice");
    newLibrary.bookMovement("check","Pride & Prejudice");
    newLibrary.printCollection();
    newLibrary.showOptions();
  }
}