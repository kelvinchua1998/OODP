import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    String username;
    String password;
    String userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password,String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static void main(String[] args) {
        
        ArrayList<User> userList = new ArrayList<User>();

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.SerializeUserList(userList);
    }
}
