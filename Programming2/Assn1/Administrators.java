import java.util.LinkedList;

public class Administrators {
    LinkedList<Administrator> administrators;

    public Administrators() {
        administrators = new LinkedList<Administrator>();
    }

  
    public void insertDummyData() {
        administrators.add(new Administrator("David Dyer", "david46@uts.com", "123"));
        administrators.add(new Administrator("Angela Huo", "angela123@uts.com", "mypw"));
        administrators.add(new Administrator("Guest", "guest@github.com", "456"));
        //... 
    }

    /**
     * Checks if a certain Administrator exists in the agenecy's system.
     * @param user      login/username of admin
     * @param password  password of admin
     * @return          admin object
     */
    public Administrator hasAdmin(String user, String password) {
        for (Administrator admin : administrators) {
            if (admin.hasUser(user) && admin.hasPassword(password)) {
                return admin;
            }
        }

        return null;
    }
}
