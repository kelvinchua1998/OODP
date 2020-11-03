import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean verified = false;
        boolean runnning = true;

        Login login = new Login();
        Scanner sc = new Scanner(System.in);
        
        while (!verified) {
            System.out.println("Are You Signing in as:");
            System.out.println("1.Admin");
            System.out.println("2.Student");
            int choice = sc.nextInt();
            System.out.println("Please Enter Username:");
            String matricNum = sc.next();
            System.out.println("Please Enter Password");
            String pw = sc.next();
            verified = login.verifyUser(matricNum, pw, choice);
            if (!verified) {
                System.out.println("login incorrect! Please Login Again!");
            } else {
                System.out.println("login success!");

            }
        }

        while (runnning) {
            System.out.println("Welcome Admin!");
            System.out.println("Select your option(1-6)");
            System.out.println("1. Edit student access period");
            System.out.println("2. Add a student (name, matric number, gender, nationality, etc)");
            System.out.println("3. Add/Update a course (course code, school, its index numbers and vacancy).");
            System.out.println("4. Check available slot for an index number (vacancy in a class)");
            System.out.println("5. Print student list by index number.");
            System.out.println("6. Print student list by course (a2ll students registered for the selected course).");
            System.out.println("[ print only student’s name, gender and nationality ]");
            System.out.println("7. Logout");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    checkVacancy();
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    break;
                }
                case 7: {
                    runnning = false;
                    break;
                }
                default: {

                }

            }

        }

        while(runnning){
            System.out.println("Welcome Student Name Matric Number!");
            System.out.println("Select your option(1-6)");
            System.out.println("1. *Add Course");
            System.out.println("2. Drop Course");
            System.out.println("3. Check/Print Courses Registered");
            System.out.println("4. Check Vacancies Available ");
            System.out.println("5. Change Index Number of Course");
            System.out.println("6. Swop Index Number with Another Student[refer to STARSPlanner STARSUserGuidev1_extracted.pdf for details of functions - ignore the Graphical display]");
            System.out.println("7. Logout");
            
            int choice = sc.nextInt();
            switch(choice){
                case 1:{
                    addCourse();
                    break;
                }
                case 2:{
                    dropCourse();
                    break;
                }
                case 3:{
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    break;
                }
                case 6:{
                    break;
                }
                case 7:{
                    runnning = false;
                    break;
                }
                default:{
    
                }
    
            }
    
        }

    }

private static void checkVacancy() {
    String coursecode;
    String cindex;

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter \' # \'to return to main menu ");
    System.out.println("Please enter coursecode: ");
    coursecode = sc.next();
    if (coursecode.equals("#"))
        return;

    System.out.println("Please enter index: ");
    cindex = sc.next();

    int vacancy = Cindex.getVacancyCindex(coursecode, cindex);

    if (vacancy != -1) {
        System.out.printf("course code: %s \n index: %s \n vacancy: %d\n", coursecode, cindex, vacancy);
    } else {
        System.out.println("course index not found! please try again!");
    }

}

private static void addCourse(){

    Scanner sc = new Scanner(System.in);
    System.out.println("Enter \' # \'to return to main menu ");
    System.out.println("Please enter coursecode: ");
    String coursecode = sc.next();
    if (coursecode.equals("#"))
        return;
    //else if(coursecode == Course.   //error checking
    
    Course.getCourseDescription();//course details should oso show vacancy

    System.out.println("Please enter index: ");
    String cindex = sc.next();
    //error checking

    int vacancy = Cindex.getVacancyCindex(coursecode, cindex);

    if(){  //how to check timetable clash?
        System.out.println("Unable to add because of timetable clash!");
    }
    else if(Course.getAU() > Student.getNumAuAvail() || Student.getNumAuAvail() == 0){
        System.out.println("Not enough AUs");
    }
    else if(vacancy != -1){
        //add course stuff
        
        Student.minusAU();    // minus amt of au of student left
        System.out.println("Course added!");
    }
    else{
        System.out.println("Course index full! Adding to waitlist.");
        //add to waitlist stuff and 2 courses cant clash
    }

}

    public static void EditStudentAccessPeriod() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter MatricNum: ");
        String matricNum = sc.next();
        if (matricNum.equals("#"))
            return;

        int year;
        int month;
        int day;
        int hour;
        int minute;
        System.out.println("AccessStartDateTime: ");
        System.out.println("Please enter year: ");
        year = sc.nextInt();
        System.out.println("Please enter month: ");
        month = sc.nextInt();
        System.out.println("Please enter day: ");
        day = sc.nextInt();
        System.out.println("Please enter hour: ");
        hour = sc.nextInt();
        System.out.println("Please enter minute: ");
        minute = sc.nextInt();

        Calendar accessStartTime = new GregorianCalendar(year, month, day, hour, minute);

        System.out.println("AccessEndDateTime: ");
        System.out.println("Please enter year: ");
        year = sc.nextInt();
        System.out.println("Please enter month: ");
        month = sc.nextInt();
        System.out.println("Please enter day: ");
        day = sc.nextInt();
        System.out.println("Please enter hour: ");
        hour = sc.nextInt();
        System.out.println("Please enter minute: ");
        minute = sc.nextInt();

        Calendar accessEndTime = new GregorianCalendar(year, month, day, hour, minute);

        Student.EditStudentAccessPeriod(matricNum, accessStartTime, accessEndTime);

        System.out.printf("Access time for %s changed!\n", matricNum);
    }


private static void dropCourse(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter \' # \'to return to main menu ");
    System.out.println("Please enter coursecode: ");
    String coursecode = sc.next();
    if (coursecode.equals("#"))
        return;
    //else ...  error checking

    System.out.println("Are you sure?");
    System.out.println("1-yes 0-no");
    int choice = sc.nextInt();

    while(choice != 1 || choice != 0){
        if(choice == 1){
            //drop course stuff

            Student.plusAU();  //add back amt of au to student
            System.out.println("Course dropped!");
        }
        else if(choice == 0){return;}
        else{
            System.out.println("invalid choice!");
        }
    }

}

private static void checkPrintCourse(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter \' # \'to return to main menu ");
    
    while(code != '#'){
        String code = sc.next();
        //Student.getCourses();
    }
}


public static void AddStudent(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter \' # \'to return to main menu ");
    System.out.println("Please enter MatricNum: ");
    String matricNum = sc.next();

    boolean unique;
    if (matricNum.equals("#"))
        return;
    else{
        unique = Student.verifyUniqueMatricNum(matricNum);
    }

    if(unique == true){
        System.out.println("Please enter Password: ");
        String password = sc.next();

        System.out.println("Please enter First Name: ");
        String firstname = sc.next();
        System.out.println("Please enter Last Name: ");
        String lastname = sc.next();
        System.out.println("Please enter Gender: ");
        String gender = sc.next();
        System.out.println("Please enter Nationality: ");
        String nationality = sc.next();
        System.out.println("Please enter Username: ");
        String username = sc.next();
        int numAUs = 0;
        long accessStartDateTime = new GregorianCalendar(2020, 01, 01, 00, 00).getTimeInMillis();
        long accessEndDateTime = new GregorianCalendar(2020, 01, 01, 00, 00).getTimeInMillis();
        
        Student studentObj = new Student(firstname, lastname, gender, nationality, matricNum,username ,numAUs, password ,accessStartDateTime, accessEndDateTime);
    }
}


