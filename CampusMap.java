import java.util.ArrayList;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) { 
        if (!buildings.contains(b)) {
            System.out.println("Adding building...");
            buildings.add(b);
            System.out.println("-->Successfully added " + b.getName() + " to the map.");
        }
        else {
            System.out.println(b +" already exists in the Campus Map.");
        }
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) { //add error handling or checking whether the building is on the Map
        if (buildings.contains(b)) {
            System.out.println("Removing building...");
            buildings.remove(b);
            System.out.println("-->Successfully removed " + b.getName() + " to the map.");
            return b; 
        }
        else {
            System.out.println(b + " is not on the Campus Map.");
            return b;
        }
    }

    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i ++) {
            mapString += "\n  " + (i+1) + ". "+ this.buildings.get(i).getName() + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        CampusMap myMap = new CampusMap();
        myMap.addBuilding(new Building("Ford Hall", "100 Green Street Northampton, MA 01063", 4));
        myMap.addBuilding(new Building("Bass Hall", "4 Tyler Court Northampton, MA 01063", 4));
        myMap.addBuilding(new Library("Neilson Library", "7 Neilson Drive Northhampton, MA 01063", 5));
        myMap.addBuilding(new House(false, false, "Haven House", "110 Elm Street Northhampton, MA 01063", 3));
        myMap.addBuilding(new Building("Seelye Hall", "2 Seelye Dr Northampton, MA 01063", 3));
        myMap.addBuilding(new Building("Ainsworth Gymnasium", "102 College Ln Northampton, MA  01060", 4));
        myMap.addBuilding(new Building("Campus Center", "100 Elm St Northampton, MA  01063", 2 ));
        myMap.addBuilding(new House(true, true, "Lamont House", "17 Prospect St Northampton, MA  01060", 3));
        myMap.addBuilding(new Library("Hillyer Art Library", "20 Elm Street Northampton, MA 01063", 2));
        myMap.addBuilding(new Building("Hatfield Hall", "3 Neilson Dr Northampton, MA  01063", 3)); 
        myMap.addBuilding(new Building("Burton Hall", "44 College Ln Northampton, MA  01063", 4)); 
        myMap.addBuilding(new Cafe(100, 200, 200, 200, 50, "Campus Center Cafe", "100 Elm StNorthampton, MA 01063", 1)); 
        System.out.println(myMap);
    }
}
