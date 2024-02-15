import java.util.LinkedList;

public class Agency {

    private Administrator loggedInUser;
    private Destinations destinations;
    private Flights flights;
    private Administrators admins;

    public Agency() {
        admins = new Administrators();
        admins.insertDummyData(); // initialising admins
        login(); // logging in an admin
        destinations = new Destinations(this);
        destinations.initialise(); // initialising destinations
        flights = new Flights(this);
        flights.initialise(); // initialising flights
    }

    public static void main(String[] args) {
        Agency agency = new Agency();
        agency.use();
    }
    
    /**
     * Main Menu
     */
    private void use() {
        welcomeMsg(getAdminName());

        char input;
        while ((input = Utils.readChoice(menu())) != 'X') {
            switch(input) {
                case '1': flights.use(); welcomeMsg(getAdminName()); break;
                case '2': destinations.use(); welcomeMsg(getAdminName()); break;
                case '3': new Trip(this).use(); welcomeMsg(getAdminName()); break;
                default: Utils.invalidChoice(); break;
            }
        }

        System.out.println("Thanks for using the Prog2 Travel Agency.");
    }

    /**
     * Logs in a user
     */
    private void login() {
        while ((loggedInUser = admins.hasAdmin(readUser(), readPassword())) == null) {
            System.out.println("Invalid Credentials! Try Again.");
        }
    }

    /**
     * Reads a username/login
     * @return  User inputted username/login
     */
    private String readUser() {
        System.out.print("Username: ");
        return Utils.nextLine();
    }

    /**
     * Read a password
     * @return  User inputted password
     */
    private String readPassword() {
        System.out.print("Password: ");
        return Utils.nextLine();
    }

    /**
     * Gets the name of the logged in user
     * @return  Name of logged in user
     */
    public String getAdminName() {
        return loggedInUser.getName();
    }

    /**
     * Gets the value of the destinations field in the agency
     * @return  agency's destinations field
     */
    public Destinations getDestinations() {
        return destinations;
    }

    /**
     * Gets the value of the flights field in the agency
     * @return  agency's flights field
     */
    public Flights getFlights() {
        return flights;
    }

    /**
     * Prints out the welcome message for the main menu
     * @param adminName name of logged in user
     */
    private void welcomeMsg(String adminName) {
        System.out.println("Welcome to the Prog2 Travel Agency " + adminName + ", Please make a selection from the menu:");
    }

    /**
     * Forms the menu string
     * @return  menu string
     */
    private String menu() {
        String menu = "1. Explore Flights\n";
        menu += "2. Explore Destinations\n";
        menu += "3. Book a Trip\n";
        menu += "X. Exit the System\n";
        menu += "Please enter an option: ";

        return menu;
    }    
}
