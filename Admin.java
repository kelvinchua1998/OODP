
import java.io.Serializable;
import java.util.ArrayList;


public class Admin extends User implements Serializable{
    int adminID;

    public Admin(String username, String password,int adminid){
        super(username,password,"admin");

        User userObj = new User(username, password, "admin");
		DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.adduser(userObj);

        this.adminID = adminid;
    }

    public static void main(String[] args) {
        Admin adminObj = new Admin("admin", "admin", 1);
        ArrayList<Admin> adminList = new ArrayList<Admin>();
        adminList.clear();

        adminList.add(adminObj);
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.SerializeAdminList(adminList);
    }
   
}