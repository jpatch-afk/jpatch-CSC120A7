public class Cafe extends Building{
    private int nCoffeeOunces;
    private int nSugarPackets;
    private int nCreams;
    private int nCups;
    private int nMilk; 
    private String name; 
    private String address; 
    private int nFloors; 

    /**
     * Constructor 
     * @param nCoffeeOunces
     * @param nSugarPackets
     * @param nCreams
     * @param nCups
     * @param nMilk 
     * @param name
     * @param address
     * @param nFloors
     */
    public Cafe(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups, int nMilk, String name, String address, int nFloors) {
        super(name, address, nFloors);
        System.out.println("You have built a cafe: ☕");
        this.nCoffeeOunces = nCoffeeOunces; 
        this.nSugarPackets = nSugarPackets;
        this.nMilk = nMilk; 
        this.nCreams = nCreams;
        this.nCups = nCups; 
        this.name = name;
        this.address = address;
        this.nFloors = nFloors; 
    }

    /**
     * Sells a coffee and decreases the amount of supplies by the amount in the parameters
     * @param size
     * @param nSugarPackets
     * @param nCreams
     */
    public void drinkCoffee(int size, int nSugar, int nCream) { //preventing double variables and adding all of the sugar packets and creams in stock to the coffee 
        if (nCoffeeOunces > 0 && nSugarPackets > 0 && nCreams > 0) {
            System.out.println("You have successfully sold a coffee!");
            nCoffeeOunces -= size; 
            nSugarPackets -= nSugar; 
            nCreams -= nCream; 
            nCups --;
            System.out.println("Remaining supplies:");
            System.out.println("Coffee Ounces: " + nCoffeeOunces + "\nSugar Packets: " + nSugarPackets + "\nCreams: " + nCreams + "\nCups: " + nCups);
        }
        else {
            System.out.println("This cafe is out of supplies, please restock.");
        }
    }

    /**
     * Restocks the coffee to its original capacity, or to what the parameters specify it to be 
     * @param nCoffeeOunces
     * @param nSugarPackets
     * @param nCreams
     * @param nCups
     * @param nMilk
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups, int nMilk) {
      nCoffeeOunces += nCoffeeOunces; 
      nSugarPackets += nSugarPackets;
      nCreams += nCreams;
      nCups += nCups;
      nMilk += nMilk; 
      System.out.println("Successfully restocked!"); 
      System.out.println("Supplies:");
      System.out.println("Coffee Ounces: " + nCoffeeOunces + "\nSugar Packets: " + nSugarPackets + "\nCreams: " + nCreams + "\nCups: " + nCups + "\nMilk: " + nMilk);
    }

    /**
     * Speciality Drinks 
     * @param specialty
     * @param size
     * @param syrup
     */
    public void specialty(String specialty, int size, String syrup) {
        if (specialty == "Latte") {
            nCoffeeOunces -= size; 
            System.out.println("Steaming milk...");
            if (size == 12) {
                nMilk -= 12; 
            }
            else if (size == 16) {
                nMilk -= 16; 
            }
        }
        else if (specialty == "Mocha") {
            nCoffeeOunces -= size;
            System.out.println("Steaming milk...");
            if (size == 12) {
                nMilk -= 12; 
            }
            else if (size == 16) {
                nMilk -= 16; 
            }
            System.out.println("Adding chocolate syrup...");
        }
        else if (specialty == "Cappuccino") {
            nCoffeeOunces -= size;
            System.out.println("Steaming milk...");
            System.out.println("Frothing the milk...");
            if (size == 12) {
                nMilk -= 12; 
            }
            else if (size == 16) {
                nMilk -= 16; 
            }
        }
        System.out.println("Adding " + syrup + " to your drink...");
        System.out.println("Here's your " + specialty + "!");
    }

    /**
     * Allows the user to enter or exit
     * @param action
     * @return
     */
    public Building move(String action) {
        if(action == "enter") {
            if (activeFloor != -1) {
                throw new RuntimeException("You are already inside this Building.");
            }
            this.activeFloor = 1;
            System.out.println("You are now inside " + this.name + " on the ground floor.");
            return this; // Return a pointer to the current building
        }
        if (action == "exit") {
            if (this.activeFloor == -1) {
                throw new RuntimeException("You are not inside this Building. Must call move(enter) before exit().");
            }
            System.out.println("You have left " + this.name + ".");
            this.activeFloor = -1; 
            return null; 
        }
        else {
            throw new RuntimeException("You can only enter or exit. Please specify enter or exit.");
        }
    }

    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Building. Must call move(enter) before navigating between floors.");
        }
        if (floorNum <= 1 || floorNum >= 1) {
            throw new RuntimeException("You cannot move between floors. You are not a cafe employee.");
        }
    }

    /**
     * Shows all of the options for the user in the cafe building
     */
    public void showOptions() {
        System.out.println("Available options at " + this.name + ":\n + move(action) \n + drinkCoffee() \n + \n specialty(speciality, size, syrup) \n + getBuilding() \n + goToFloor()");
        System.out.println("Drink Options: \nLatte, \n Mocha, \n Cappuccino");
        System.out.println("Sizes: \n 12 ounces or 16 ounces");
        System.out.println("Syrup Options: \n Vanilla, \n Caramel, \n Hazelnut, \n Lavender, \n None");
    }
    
   /**
    * Accessor to the building parameters
    */
    public void getBuilding() { //makes sure that the attributes of the building class are used 
        System.out.println("Building name: " + name + "\nBuilding Address: " + address + "\nNumber of Floors: " +nFloors);
    }

    public static void main(String[] args) {
        Cafe newCafe = new Cafe(100, 200, 200, 200, 50, "Compass Cafe", "Address", 1);
        newCafe.move("enter");
        newCafe.drinkCoffee(12, 2, 3);
        newCafe.restock(100, 200, 200, 50, 50); 
        newCafe.specialty("Latte", 12, "Vanilla");
    }
}