public class Destination {
    private String country;
    private String name;

    public Destination(String name, String country) {
        this.name = name;
        this.country = country;
    }

    /**
     * Checks if a destination is in a particular country
     * @param s the country being compared
     * @return  boolean dependent on whether s matches the country
     */
    public boolean hasCountry(String s) {
        return s.contains(this.country);
    }

    /**
     * Checks if a destination has a particular name
     * @param s the name being compared
     * @return  boolean dependent on whether s matches the name
     */
    public boolean hasName(String s) {
        return s.contains(this.name);
    }

    /**
     * Gets country
     * @return  country of object
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * toString function - forms string for destination in the following format:
     * {name} in {country}
     */
    @Override
    public String toString() {
        return this.name + " in " + this.country;
    }
}
