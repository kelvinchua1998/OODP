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
        
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayList<User> userList = databaseManager.DeserializeUserList();

        for(int i=0; i<userList.size(); i++){
            System.out.println(userList.get(i).getUsername()); 
            System.out.println(userList.get(i).getPassword()); 
        }

        // userList.remove(1);
        // userList.remove(1);
        // userList.remove(1);
        // userList.remove(1);

        // databaseManager.SerializeUserList(userList);
    }

}
