
import java.io.Serializable;
import java.util.ArrayList;


public class Admin extends User implements Serializable{
    private static final long serialVersionUID = 1L;
    int adminID;

    public Admin(String username, String password,int adminID){
        super(username,password,"admin",1);

        User userObj = new User(username, password, "admin");
		DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.adduser(userObj);

        this.adminID = adminID;
    }

    public static void main(String[] args) {
        Admin adminObj = new Admin("admin", "admin", 1,);
        ArrayList<Admin> adminList = new ArrayList<Admin>();

        adminList.add(adminObj);
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.SerializeAdminList(adminList);
    }
   
}