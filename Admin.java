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
   

   private static void SerializeAdminList(List<Admin> adminList) {
      try {
         FileOutputStream fileOut = new FileOutputStream("admin.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(adminList);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved\n");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }
   
   private static List<Admin> DeserializeAdminList() {
      try {
         List<Admin> adminList2;
         FileInputStream fileIn = new FileInputStream("admin.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         adminList2 = (List<Admin>) in.readObject();
         in.close();
         fileIn.close();
         return adminList2;
      } catch (IOException i) {
         i.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      return null;
   }
   public static void main(String[] args) {
    Admin adminObj = new Admin("limjingda", "password");
    Admin adminObj2 = new Admin("kelvinchua", "password");
    Admin adminObj3 = new Admin("qwerty", "password");
    Admin adminObj4 = new Admin("asdfg", "password");
    Admin adminObj5 = new Admin("zxcvc", "password");

    List<Admin> adminList = new ArrayList<Admin>();
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