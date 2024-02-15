import java.util.LinkedList;

public class Administrator {
    private String name;
    private String login;
    private String password;

    public Administrator(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    /**
     * Gets the name of the Administrator
     * @return  name of admin
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the inputted username/login matches with this Administrator's
     * @param s User inputted username/login
     * @return  boolean of whether the usernames/logins match
     */
    public boolean hasUser(String s) {
        return s.contains(this.login);
    }

    /**
     * Checks if the password matches with this Administrator's
     * @param s User inputted password
     * @return  boolean of whether the passwords match
     */
    public boolean hasPassword(String s) {
        return s.contains(this.password);
    }
}
