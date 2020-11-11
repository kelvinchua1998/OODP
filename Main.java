import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean runnning = true;
        String userType = null;

        Login login = new Login();
        Scanner sc = new Scanner(System.in);
        String username = null;
        String password = null;

        while (userType == null) {
            System.out.println("Please Enter Username:");

            username = sc.next();

            System.out.println("Please Enter Password");
            password = sc.next();

            userType = login.verifyUser(username, password);

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
                        default: {

                        }

                    }
                }

            case "student":{
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
                            addCourse(username);
                            break;
                        }
                        case 2: {
                            dropCourse(username);
                            break;
                        }
                        case 3: {
                            checkPrintCourse(username);
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
            default:{

            }
        }

    }

    private static void adminAddCourse() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        ArrayList<Cindex> CindexList = new ArrayList<Cindex>();

        DateFormat timeformat = new SimpleDateFormat("HHmm");
        Scanner sc = new Scanner(System.in);

        System.out.println("Add new Course ");
        System.out.println("Course Code: ");
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

                    System.out.println("Add new lesson: ");
                    System.out.println("0.Stop adding lesson ");
                    System.out.println("1.Add new Lecture ");
                    System.out.println("2.Add new Tutorial ");
                    System.out.println("3.Add new Lab ");

                    ArrayList<Lesson> schedule = new ArrayList<Lesson>();
                    while (choice != 0) {
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
            }
        }

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

    studentList =databaseManager.getStudentList(coursecode, cindex);

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

        studentList = databaseManager.getStudentListbyCourse(coursecode);

        if (studentList != null) {
            System.out.printf("student in %s\n", coursecode);

            for (int i = 0; i < studentList.size(); i++) {
                System.out.printf("%d. %s %s", i, studentList.get(i).getFirstName(), studentList.get(i).getLastName());
            }
        } else {
            System.out.println("course not found! please try again!");
        }
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

private static void addCourse( String username){
    //add course
    //chekc timetable clash
    // check vacancy of Cindex
    // if full go waiting list
    //if have vacancy register for C index

    Scanner sc = new Scanner(System.in);
    DatabaseManager databaseManager = new DatabaseManager();
    int choice ;
    Course singleCourse;
    Cindex singleIndex;
    Student stud;

    //get student

    stud = databaseManager.getStudentbyMatricNum(username);

    while(choice != 0){
        System.out.println("Enter 0 to return to main menu ");
        System.out.println("Please enter coursecode: ");
        String coursecode = sc.next();
    
        if (coursecode.equals("#"))
            return;

        singleCourse = databaseManager.searchSingleCourse(coursecode);
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
            singleIndex = singleCourse.getListCindex().get(Integer.parseInt(choiceIndex));
        }
        
        // check timetable clash
        
        if(databaseManager.checkClashforStudent(username, coursecode, singleIndex.getIndex());){ 
            //CLASH
            System.out.println("Unable to add because of timetable clash!");
            //go back to index selection screen
        }else if(stud.getNumAuAvail() < singleCourse.getAU()){
            //inssufficient AUs
            System.out.println("Unable to add due to insuffiecient AUs!");
            //go back to index selection screen
        }else{
            //no clash found
            //if got vacancy add stud
            //if no vacancy add into waitlist
            if(singleIndex.getCurrentVacancy() > 0){
                //add course into student reg courses
                // add studnet into courses reg stud list
                // minus student s available aus

                stud.minusAU(singleCourse);
                singleIndex.addRegisteredStudent(stud);

                // create a new studentCourse
                StudentCourse newlyregisteredCourse = new StudentCourse(singleCourse.getCourseCode(), singleCourse.getCourseName(),singleCourse.getCourseDescription(), singleIndex);
                stud.addCourse(newlyregisteredCourse);

                //update database
                databaseManager.updateDatabase(stud);
                databaseManager.updateDatabase(singleCourse);

                System.out.println("Course added!");
            }
            else{
                //add stud to waitlist
                // add Cindex to student waitlist
                StudentCourse newlyregisteredCourse = new StudentCourse(singleCourse.getCourseCode(), singleCourse.getCourseName(),singleCourse.getCourseDescription(), singleIndex);
                stud.addWaitlist(newlyregisteredCourse);

                singleIndex.addWaitlistStudent(stud);

                databaseManager.updateDatabase(stud);
                databaseManager.updateDatabase(singleCourse);

                System.out.println("Course index full! Adding to waitlist.");
            }
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

        Student studentObj = new Student(firstname, lastname, gender, nationality, matricNum, username, password,numAUs, accessStartDateTime, accessEndDateTime);
        
        boolean unique = studentObj.verifyUniqueMatricNum(matricNum);

        while (unique != true) {
            System.out.println("MatricNum not Unique! Please enter MatricNum: ");
            matricNum = sc.next();
            studentObj.setMatricNum(matricNum);
        }
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.addStudentintoStudentDB(studentObj);
    }
}