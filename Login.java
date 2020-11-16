import java.util.ArrayList;
import java.util.Date;

public class Login {

    public String verifyUser(String Username,String Password){
        DatabaseManager databaseManager = new DatabaseManager();

        ArrayList<User> UserList = databaseManager.DeserializeUserList();
        for(int i=0; i < UserList.size(); i++){
            if(UserList.get(i).username.equals(Username)){
                if(UserList.get(i).password.equals(Password)){
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

    public boolean verifyPeriod(Date currentDate){
        // check with admin if the current date is within the period
        return true;
    }
}
