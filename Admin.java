import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Admin implements Serializable{

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    public String accessTime;
    public String course;
    public int courseIndex;
    public int vacancy;

    public Admin(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getPassword(){
        return this.password;}
        
    public String getUserName(){
        return this.username;
    }

    
    public void addStudent(Object Admin) {
		return;
	}
	
	public String studentAccessTime() {
		return accessTime;
	}
	
	public String addCourse(){
        return course;
    }

    public int updateCourse(){
        return courseIndex;
    }

    public int checkVacancy(){
        return vacancy;
    }
   
   public static Admin getAdminbyUsername(String username, List<Admin> adminList) {
		for (int i = 0; i < adminList.size(); i++) {
			if (adminList.get(i).getUserName() == username) {
				return adminList.get(i);
			}
		}
		return null;
	}

   public static void main(String[] args) {
    Admin adminObj = new Admin("limjingda", "password");
    Admin adminObj2 = new Admin("kelvinchua", "password");
    Admin adminObj3 = new Admin("qwerty", "password");
    Admin adminObj4 = new Admin("asdfg", "password");
    Admin adminObj5 = new Admin("zxcvc", "password");

    ArrayList<Admin> adminList = new ArrayList<Admin>();
    adminList.add(adminObj);
    adminList.add(adminObj2);
    adminList.add(adminObj3);
    adminList.add(adminObj4);
    adminList.add(adminObj5);


    SerializeAdminList(adminList);
    adminList = null;
    adminList = DeserializeAdminList();

    ListIterator<Admin> ListItr = adminList.listIterator();
    while (ListItr.hasNext()) {
       System.out.println("index:" + ListItr.nextIndex() + " value:" + ListItr.next().getUserName());
    }
   }

   
}