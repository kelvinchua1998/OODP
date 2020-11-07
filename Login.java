import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Login {
    // username for student is their matric number
    private String Username;
    private String Password;
    // private Date currentDate;

    public boolean verifyUser(String Username,String Password, int choice){
        boolean verified=false;
        DatabaseManager databaseManager = new DatabaseManager();

        switch(choice){
            case 1:{
                 ArrayList<Admin> adminList = new ArrayList<Admin>();
            adminList = databaseManager.DeserializeAdminList();

            Admin adminobj = Admin.getAdminbyUsername(Username, adminList);

            if(adminobj != null){
                if(adminobj.getPassword() == Password){
                    verified = true;
                }
            }else{
                System.out.println("Admin not found. please register as a Admin first!");
            }
            break;
            }
           

            case 2:{
                ArrayList<Student> studentList = new ArrayList<Student>();
            studentList = databaseManager.DeserializeStudentList();

            Student studentobj = Student.getStudentbyMatricNum(Username, studentList);

            if(studentobj != null){
                if(studentobj.getPassword() == Password){
                    verified = true;
                }
            }else{
                System.out.println("Student not found. please register as Student first!");
            }

            break;
            }


            default: {
                // print invalid choice
            }
        }
        
        return verified;

    }

    public boolean verifyPeriod(Date currentDate){
        // check with admin if the current date is within the period
        return true;
    }
}
