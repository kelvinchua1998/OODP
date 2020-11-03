import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import jdk.nashorn.internal.ir.VarNode;

public class Main {

    public static void main(String[] args) {
        boolean verified = false;
        boolean runnning = true;

        Login login = new Login();
        Scanner sc = new Scanner(System.in);
        // ask user whether is student Or Admin
        // if student check whether is allowed period
        // if within allowed period => ask for matric number and pw
        // else admin ask for username and pw
        while (!verified) {
            System.out.println("Please Enter Username:");
            String matricNum = sc.next();
            System.out.println("Please Enter Password");
            String pw = sc.next();
            verified = login.verifyUser(matricNum, pw);
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

        while (runnning) {
            System.out.println("Welcome Student Name Matric Number!");
            System.out.println("Select your option(1-6)");
            System.out.println("1. *Add Course");
            System.out.println("2. Drop Course");
            System.out.println("3. Check/Print Courses Registered");
            System.out.println("4. Check Vacancies Available ");
            System.out.println("5. Change Index Number of Course");
            System.out.println(
                    "6. Swop Index Number with Another Student[refer to STARSPlanner STARSUserGuidev1_extracted.pdf for details of functions - ignore the Graphical display]");
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
    //else if(coursecode == Course.
    // how to make course details?  course details oso show vacancy

    System.out.println("Please enter index: ");
    String cindex = sc.next();
    //error checking

    int vacancy = Cindex.getVacancyCindex(coursecode, cindex);

    if(){  //how to check timetable clash?
        System.out.println("Unable to add because of timetable clash!");
    }
    else if(vacancy != -1){
        //add course stuff
        System.out.println("Course added!");
    }
    else{
        System.out.println("Course index full! Adding to waitlist.");
        //add to waitlist stuff
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

    public static void AddStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter MatricNum: ");
        String matricNum = sc.next();
        if (matricNum.equals("#"))
            return;

        boolean unique = Student.verifyUniqueMatricNum(matricNum);

        if()
    }
}
