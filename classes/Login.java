package classes;
import java.util.ArrayList;
import java.util.Date;
/**
 * Login class for login functionalities
 * @author kelvin melvin
 *
 */
public class Login {
/**
 * verifies the user with their password 
 * @param Username username of the user
 * @param Password password of the user
 * @return returns the User type{Admin/ Student} when password is correct otherwise returns null
 */
    public String verifyUser(String Username,String Password){
        DatabaseManager databaseManager = new DatabaseManager();

        ArrayList<User> UserList = databaseManager.DeserializeUserList();
        for(int i=0; i < UserList.size(); i++){
            if(UserList.get(i).username.equals(Username)){
                if(UserList.get(i).password.equals(Password)){
                    System.out.println("User Verified!");
                    return UserList.get(i).userType;
                }else{
                    System.out.println("Password is wrong");
                    return null;
                }
            }
        }
        System.out.println("user not found");
        return null;
    }

}
