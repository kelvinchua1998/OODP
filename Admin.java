import java.io.Serializable;
import java.util.ArrayList;


public class Admin extends User implements Serializable{
    private static final long serialVersionUID = 1L;

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