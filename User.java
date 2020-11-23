import java.io.Serializable;
import java.util.ArrayList;
/**
 * User parent class for Students and Admin
 * @author kelvin
 *
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * unique user name for every user to log in
     * 
     */
    String username;
    /**
     * their password
     * 
     */
    String password;
    /**
     * Usertype to differentiate between them
     * 
     */
    String userType;

    /**
     * return the User's username
     * @return their username
     */
    public String getUsername() {
        return username;
    }
	/**
	 * sets their username
	 * @param username
	 */
    public void setUsername(String username) {
        this.username = username;
    }
/**
 * return the User's password
 * @return
 */
    public String getPassword() {
        return password;
    }
/**
 * sets the User's password
 * @param password
 */
    public void setPassword(String password) {
        this.password = password;
    }
/**
 * default constructor for creating a User class
 * @param username
 * @param password
 * @param userType
 */
    public User(String username, String password,String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    /**
     * get User Type
     * @return
     */

    public String getUserType() {
        return userType;
    }
/**
 * sets the User Type
 * @param userType
 */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static void main(String[] args) {
        
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayList<User> userList = new ArrayList<User>();

        // for(int i=0; i<userList.size(); i++){
        //     System.out.println(userList.get(i).getUsername()); 
        //     System.out.println(userList.get(i).getPassword()); 
        // }

        // userList.remove(1);
        // userList.remove(1);
        // userList.remove(1);
        // userList.remove(1);

        databaseManager.SerializeUserList(userList);
    }

}
