import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Utils {

    static int counter = 0;
    static Scanner scanner = new Scanner(System.in);
    static String[] airlines = {"American Airlines", "QANTAS", "JetStar", "Tiger Airways", "United Airlines", "Egypt Air", "Etihad", "Singapore Airlines", "British Air", "Cathay Dragon"};

    //Prompts the user with the provided message and read in the first char inputted to the console
    public static char readChoice(String message) {
        System.out.print(message);
        return scanner.nextLine().charAt(0);
    }

    //Returns the next line inputted
    public static String nextLine() { return scanner.nextLine(); }

    //Returns the next integer inputted
    public static int nextInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    //Returns the the next double inputted
    public static double nextDouble() {
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public static void clearConsole() { scanner.nextLine(); }

    //Takes in a destination and then adds flights to the agency that land and depart to all combinations of <provided country> and <other country>
    public static void addFlightsForDestination(Destination destination, Agency agency) {
        HashSet<String> allCountries = new HashSet<String>();
        for (Destination d : agency.getDestinations().getDestinations()) { if (!allCountries.contains(d.getCountry())) { allCountries.add(d.getCountry()); } }
        
        for (String s : allCountries) {
            if (s.equals(destination.getCountry())) { continue; }
            if (!agency.getFlights().hasFlight(s, destination.getCountry())) { agency.getFlights().addFlight(new Flight(airlines[counter%10], getNewFlightNumber(agency), s, destination.getCountry(), getNewCost(agency))); }
            if (!agency.getFlights().hasFlight(destination.getCountry(), s)) { agency.getFlights().addFlight(new Flight(airlines[counter%10], getNewFlightNumber(agency), destination.getCountry(), s, getNewCost(agency))); }
        }  
    }

    public static void flightsHeader() {
        System.out.format("+----------------------+----------------+--------------+-------+-------+%n");
        System.out.format("|                                Flights                               |%n");
        System.out.format("+----------------------+----------------+--------------+-------+-------+%n");
    }

    public static void destinationsHeader() {
        System.out.format("+----------------------+----------------+--------------+-------+-------+%n");
        System.out.format("|                              Destinations                            |%n");
        System.out.format("+----------------------+----------------+--------------+-------+-------+%n");
    }

    public static void tripHeader() {
        System.out.format("+----------------------+----------------+--------------+-------+-------+%n");
        System.out.format("|                                  Trip                                |%n");
        System.out.format("+----------------------+----------------+--------------+-------+-------+%n");
    }

    public static void footer() { System.out.format("+----------------------+----------------+--------------+-------+-------+%n"); }

    // STUDENT'S FUNCTIONS

    /**
     * Prints out message for invalid choices at the menus
     */
    public static void invalidChoice() {
        System.out.println("Please enter a valid choice, or press X to exit.");
    }

    /**
     * Reads user input for country of destination
     * @return  user inputted country
     */
    public static String readCountry() {
        System.out.print("Country: ");
        return nextLine();
    }

    /**
     * Reads user input for name of destination
     * @return  user inputted name
     */
    public static String readName() {
        System.out.print("Name: ");
        return nextLine();
    }

    //DONT USE METHODS BELOW
    //Everything below is just to stop Ed tests from exploding

    static int[] flightNumbers = {683, 575, 722, 453, 516, 339, 940, 642, 72, 368, 892, 162, 855, 372, 763, 687, 101, 905, 933, 311, 693, 739, 629, 829, 475, 634, 587, 885, 300, 967, 812, 130, 68, 166, 536, 208, 647, 860, 173, 325, 450, 662, 664, 554, 806, 705, 457, 872, 825, 859, 173, 229, 966, 988, 348, 469, 962, 861, 322, 110, 394, 973, 808, 986, 943, 970, 887, 13, 225, 419, 532, 447, 346, 723, 819, 63, 635, 697, 490, 879, 65, 975, 980, 912, 633, 824, 478, 278, 743, 7, 389, 604, 224, 231, 843, 59, 45, 619, 698, 117};
    static double[] costs = {749.87, 978, 826.58, 445.31, 417.91, 220.87, 911.92, 863.21, 8.29, 876.95, 841.37, 348.29, 396.26, 421.29, 636.65, 810.02, 779.25, 347.49, 454.54, 511.52, 415.68, 178.97, 611.49, 178.42, 420.67, 665.55, 491.86, 222.99, 548.23, 314.31, 736.12, 211.3, 530.05, 438.52, 299.32, 819.67, 641.98, 195.59, 672.9, 548.54, 806.92, 247.6, 148.1, 857.81, 302.36, 81.36, 239.36, 970.31, 13.45, 960.45, 449.41, 301.03, 615.24, 138.32, 402.64, 605.96, 135.84, 189.21, 533.04, 10.88, 290.01, 19.28, 210.12, 678.15, 362.85, 9.75, 381.48, 964.55, 903.66, 73.98, 731.05, 3.35, 291.69, 452.74, 370.26, 280.52, 10.21, 850.91, 58.26, 126.74, 824.87, 270.07, 412.11, 140.27, 534.23, 721.42, 452.62, 20.04, 982.77, 208.24, 341.84, 810.56, 917.71, 727.7, 815.34, 874.81, 450.99, 151.48, 307.15, 138.51};

    public static double getNewCost(Agency agency) {
        counter++;
        if (counter >= 50) { counter = 0; }
        return costs[counter];
    }

    public static int getNewFlightNumber(Agency agency) {
        counter++;
        if (counter >= 50) { counter = 0; }
        return flightNumbers[counter];
    }
}

