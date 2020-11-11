import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class Main {

    public static void main(String[] args) {
        boolean runnning = true;
        String userType = null;

        Login login = new Login();
        Scanner sc = new Scanner(System.in);
        String matricNum = null;
        String password = null;

        while (userType == null) {
            System.out.println("Please Enter Username:");

            matricNum = sc.next();

            System.out.println("Please Enter Password");
            password = sc.next();

            userType = login.verifyUser(matricNum, password);

            if (userType == null) {
                System.out.println("Please Login Again!");
            } else {
                System.out.println("login success!");
            }
        }

        switch (userType) {
            case "admin":
                while (runnning) {
                    System.out.println("Welcome Admin!");
                    System.out.println("Select your option(1-6)");
                    System.out.println("1. Edit student access period");
                    System.out.println("2. Add a student (name, matric number, gender, nationality, etc)");
                    System.out.println("3. Add/Update a course (course code, school, its index numbers and vacancy).");
                    System.out.println("4. Check available slot for an index number (vacancy in a class)");
                    System.out.println("5. Print student list by index number.");
                    System.out.println(
                            "6. Print student list by course (a2ll students registered for the selected course).");
                    System.out.println("[ print only student’s name, gender and nationality ]");
                    System.out.println("7. Logout");
                    System.out.println("8. Print available courses");

                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1: {
                            EditStudentAccessPeriod();
                            break;
                        }
                        case 2: {
                            AddStudent();
                            break;
                        }
                        case 3: {
                            adminAddCourse();
                            break;
                        }
                        case 4: {
                            // checked can
                            checkVacancy();
                            break;
                        }
                        case 5: {
                            printStudentListByCIndex();
                            break;
                        }
                        case 6: {
                            printStudentListByCourse();
                            break;
                        }
                        case 7: {
                            runnning = false;
                            break;
                        }

                        case 8: {
                            getAvailCourse();
                            break;
                        }
                        default: {

                        }

                    }
                }

            case "student": {
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
                            // addCourse(matricNum);
                            break;
                        }
                        case 2: {
                            // dropCourse(matricNum);
                            break;
                        }
                        case 3: {
                            checkPrintCourse(matricNum);
                            break;
                        }
                        case 4: {
                            vacancyAvailable();
                            break;
                        }
                        case 5: {
                            changeIndex(matricNum);
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
            default: {

            }
        }

    }

    private static void adminAddCourse() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        ArrayList<Cindex> CindexList = new ArrayList<Cindex>();

        DateFormat timeformat = new SimpleDateFormat("HHmm");
        Scanner sc = new Scanner(System.in);

        System.out.println("Add new Course ");
        System.out.println("Course Code: ");          //check for duplicates
        String CourseCode = sc.next();
        sc.nextLine();

        System.out.println("Course Name: ");
        String CourseName = sc.nextLine();

        System.out.println("Course Description: ");
        String CourseDescription = sc.nextLine();

        System.out.println("Course AUs: ");
        int AU = sc.nextInt();

        Course courseObj = new Course(CourseCode, CourseName, CourseDescription, AU, studentList, CindexList);

        int choice = -1;

        ArrayList<Cindex> cindexArrayList = new ArrayList<>();

        while (choice != 0) {
            System.out.println("Add new index: ");
            System.out.println("0.Quit");
            System.out.println("1.Add new index");
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.println("Index: ");
                    String index = sc.next();

                    System.out.println("Capacity: ");
                    int Capacity = sc.nextInt();

                    Cindex CindexObj = new Cindex(index, Capacity);

                    ArrayList<Lesson> schedule = new ArrayList<Lesson>();
                    while (choice != 0) {
                        System.out.println("Add new lesson: ");
                        System.out.println("0.Stop adding lesson ");
                        System.out.println("1.Add new Lecture ");
                        System.out.println("2.Add new Tutorial ");
                        System.out.println("3.Add new Lab ");

                        choice = sc.nextInt();

                        switch (choice) {
                            case 0:
                                break;
                            case 1:
                                System.out.println("Start Time: ");
                                String startTimeLect = sc.next();
                                Date startTimeParsedLect = null;
                                try {
                                    startTimeParsedLect = timeformat.parse(startTimeLect);
                                } catch (ParseException e) {

                                    e.printStackTrace();
                                }

                                System.out.println("End Time: ");
                                String endTimeLect = sc.next();
                                Date endTimeParsedLect = null;
                                try {
                                    endTimeParsedLect = timeformat.parse(endTimeLect);
                                } catch (ParseException e) {

                                    e.printStackTrace();
                                }

                                System.out.println("Venue: ");
                                String venueLect = sc.next();

                                System.out.println("Day Of Week: ");
                                String dayOfweekLect = sc.next();

                                Lecture lecture = new Lecture(startTimeParsedLect, endTimeParsedLect, venueLect,
                                        dayOfweekLect);

                                schedule.add(lecture);
                                break;
                            case 2:
                                System.out.println("Start Time: ");
                                String startTimeTut = sc.next();
                                Date startTimeParsedTut = null;
                                try {
                                    startTimeParsedTut = timeformat.parse(startTimeTut);
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                                System.out.println("End Time: ");
                                String endTimeTut = sc.next();
                                Date endTimeParsedTut = null;
                                try {
                                    endTimeParsedTut = timeformat.parse(endTimeTut);
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                                System.out.println("Venue: ");
                                String venueTut = sc.next();

                                System.out.println("Day Of Week: ");
                                String dayOfweekTut = sc.next();

                                Tutorial tutorial = new Tutorial(startTimeParsedTut, endTimeParsedTut, venueTut,
                                        dayOfweekTut);

                                schedule.add(tutorial);
                                break;
                            case 3:
                                System.out.println("Start Time: ");
                                String startTimeLab = sc.next();
                                Date startTimeParsedLab = null;
                                try {
                                    startTimeParsedLab = timeformat.parse(startTimeLab);
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                                System.out.println("End Time: ");
                                String endTimeLab = sc.next();
                                Date endTimeParsedLab = null;
                                try {
                                    endTimeParsedLab = timeformat.parse(endTimeLab);
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                                System.out.println("Venue: ");
                                String venueLab = sc.next();

                                System.out.println("Day Of Week: ");
                                String dayOfweekLab = sc.next();

                                System.out.println("Odd or Even: ");
                                String oddOrEvenLab = sc.next();

                                Labs lab = new Labs(startTimeParsedLab, endTimeParsedLab, venueLab, dayOfweekLab,
                                        oddOrEvenLab);

                                schedule.add(lab);
                                break;

                        }
                    }
                    CindexObj.setSchedule(schedule);
                    cindexArrayList.add(CindexObj);
            }
        }
        courseObj.setListCindex(cindexArrayList);
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayList<Course> courseList = databaseManager.DeserializeCourseList();
        courseList.add(courseObj);
        databaseManager.SerializeCourseList(courseList);
        System.out.println("Course added");
    }

    private static void printStudentListByCIndex() {
        String coursecode;
        String cindex;
        ArrayList<Student> studentList = null;
        DatabaseManager databaseManager = new DatabaseManager();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter coursecode: ");
        coursecode = sc.next();
        if (coursecode.equals("#"))
            return;

        System.out.println("Please enter index: ");
        cindex = sc.next();

        studentList = databaseManager.getStudentList(coursecode, cindex);

        if (studentList != null) {
            System.out.printf("student in %s\n", coursecode);

            for (int i = 0; i < studentList.size(); i++) {
                System.out.printf("%d. %s %s", i, studentList.get(i).getFirstName(), studentList.get(i).getLastName());
            }

        } else {
            System.out.println("course index not found! please try again!");
        }
    }

    private static void printStudentListByCourse() {
        String coursecode;
        ArrayList<Student> studentList = null;
        DatabaseManager databaseManager = new DatabaseManager();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter coursecode: ");
        coursecode = sc.next();
        if (coursecode.equals("#"))
            return;

        studentList = databaseManager.getStudentList(coursecode);

        if (studentList != null) {           
            System.out.printf("student in %s \n", coursecode);

            for (int i = 0; i < studentList.size(); i++) {
                System.out.printf("%d. %s %s ", i+1, studentList.get(i).getFirstName(), studentList.get(i).getLastName());
            }
        } else {
            System.out.println("course not found! please try again!");
        }
        System.out.println();
    }

    private static void getAvailCourse(){
        ArrayList<Course> courseList = null;
        DatabaseManager databaseManager = new DatabaseManager();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Press any key to proceed");
        String coursecode = sc.next();
        if (coursecode.equals("#"))
            return;
        
        courseList = databaseManager.getCourseList();

        if(courseList != null){
            System.out.println("courses: ");

            for(int i = 0; i<courseList.size(); i++){
                System.out.printf("%d. %s : \n", i+1, courseList.get(i).getCourseCode());

                //for(int j=0; j<courseList.get(i).getListCindex().size(); j++){
                    System.out.printf("\t %s \n", courseList.get(i).getListCindex());
                //}
            }
        }else{
            System.out.println("no courses added yet");
        }

        System.out.println();
        
    }

    private static void checkVacancy() {
        String coursecode;
        String cindex;

        DatabaseManager databaseManager = new DatabaseManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter coursecode: ");
        coursecode = sc.next();
        if (coursecode.equals("#"))
            return;

        System.out.println("Please enter index: ");
        cindex = sc.next();

        int vacancy = databaseManager.getVacancyCindex(coursecode, cindex);

        if (vacancy != -1) {
            System.out.printf("course code: %s \n index: %s \n vacancy: %d\n", coursecode, cindex, vacancy);
        } else {
            System.out.println("course index not found! please try again!");
        }
    }

private static void addCourse( String matricNum){
    //add course
    //chekc timetable clash
    // check vacancy of Cindex
    // if full go waiting list
    //if have vacancy register for C index

    Scanner sc = new Scanner(System.in);
    DatabaseManager databaseManager = new DatabaseManager();
    int choice ;
    while(choice != 0){
        System.out.println("Enter 0 to return to main menu ");
        System.out.println("Please enter coursecode: ");
        String coursecode = sc.next();
    
        if (coursecode.equals("#"))
            return;

        Course singleCourse = databaseManager.searchSingleCourse(coursecode);
        if (singleCourse != null){
            System.out.println(singleCourse.getCourseDescription());

            // print list of indexes and vacancies in the course 
            // shud show timetable clash for each index
            //show index lesson timings

            System.out.printf("%s %s", singleCourse.getCourseCode(),singleCourse.getCourseName());
            System.out.println("-------------------------------------");
            System.out.println("index   /   vacacy   /    waitlist");
            for (int i = 0; i < singleCourse.getListCindex().size();i++){
                Cindex singleindex = singleCourse.getListCindex().get(i);
                System.out.printf("%d.  %s  /  %d  /  %d",i+1, singleindex.getIndex(),singleindex.getCurrentVacancy(), singleindex.getWaitList().size());
            }
            
        }else{
            System.out.println("course not found! please enter course code again ");
            continue;
        }
        
        System.out.println("Please enter choice: ");
        System.out.println("Enter \' # \'to go back to main menu");
        String choiceIndex = sc.next();
        if (coursecode.equals("#"))
            return;
        else{
            Cindex singleIndex = singleCourse.getListCindex().get(Integer.parseInt(choiceIndex));
            
        }
        
        
        if(){  //how to check timetable clash?
            
            System.out.println("Unable to add because of timetable clash!");
        }
        // else if(Course.getAU() > Student.getNumAuAvail() || Student.getNumAuAvail() == 0){
        //     System.out.println("Not enough AUs");
        // }
        else if(vacancy != -1){
            //add course stuff
            Student.minusAU();// minus amt of au of student left
            System.out.println("Course added!");
        }
        else{
            System.out.println("Course index full! Adding to waitlist.");
            //add to waitlist stuff and 2 courses cant clash
        }

    }
    
}

    private static void EditStudentAccessPeriod() {
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

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.EditStudentAccessPeriod(matricNum, accessStartTime, accessEndTime);

        System.out.printf("Access time for %s changed!\n", matricNum);
    }

    private static void dropCourse(String matricNum) {
        // have to show the student reg courses
        // remove the student from the courses registered students
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter coursecode: ");
        String coursecode = sc.next();
        if (coursecode.equals("#"))
            return;
        // else ... error checking

        System.out.println("Are you sure?");
        System.out.println("1-yes 0-no");
        int choice = sc.nextInt();

        while (choice != 1 || choice != 0) {
            if (choice == 1) {
                // drop course stuff
                ArrayList<Student> studentList;

                Student.removeCourseMain(matricNum, coursecode);
                Student.plusAU(); // add back amt of au to student
                System.out.println("Course dropped!");
            } else if (choice == 0) {
                return;
            } else {
                System.out.println("invalid choice!");
            }
        }

    }

    private static void checkPrintCourse(String matricNum) {
        Scanner sc = new Scanner(System.in);

        Student.printCourseMain(matricNum);
        System.out.println("press enter to return to main menu ");

        String code = sc.next();
    }

    private static void AddStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter MatricNum: ");
        String matricNum = sc.next();

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

        Student studentObj = new Student(firstname, lastname, gender, nationality, matricNum, username, password,
                numAUs, accessStartDateTime, accessEndDateTime);

        boolean unique = studentObj.verifyUniqueMatricNum(matricNum);

        while (unique != true) {
            System.out.println("MatricNum not Unique! Please enter MatricNum: ");
            matricNum = sc.next();
            studentObj.setMatricNum(matricNum);
        }
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.addStudentintoStudentDB(studentObj);
    }

    private static void vacancyAvailable() {
        Scanner sc = new Scanner(System.in);
    
        int vacancy;
        String coursecode;
        String cindex;
        Course courseobj;
    
        System.out.println("Please enter coursecode: ");
        coursecode = sc.next();

        DatabaseManager databaseManager = new DatabaseManager();
        courseobj = databaseManager.searchCourse(coursecode);
        ArrayList<Cindex> CindexList = courseobj.getListCindex();

        for(int i=0; i<CindexList.size(); i++){
            System.out.println("index: " + CindexList.get(i).getIndex() + "Vacancy: " +  CindexList.get(i).getCurrentVacancy());
        }
    
    }

    private static void changeIndex(String matricnumber){
        Scanner sc = new Scanner(System.in);
        StudentCourse studentCourse = null;
        int indexOfRegisteredCourse = 0;
        String input;

        System.out.println("Please enter coursecode that you want to change index: ");
        input = sc.next();

        DatabaseManager databaseManager = new DatabaseManager();
        ArrayList<Student> studentList = databaseManager.DeserializeStudentList();
        Student studentobj = databaseManager.getStudentbyMatricNum(matricnumber, studentList);
        ArrayList<StudentCourse> registeredcourse = studentobj.getRegisteredCourse();
        ArrayList<Course> courseList = databaseManager.DeserializeCourseList();

        for(int i=0; i < registeredcourse.size(); i++){
            if(registeredcourse.get(i).getCourseCode().equals(input)){
                studentCourse = registeredcourse.get(i);
                indexOfRegisteredCourse = i;
            }
            break;
        }

        System.out.println("Your Current index: " + studentCourse.getIndex().getIndex());
        System.out.println("New index: ");
        input = sc.next();

        Cindex newindex = databaseManager.searchCindex(studentCourse.getCourseCode(), input);
        Cindex oldindex = databaseManager.searchCindex(studentCourse.getCourseCode(), studentCourse.getIndex().getIndex());
        if(newindex.getCurrentVacancy() == 0){
            
            newindex.getWaitList().add(studentobj);
            registeredcourse.remove(indexOfRegisteredCourse);
            System.out.println("index is removed from your registered course, you are placed in waitlist");
        }else{
            newindex.getRegisteredStudents().add(studentobj);
            oldindex.getRegisteredStudents().remove(studentobj);

            studentCourse.setIndex(newindex);
            System.out.println("you have changed your index successfully");
        }

    }

}