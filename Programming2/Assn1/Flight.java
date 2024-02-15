import java.text.DecimalFormat;
import java.util.Date;

public class Flight {
    private String airline;
    private int flightNo;
    private String takeoff;
    private String landing;
    private double cost;

    public Flight(String airline, int flightNo, String takeoff, String landing, double cost) {
        this.airline = airline;
        this.flightNo = flightNo;
        this.takeoff = takeoff;
        this.landing = landing;
        this.cost = cost;
    }

    /**
     * Checks if a flight takes off or lands in a particular country
     * @param s country
     * @return  boolean dependent on whether country is included in flight details
     */
    public boolean hasCountry(String s) {
        return (hasTakeoff(s) || hasLanding(s));
    }

    /**
     * Checks if take off and landing are the same
     * @param takeoff   country of takeoff
     * @param landing   country of landing
     * @return          boolean dependent on whether takeoff and landing are the same
     */
    public boolean match(String takeoff, String landing) {
        return (hasTakeoff(takeoff) && hasLanding(landing));
    }

    /**
     * Checks if flight takes off from a particular country
     * @param s country
     * @return  boolean dependent on whether flight takes off from this country
     */
    public boolean hasTakeoff(String s) {
        return s.contains(this.takeoff);
    }

    /**
     * Checks if flight lands in a particular country
     * @param s country
     * @return  boolean depend on whether flight lands in this country
     */
    public boolean hasLanding(String s) {
        return s.contains(this.landing);
    }

    /**
     * Checks gets cost of flight
     * @return  cost of flight
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * toString function - forms string for flight in the following format:
     * {airline} Flight {flight number} from {takeoff} to {landing} + for + {cost}
     */
    @Override
    public String toString() {
        return this.airline + " Flight " + Integer.toString(this.flightNo) + " from " + this.takeoff + " to " + this.landing + " for " + formatted(this.cost);
    }

    /**
     * Formats a double so it appears like money
     * @param money double to be formatted
     * @return      string with formatted price
     */
    private String formatted(double money) {
        return new DecimalFormat("###,##0.00").format(money);
    }
}
