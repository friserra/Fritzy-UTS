import java.text.DecimalFormat;

public class Trip {

    private Flights flights;
    private Destinations destinations;
    private Agency agency;

    public Trip(Agency agency) {
        flights = new Flights(agency);
        destinations = new Destinations(agency);
        this.agency = agency;
    }

    /**
     * Trips menu
     */
    public void use() {
        System.out.println("Welcome to the Trips section! Please make a selection from the menu:");
        char input;

        while ((input = Utils.readChoice(menu())) != 'X') {
            switch(input) {
                case '1': addNewDestination(); break;
                case '2': removeOldDestination();; break;
                case '3': addConnectingFlights(); break;
                case '4': display(); break;
                default: Utils.invalidChoice();
            }
        }
    }

    /**
     * Adds connecting flights between destinations in trip 
     */
    private void addConnectingFlights() {
        if (flights.getFlightsSize() != 0) {
            flights.clearFlights();
        }

        int i = 0;

        while (i < destinations.getDestinationsSize() - 1) {
            String takeoff = destinations.getDestinationByIndex(i).getCountry();
            String landing = destinations.getDestinationByIndex(i + 1).getCountry();

            if (!takeoff.contains(landing)) {
                Flight connectingFlight = this.agency.getFlights().flight(takeoff, landing);
                flights.addFlight(connectingFlight);
            }

            i++;
        }

        System.out.println("Connecting flights added.");
    }

    /**
     * Lists all destinations and flights included in trip
     */
    private void display() {
        Utils.tripHeader();
        
        int i = 0;

        while (i < destinations.getDestinationsSize()) {
            System.out.println(destinations.getDestinationByIndex(i).toString());
            
            if (i < flights.getFlightsSize()) {
                System.out.println(flights.getFlightByIndex(i).toString());
            }
            
            i++;
        }

        System.out.println("Total Cost: " + flights.calculateCost());
        Utils.footer();
    }

    /**
     * Adds a destination within the agency to the trip
     */
    private void addNewDestination() {
        String name;
        String country;

        while (!agency.getDestinations().hasDestination((name = Utils.readName()), (country = Utils.readCountry()))) {
            System.out.println("No such destination in the agency.");
        }

        destinations.addDestination(agency.getDestinations().destination(name, country));
        System.out.println("Destination added.");
    }

    /**
     * Removes a destination from the trip
     */
    private void removeOldDestination() {
        String name;
        String country;

        while (!destinations.hasDestination((name = Utils.readName()), (country = Utils.readCountry()))) {
            System.out.println("No matching destinations found.");
        }

        destinations.removeDestination(destinations.destination(name, country));
        System.out.println("Destination removed.");
    }

    /**
     * Forms the menu string
     * @return  menu string
     */
    private String menu() {
        String menu = "1. Add a Destination\n";
        menu += "2. Remove a Destination\n";
        menu += "3. Add Connecting Flights\n";
        menu += "4. View Trip Details\n";
        menu += "X. Return to Main Menu\n";
        menu += "Please enter an option: ";

        return menu;
    }
}

