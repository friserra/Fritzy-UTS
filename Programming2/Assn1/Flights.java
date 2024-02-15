import java.util.InputMismatchException;
import java.util.LinkedList;

public class Flights {
    private LinkedList<Flight> flights;
    private Agency agency;

    public Flights(Agency agency) {
        flights = new LinkedList<Flight>();
        this.agency = agency;
    }

    /**
     * Initialises flights list
     */
    public void initialise() {
        LinkedList<Destination> destinations = agency.getDestinations().getDestinations();
        for (Destination destination : destinations) {
            Utils.addFlightsForDestination(destination, agency);
        }
    }

    /**
     * Flights Menu
     */
    public void use() {
        welcomeString(agency.getAdminName());

        char input;
        while ((input = Utils.readChoice(menu())) != 'X') {
            switch(input) {
                case '1': display(); break;
                case '2': displayByCountry(); break;
                case '3': addNewFlight(); break;
                case '4': removeOldFlight(); break;
                default: Utils.invalidChoice(); break;
            }
        }
    }

    /**
     * Displays all flights
     */
    private void display() {
        list(flights);
    }

    /**
     * Displays flights that take off or land from a particular country
     */
    private void displayByCountry() {
        LinkedList<Flight> matches = hasCountry(Utils.readCountry());
        
        if (matches.size() == 0) {
            System.out.println("No matching flights.");
            return;
        }

        list(matches);
    }

    /**
     * Adds new flight to flights list
     */
    private void addNewFlight() {
        String airline = readAirline();
        int flightNo = -1;

        try {
            flightNo = readFlightNo();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            Utils.clearConsole();
            addNewFlight();
        }

        String takeoff;
        String landing;
        double cost = -1;

        while (flight((takeoff = readTakeoff()), (landing = readLanding())) != null) {
            System.out.println("This flight already exists.");
            airline = readAirline();
            
            try {
                flightNo = readFlightNo();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                Utils.clearConsole();
                addNewFlight();
            }
        }

        try {
            cost = readCost();
            addFlight(new Flight(airline, flightNo, takeoff, landing, cost));
            System.out.println("Flight added.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            Utils.clearConsole();
            addNewFlight();
        }
    }

    /**
     * Removes old flight from flight list
     */
    private void removeOldFlight() {
        String takeoff = readTakeoff();
        String landing = readLanding();

        while (flight(takeoff, landing) == null) {
            System.out.println("No matching flight found.");
            takeoff = readTakeoff();
            landing = readLanding();
        }

        Flight flight = flight(takeoff, landing);
        removeFlight(flight);
        System.out.println(flight.toString() + " removed.");
    }

    /**
     * Gets flight with specific takeoff and landing
     * @param takeoff   name of where flight takesoff
     * @param landing   name of where flight lands
     * @return          flight object
     */
    public Flight flight(String takeoff, String landing) {
        for (Flight flight : flights) {
            if (flight.match(takeoff, landing)) {
                return flight;
            }
        }

        return null;
    }

    /**
     * Adds flight to flights list
     * @param f flight object
     */
    public void addFlight(Flight f) {
        flights.add(f);
    }

    /**
     * Removes flight from flights list
     * @param f flight object
     */
    public void removeFlight(Flight f) {
        flights.remove(f);
    }

    /**
     * Gets user input for airline of flight
     * @return  user inputted airline
     */
    private String readAirline() {
        System.out.print("Airline: ");
        return Utils.nextLine();
    }

    /**
     * Gets user input for flight number of flight
     * @return  user inputted flight number
     */
    private int readFlightNo() {
        System.out.print("Flight Number: ");
        return Utils.nextInt();
    }

    /**
     * Gets user input for takeoff location of flight
     * @return  user inputted takeoff
     */
    private String readTakeoff() {
        System.out.print("Takeoff: ");
        return Utils.nextLine();
    }

    /**
     * Gets user input for landing location of flight
     * @return  user inputted landing
     */
    private String readLanding() {
        System.out.print("Landing: ");
        return Utils.nextLine();
    }

    /**
     * Gets user input for cost of flight
     * @return  user inputted cost
     */
    private double readCost() {
        System.out.print("Cost: ");
        return Utils.nextDouble();
    }

    /**
     * Gets the flight at index i of flights lsit
     * @param i index of flight
     * @return  flight at index i of flights list
     */
    public Flight getFlightByIndex(int i) {
        return flights.get(i);
    }

    /**
     * Gets the size of flights list
     * @return  size of flights list
     */
    public int getFlightsSize() {
        return flights.size();
    }

    /**
     * Checks if a specific flight exists within flights list
     * @param takeoff   name of flight takeoff location
     * @param landing   name of flight landing location
     * @return          boolean dependent on whether flight exists
     */
    public boolean hasFlight(String takeoff, String landing) {        
        return flight(takeoff, landing) != null;
    }

    /**
     * Clears flight from flight list
     */
    public void clearFlights() {
        flights.clear();
    }

    /**
     * Prints out a list of flights
     * @param list  list of flights to print out
     */
    private void list(LinkedList<Flight> list) {
        Utils.flightsHeader();
            for (Flight flight : list) {
                System.out.println(flight.toString());
            }
        Utils.footer();
    }

    /**
     * Calculates cost of all flights on flights list
     * @return  total cost of flights
     */
    public double calculateCost() {
        double cost = 0;
        for (Flight flight : flights) {
            cost += flight.getCost();
        }
        return cost;
    }

    /**
     * Gets list of flights that take off or land in a certain country
     * @param s name of country for take off or landing
     * @return  list of flights that take off or land in that country
     */
    private LinkedList<Flight> hasCountry(String s) {
        LinkedList<Flight> matches = new LinkedList<Flight>();

        for (Flight flight : flights) {
            if (flight.hasCountry(s)) {
                matches.add(flight);
            }
        }

        return matches;
    }

    /**
     * Welcome message for Flights menu
     * @param   adminName name of logged in user
     */
    private void welcomeString(String adminName) {
        System.out.println("Welcome to the Flights section " + adminName + ", Please make a selection from the menu:");
    }

    /**
     * Forms the menu string
     * @return  menu string
     */
    private String menu() {
        String menu = "1. View All Flights\n";
        menu += "2. View Flights by Country\n";
        menu += "3. Add a Flight\n";
        menu += "4. Remove a Flight\n";
        menu += "X. Return to Main Menu\n";
        menu += "Please enter an option: ";

        return menu;
    } 
}

