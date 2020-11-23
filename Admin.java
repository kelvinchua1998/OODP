
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Admin class that is a subclass of User and implements Serializable so that it can be serialized into the database
 * @author kelvi
 *
 */
public class Admin extends User implements Serializable{
    private static final long serialVersionUID = 1L;
    
/**
 * default constructor for the admin class
 * @param username
 * @param password
 */
    public Admin(String username, String password){
        super(username,password,"admin");

        User userObj = new User(username, password, "admin");
		DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.adduser(userObj);
    }

    public static void main(String[] args) {
        Admin adminObj = new Admin("admin", "admin");
        ArrayList<Admin> adminList = new ArrayList<Admin>();

        adminList.add(adminObj);
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.SerializeAdminList(adminList);
    }
   
}