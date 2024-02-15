import java.util.LinkedList;

public class Destinations {

    private LinkedList<Destination> destinations;
    private Agency agency;

    public Destinations(Agency agency) {
        destinations = new LinkedList<Destination>();
        this.agency = agency;
    }

    /**
     * Initialises destinations list
     */
    public void initialise() {
        destinations.add(new Destination("Eiffel Tower", "France"));
        destinations.add(new Destination("Opera House", "Australia"));
        destinations.add(new Destination("Uluru", "Australia"));
        destinations.add(new Destination("Machu Picchu", "Peru"));
        destinations.add(new Destination("Great Pyramids", "Egypt"));
        destinations.add(new Destination("Niagara Falls", "Canada"));
    }

    /**
     * Destinations Menu
     */
    public void use() {
        welcomeString(agency.getAdminName());
        char input;

        while ((input = Utils.readChoice(menu())) != 'X') {
            switch(input) {
                case '1': display(); break;
                case '2': displayByCountry(); break;
                case '3': addNewDestination(); break;
                case '4': removeOldDestination(); break;
                default: Utils.invalidChoice(); break;
            }
        }
    }

    /**
     * Displays all destinations
     */
    private void display() {
        list(destinations);
    }

    /**
     * Displays destinations in a particular country that inputted by a user
     */
    private void displayByCountry() {
        LinkedList<Destination> matches = hasCountry(Utils.readCountry());

        while (matches.size() == 0) {
            System.out.println("No matching destinations found.");
            matches = hasCountry(Utils.readCountry());
        }

        list(matches);
    }

    /**
     * Adds a new destination to the destination list, details inputted by user
     */
    private void addNewDestination() {
        String name;
        String country;

        while (hasDestination((name = Utils.readName()), (country = Utils.readCountry()))) {
            System.out.println("This destination already exists.");
        }

        addDestination(new Destination(name, country));
        Utils.addFlightsForDestination(destination(name, country), agency);
        System.out.println("Destination added.");
    }

    /**
     * Removes an old destination from the destination list, details inputted by user
     */
    private void removeOldDestination() {
        String name;
        String country;
        
        while (!hasDestination((name = Utils.readName()), (country = Utils.readCountry()))) {
            System.out.println("No matching destinations found.");
        }

        removeDestination(destination(name, country));
        System.out.println("Destination removed.");
    }

    /**
     * Adds destination to destination list
     * @param d destination to add
     */
    public void addDestination(Destination d) {
        destinations.add(d);
    }

    /**
     * Removes destination from destination list
     * @param d destination to remove
     */
    public void removeDestination(Destination d) {
        destinations.remove(d);
    }
    
    /**
     * Checks if the destination exists on the destination list
     * @param name      name of destination
     * @param country   country where destination is located
     * @return          boolean dependent on if destination exists
     */
    public boolean hasDestination(String name, String country) {
        return destination(name, country) != null;
    }

    /**
     * Gets destinations list
     * @return  destinations list
     */
    public LinkedList<Destination> getDestinations() {
        return destinations;
    }

    /**
     * Gets size of destinations list
     * @return  size of destinations list
     */
    public int getDestinationsSize() {
        return destinations.size();
    }

    /**
     * Gets a destination at index i of destinations list
     * @param i index of destination
     * @return  the destination at index i of destinations list
     */
    public Destination getDestinationByIndex(int i) {
        return destinations.get(i);
    }

    /**
     * Finds a specific destinations in the destinations list based on name and country
     * @param name      name of destination
     * @param country   country where destination is located
     * @return          destination object
     */
    public Destination destination(String name, String country) {
        for (Destination destination : destinations) {
            if (destination.hasName(name) && destination.hasCountry(country)) {
                return destination;
            }
        }

        return null;
    }

    /**
     * Gets a list of destinations located in a certain country
     * @param s country
     * @return  list of destinations in that country
     */
    private LinkedList<Destination> hasCountry (String s) {
        LinkedList<Destination> matches = new LinkedList<Destination>();
        for (Destination destination : destinations) {
            if (destination.hasCountry(s)) {
                matches.add(destination);
            }
        }
        return matches;
    }

    /**
     * Prints out a list of destinations
     * @param list  list of destinations
     */
    private void list(LinkedList<Destination> list) {
        Utils.destinationsHeader();
        for (Destination destination : list) {
            System.out.println(destination.toString());
        }
        Utils.footer();
    }

    /**
     * Welcome message for Destinations menu
     * @param adminName name of logged in user
     */
    private void welcomeString(String adminName) {
        System.out.println("Welcome to the Destinations section " + adminName + ", Please make a selection from the menu:");
    }

    /**
     * Forms the menu string
     * @return  menu string
     */
    private String menu() {
        String menu = "1. View All Destinations\n";
        menu += "2. View Destinations by Country\n";
        menu += "3. Add a Destination\n";
        menu += "4. Remove a Destination\n";
        menu += "X. Return to Main Menu\n";
        menu += "Please enter an option: ";

        return menu;
    }
}

