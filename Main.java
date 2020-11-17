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
        boolean mainRunning = true;
        while (mainRunning) {

            boolean runnning = true;
            String userType = null;

            Login login = new Login();
            Scanner sc = new Scanner(System.in);
            String username = null;
            String password = null;

            while (userType == null) {
                System.out.println("==================================================");
                System.out.println("Welcome to NTU STARS Planner");
                System.out.println("Please Enter Username:");

                username = sc.next();

                System.out.println("Please Enter Password");
                password = sc.next();

                userType = login.verifyUser(username, password);

                if (userType == null) {
                    System.out.println("Wrong Username and Password!");
                } else {
                    System.out.println("User Verified!");
                }
            }

            switch (userType) {
                case "admin":{
                    while (runnning) {
                        System.out.println("==================================================");
                        System.out.println("Welcome Admin!");
                        System.out.println("Select your option(1-6)");
                        System.out.println("1. Edit student access period");
                        System.out.println("2. Add a student (name, matric number, gender, nationality, etc)");
                        System.out.println("3. Add a course.");
                        System.out.println("4. update a course (course code, school, its index numbers and vacancy).");
                        System.out.println("5. Check available slot for an index number (vacancy in a class)");
                        System.out.println("6. Print student list by index number.");
                        System.out.println(
                                "7. Print student list by course (all students registered for the selected course).");
                        System.out.println("[ print only student’s name, gender and nationality ]");
                        System.out.println("8. Logout");
                        System.out.println("9. Print available courses");

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
                                updateCourse();
                                break;
                            }
                            case 5: {
                                // WORKING
                                checkVacancy();
                                break;
                            }
                            case 6: {
                                // WORKING
                                printStudentListByCIndex();
                                break;
                            }
                            case 7: {
                                // WORKING
                                printStudentListByCourse();
                                break;
                            }
                            case 8: {
                                runnning = false;
                                break;
                            }

                            case 9: {
                                getAvailCourse();
                                break;
                            }
                            default: {
                                System.out.println("invalid input! please try again!");
                            }

                        }
                    }
                    break;
                }
                case "student":{
                    // verify access Time
                    if (verifyAccessTime(username)) {
                        while (runnning) {
                            System.out.println("==================================================");
                            System.out.println("Welcome Student!");
                            System.out.println("Select your option(1-6)");
                            System.out.println("1. *Add Course");
                            System.out.println("2. Drop Course");
                            System.out.println("3. Check/Print Courses Registered");
                            System.out.println("4. Check Vacancies Available");
                            System.out.println("5. Change Index Number of Course");
                            System.out.println(
                                    "6. Swop Index Number with Another Student[refer to STARSPlanner STARSUserGuidev1_extracted.pdf for details of functions - ignore the Graphical display]");
                            System.out.println("7. Logout");

                            int choice = sc.nextInt();
                            switch (choice) {
                                case 1: {
                                    // working for student
                                    addCourse(username);
                                    break;
                                }
                                case 2: {
                                    // working
                                    dropCourse(username);
                                    break;
                                }
                                case 3: {
                                    // working for no course registered
                                    // working for course registered
                                    checkPrintCourseRegistered(username);
                                    break;
                                }
                                case 4: {
                                    // working
                                    vacancyAvailable();
                                    break;
                                }
                                case 5: {
                                    changeIndex(username);
                                    break;
                                }
                                case 6: {
                                    swapIndexWithAnotherStudent(username);
                                    break;
                                }
                                case 7: {
                                    runnning = false;
                                    break;
                                }
                                default: {
                                    System.out.println("invalid input! please try again!");
                                }

                            }

                        }
                    } else {
                        // Using Calendar class
                        Calendar cal = Calendar.getInstance();
                        // get Date from calendar
                    
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);      // NOTE!!! : Month from 0 to 11
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        int minute = cal.get(Calendar.MINUTE);
                        int second = cal.get(Calendar.SECOND);

                        System.out.printf("Now is %4d/%02d/%02d %02d:%02d:%02d, Login at your access Time period! ",year, month+1, day, hour, minute, second);
                    }
                    break;
                }
                default: {

                }
            }
        }
    }

    private static boolean verifyAccessTime(String username) {
        DatabaseManager databaseManager = new DatabaseManager();
        Student studentObj = (Student) databaseManager.getObjectbyUsername(username);

        // Using Calendar class
        Calendar cal = Calendar.getInstance();
        // get Date from calendar
        Date dateNow = cal.getTime();

        if (dateNow.getTime() >= studentObj.getAccessStartTime()
                && dateNow.getTime() <= studentObj.getAccessEndTime()) {
            return true;
        }
        return false;
    }

    private static void updateCourse() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Course Code which you want to update: ");
        String courseCode = sc.next();

        DatabaseManager databaseManager = new DatabaseManager();
        Course courseObj = databaseManager.searchCourse(courseCode);

        if (courseObj != null) {
            System.out.println("Course Code: " + courseObj.getCourseCode());
            System.out.println("Course Name: " + courseObj.getCourseName());
            System.out.println("school: " + courseObj.getSchool());
            System.out.println("Course Description: " + courseObj.getCourseDescription());
            System.out.println("Number of AUs: " + courseObj.getAU());

            ArrayList<Cindex> CindexList = courseObj.getListCindex();
            for (int i = 0; i < CindexList.size(); i++) {
                System.out.println(
                        "Index " + CindexList.get(i).getIndex() + " Capacity: " + CindexList.get(i).getCapacity());
            }
            int choice = -1;
            ArrayList<Cindex> cindexList = courseObj.getListCindex();
            while (choice != 0) {
                System.out.println("update options:");
                System.out.println("0. exit");
                System.out.println("1. Update Course Code");
                System.out.println("2. Update School");
                System.out.println("3. Update Capacity");
                System.out.println("4. Update Index");
                System.out.println("5. Delete Index");
                choice = sc.nextInt();

                switch (choice) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        System.out.println("Enter new Course Code:");
                        String newCourseCode = sc.next();

                        ArrayList<Course> courselist = databaseManager.DeserializeCourseList();
                        courselist.remove(courseObj);

                        if (databaseManager.verifyUniqueCourseCode(newCourseCode)) {
                            courseObj.setCourseCode(newCourseCode);
                        } else {
                            System.out.println("Course Code not unique");
                        }

                        courselist.add(courseObj);
                        databaseManager.SerializeCourseList(courselist);

                        choice = -1;
                        break;
                    }
                    case 2: {
                        System.out.println("Enter new School:");
                        String newSchool = sc.next();

                        courseObj.setSchool(newSchool);
                        choice = -1;
                        break;
                    }
                    case 3: {

                        System.out.println("Enter the index to change Capacity:");
                        String index = sc.next();

                        Cindex cindexobj = databaseManager.searchCindex(courseCode, index);   

                        if(cindexobj == null){
                            System.out.println("Index does not exist!");
                            System.out.println();
                            break;
                        }
                        else{
                            System.out.println("Enter new Capacity:");
                            int newCapacity = sc.nextInt();

                            for (int i = 0; i < cindexList.size(); i++) {
                                 if (cindexList.get(i).getIndex().equals(index)) {
                                    cindexList.remove(i);
                                    break;
                                }
                            }

                            cindexobj.setCapacity(newCapacity);
                            cindexList.add(cindexobj);

                            courseObj.setListCindex(cindexList);
                            choice = -1;
                        }   
                        break;
                    }
                    case 4: {

                        System.out.println("Enter the Index to change new Index Number:");
                        String index = sc.next();
                        
                        Cindex cindexobj = databaseManager.searchCindex(courseCode, index);

                        if(cindexobj == null){
                            System.out.println("Index does not exist!");
                            System.out.println();
                            break;
                        }
                        else{
                            System.out.println("Enter new Index:");
                            String newIndex = sc.next();
                        

                            for (int i = 0; i < cindexList.size(); i++) {
                                if (cindexList.get(i).getIndex().equals(index)) {
                                    cindexList.remove(i);
                                    break;
                                }
                            }

                            cindexobj.setIndex(newIndex);
                            cindexList.add(cindexobj);

                            courseObj.setListCindex(cindexList);
                            choice = -1;
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Enter the Index that you want to delete:");
                        String index = sc.next();

                        for (int i = 0; i < cindexList.size(); i++) {
                            if (cindexList.get(i).getIndex().equals(index)) {
                                cindexList.remove(i);
                                break;
                            }
                        }

                        courseObj.setListCindex(cindexList);
                        choice = -1;
                        break;
                    }
                }

            }

            databaseManager.updateDatabase(courseObj);

        } else {
            System.out.println("Course Code does not exists in DataBase");
        }
        System.out.println(); // space btwn current func & main menu
    }

    private static void adminAddCourse() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        ArrayList<Cindex> CindexList = new ArrayList<Cindex>();

        DateFormat timeformat = new SimpleDateFormat("HHmm");
        Scanner sc = new Scanner(System.in);

        System.out.println("Add new Course ");
        System.out.println("Course Code: "); // check for duplicates
        String CourseCode = sc.next();
        sc.nextLine();

        System.out.println("Course Name: ");
        String CourseName = sc.nextLine();

        System.out.println("Course Description: ");
        String CourseDescription = sc.nextLine();

        System.out.println("Course School: ");
        String school = sc.nextLine();

        System.out.println("Course AUs: ");
        int AU = sc.nextInt();

        Course courseObj = new Course(CourseCode, CourseName, CourseDescription, school, AU, studentList, CindexList);

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
                    int choice2 = -1;
                    while (choice2 != 0) {
                        System.out.println("Add new lesson: ");
                        System.out.println("0.Stop adding lesson ");
                        System.out.println("1.Add new Lecture ");
                        System.out.println("2.Add new Tutorial ");
                        System.out.println("3.Add new Lab ");

                        choice2 = sc.nextInt();

                        switch (choice2) {
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
        System.out.println();
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

        if (studentList.size() != 0) {
            System.out.printf("student in %s\n", coursecode);

            for (int i = 0; i < studentList.size(); i++) {
                System.out.printf("%d. %s %s \n", i + 1, studentList.get(i).getFirstName(),
                        studentList.get(i).getLastName());
            }

        } else if (studentList.size() == 0) {
            System.out.printf("There are no registered students in %s\n", coursecode);
        } // else if(studentList == null){ //dsnt even print this
          // System.out.println("course index not found! please try again!");
          // }
        System.out.println();
    }

    private static void printStudentListByCourse() {
        String coursecode;
        ArrayList<Student> studentList;
        DatabaseManager databaseManager = new DatabaseManager();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter coursecode: ");
        coursecode = sc.next();
        if (coursecode.equals("#"))
            return;

        studentList = databaseManager.getStudentListbyCourse(coursecode);

        if (studentList.size() != 0) {
            System.out.printf("student in %s \n", coursecode);

            for (int i = 0; i < studentList.size(); i++) {
                System.out.printf("%d. %s %s \n", i + 1, studentList.get(i).getFirstName(),
                        studentList.get(i).getLastName());
            }
        } else if (studentList.size() == 0) {
            System.out.printf("There are no registered students in %s \n", coursecode);
        } // else if(studentList == null){
          // System.out.println("course not found! please try again!");
          // }
        System.out.println();
    }

    private static void getAvailCourse() {
        ArrayList<Course> courseList = null;
        DatabaseManager databaseManager = new DatabaseManager();
        Course singleCourse;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \' # \' to return to main menu ");
        System.out.println("Press any key to proceed");
        String coursecode = sc.next();
        if (coursecode.equals("#"))
            return;

        courseList = databaseManager.getCourseList();

        while (coursecode != "#") {
            if (coursecode.equals("#"))
                return;

            else if (courseList != null) {
                System.out.println("courses: ");

                for (int i = 0; i < courseList.size(); i++) {
                    System.out.printf("%d. %s : \n", i + 1, courseList.get(i).getCourseCode());

                    System.out.printf("\t %s \n", courseList.get(i).getCourseDescription());
                    
                    singleCourse = databaseManager.searchCourse(courseList.get(i).getCourseCode());

                    for (int j = 0; j < singleCourse.getListCindex().size(); j++) {
                        System.out.printf("\t %s: %s\n", singleCourse.getListCindex().get(j),
                                singleCourse.getListCindex().get(j).getSchedule());
                    }
                }
            } else {
                System.out.println("no courses added yet");
            }
            System.out.println("Enter \' # \' to return to main menu ");
            coursecode = sc.next();
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

    private static void addCourse(String username) {
        // add course
        // chekc timetable clash
        // check vacancy of Cindex
        // if full go waiting list
        // if have vacancy register for C index

        Scanner sc = new Scanner(System.in);
        DatabaseManager databaseManager = new DatabaseManager();
        int choice = -1;
        Course singleCourse;
        Cindex singleIndex;
        Student stud;

        // get student

        stud = (Student) databaseManager.getObjectbyUsername(username);

        while (choice != 0) {
            System.out.println("Enter \'#\' to return to main menu "); // didnt initialize
            // choice. had error
            System.out.println("Please enter coursecode: ");
            String coursecode = sc.next();
            if (coursecode.equals("#"))
                return;

            singleCourse = databaseManager.searchCourse(coursecode);
            if (singleCourse != null) {

                // check whether the course is already registered
                if (databaseManager.checkStudentRegisteredCourses(stud, singleCourse)) {
                    // true => the course is already registered
                    System.out.println("Course already registered!");
                    continue;
                }
                System.out.printf("%s %s \n", singleCourse.getCourseCode(), singleCourse.getCourseName());
                System.out.println("Description: " + singleCourse.getCourseDescription());

                // print list of indexes and vacancies in the course
                // shud show timetable clash for each index
                // show index lesson timings

                System.out.println("-------------------------------------");
                System.out.println("index   /   vacacy   /    waitlist");
                for (int i = 0; i < singleCourse.getListCindex().size(); i++) {
                    Cindex singleindex = singleCourse.getListCindex().get(i);
                    System.out.printf("%d.  %s  /  %d  /  %d\n", i + 1, singleindex.getIndex(),
                            singleindex.getCurrentVacancy(), singleindex.getWaitList().size());
                }

            } else {
                System.out.println("course not found! please enter course code again ");
                continue;
            }

            System.out.println("Please enter choice: ");
            System.out.println("Enter \'#\'to go back to main menu");
            String choiceIndex = sc.next();

            if (choiceIndex.equals("#"))
                return;
            else {
                singleIndex = singleCourse.getListCindex().get(Integer.parseInt(choiceIndex) - 1);
            }

            // check timetable clash

            if (databaseManager.checkClashforStudent(username, coursecode, singleIndex.getIndex())) {
                // CLASH
                System.out.println("Unable to add because of timetable clash!");
                // go back to index selection screen
            } else if (21 - stud.getNumAuRegistered() < singleCourse.getAU()) {
                // inssufficient AUs
                System.out.println("Unable to add due to insufficient AUs!");
                // go back to index selection screen
            } else {
                // no clash found
                // if got vacancy add stud
                // if no vacancy add into waitlist
                if (singleIndex.getCurrentVacancy() > 0) {
                    // add course into student reg courses
                    // add studnet into courses reg stud list
                    // minus student s available aus

                    stud.minusAU(singleCourse);
                    singleIndex.addRegisteredStudent(stud);

                    // create a new studentCourse
                    StudentCourse newlyregisteredCourse = new StudentCourse(singleCourse.getCourseCode(),
                            singleCourse.getCourseName(), singleCourse.getCourseDescription(), singleIndex);
                    stud.addCourse(newlyregisteredCourse);

                    // update database
                    databaseManager.updateDatabase(stud);
                    databaseManager.updateDatabase(singleCourse);

                    SendMail sendMail = new SendMail();
                    String EmailContent = "Dear Sir/Mdm,\n This a confirmation email that your course "+singleCourse.getCourseCode()+" "+singleCourse.getCourseName() + " index "+ singleIndex.getIndex() +" have been successfully added\n Thank You\n NTU STARS";
                    sendMail.sendgmail("melvinchuaqwerty@gmail.com", "melvinchuaqwerty@gmail.com", "s9825202i",
                            stud.getEmail(), "Course Added", EmailContent);

                    System.out.println("Course added!");
                } else {
                    // add stud to waitlist
                    // add Cindex to student waitlist
                    StudentCourse newlyregisteredCourse = new StudentCourse(singleCourse.getCourseCode(),
                            singleCourse.getCourseName(), singleCourse.getCourseDescription(), singleIndex);
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
        DatabaseManager databaseManager = new DatabaseManager();

        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter MatricNum: ");
        String matricNum = sc.next();
        if (matricNum.equals("#"))
            return;

        Student StudentObj = databaseManager.getStudentbyMatricNum(matricNum);
        if (StudentObj == null) {
            System.out.println("Student does not exist with that Matric Number!");
        } else {
            int year;
            int month;
            int day;
            int hour;
            int minute;

            System.out.println("AccessStartDateTime: ");
            System.out.println("Please enter year (YYYY): ");
            year = sc.nextInt();
            System.out.println("Please enter month (MM)JAN-1,FEB-2.....DEC-12: ");
            month = sc.nextInt();
            System.out.println("Please enter day (DD): ");
            day = sc.nextInt();
            System.out.println("Please enter hour (hh): ");
            hour = sc.nextInt();
            System.out.println("Please enter minute (mm): ");
            minute = sc.nextInt();

            Calendar accessStartTime = new GregorianCalendar(year, month-1, day, hour, minute);

            System.out.println("AccessEndDateTime: ");
            System.out.println("Please enter year (YYYY): ");
            year = sc.nextInt();
            System.out.println("Please enter month (MM)JAN-1,FEB-2.....DEC-12: ");
            month = sc.nextInt();
            System.out.println("Please enter day (DD): ");
            day = sc.nextInt();
            System.out.println("Please enter hour (hh): ");
            hour = sc.nextInt();
            System.out.println("Please enter minute (mm): ");
            minute = sc.nextInt();

            Calendar accessEndTime = new GregorianCalendar(year, month-1, day, hour, minute);

            databaseManager.EditStudentAccessPeriod(matricNum, accessStartTime, accessEndTime);
        }
    }

    private static void dropCourse(String username) {
        // have to show the student reg courses
        // remove the student from the courses registered students
        Scanner sc = new Scanner(System.in);
        DatabaseManager databaseManager = new DatabaseManager();
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter coursecode: ");
        String coursecode = sc.next();
        boolean running = true;
        while(running){
            if (coursecode.equals("#"))
                return;
            // else ... error checking
            if(databaseManager.checkStudentReg(username, coursecode)){
                System.out.println("Are you sure? [y/n]");
                String choice = sc.next();

                

                if (choice.equals("y")) {
                    
                    databaseManager.removeCourseMain(username, coursecode);

                    System.out.println("Course dropped!");
                } else if (choice.equals("n")) {
                    return;
                } else {
                    System.out.println("invalid choice!");
                }
            }else{
                System.out.println("cant find course in your registered courses! pls try agaain");
            }
        }
        
    }

    private static void checkPrintCourseRegistered(String username) {
        Scanner sc = new Scanner(System.in);
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.printCourseRegistered(username);
        System.out.println("enter any key to return to main menu ");
        sc.next();
    }

    private static void AddStudent() {
        DatabaseManager databaseManager = new DatabaseManager();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter \' # \'to return to main menu ");
        System.out.println("Please enter MatricNum: ");

        String matricNum = sc.next();
        boolean uniqueMatric = databaseManager.verifyUniqueMatricNum(matricNum);
        if(!uniqueMatric){
            System.out.println("Matric number not unique!");
            return;
        }

        System.out.println("Please enter Username: ");
        String username = sc.next();

        boolean uniqueUsername = databaseManager.verifyUniqueMatricNum(matricNum);
        if(!uniqueUsername){
            System.out.println("Username not unique!");
            return;
        }

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

        System.out.println("Please enter email: ");
        String email = sc.next();

        long accessStartDateTime = new GregorianCalendar(2020, 01, 01, 00, 00).getTimeInMillis();
        long accessEndDateTime = new GregorianCalendar(2021, 01, 01, 00, 00).getTimeInMillis();

        Student studentObj = new Student(firstname, lastname, gender, nationality, matricNum, username, password,
                accessStartDateTime, accessEndDateTime, email);

        databaseManager.addStudentintoStudentDB(studentObj);
    }

    private static void vacancyAvailable() {
        // Scanner sc = new Scanner(System.in);

        // int vacancy;
        // String coursecode;
        // String cindex;
        // Course courseobj;

        // System.out.println("Please enter coursecode: ");
        // coursecode = sc.next();

        // DatabaseManager databaseManager = new DatabaseManager();
        // courseobj = databaseManager.searchCourse(coursecode);
        // ArrayList<Cindex> CindexList = courseobj.getListCindex();

        // for (int i = 0; i < CindexList.size(); i++) {
        // System.out.println(
        // "index: " + CindexList.get(i).getIndex() + "Vacancy: " +
        // CindexList.get(i).getCurrentVacancy());
        // }

        Scanner sc = new Scanner(System.in);
        DatabaseManager databaseManager = new DatabaseManager();
        int choice = -1;
        Course singleCourse;
        Cindex singleIndex;
        Student stud;

        while (true) {
            System.out.println("Enter \'#\' to return to main menu "); // didnt initialize
            // choice. had error
            System.out.println("Please enter coursecode: ");
            String coursecode = sc.next();
            if (coursecode.equals("#"))
                return;

            singleCourse = databaseManager.searchCourse(coursecode);
            if (singleCourse != null) {
                System.out.println("Course description:");
                System.out.println(singleCourse.getCourseDescription());

                // print list of indexes and vacancies in the course
                // shud show timetable clash for each index
                // show index lesson timings

                System.out.printf("%s %s\n", singleCourse.getCourseCode(), singleCourse.getCourseName());
                System.out.println("-------------------------------------");
                System.out.println("index   /   vacacy   /    waitlist");
                for (int i = 0; i < singleCourse.getListCindex().size(); i++) {
                    Cindex singleindex = singleCourse.getListCindex().get(i);
                    System.out.printf("%d.  %s  /  %d  /  %d\n", i + 1, singleindex.getIndex(),
                            singleindex.getCurrentVacancy(), singleindex.getWaitList().size());
                }

            } else {
                System.out.println("course not found! please enter course code again ");
            }
        }

    }

    private static void changeIndex(String username) { // in switch case didnt obtain matricnumber so got error
        Scanner sc = new Scanner(System.in);
        StudentCourse studentCourse = null;
        int indexOfRegisteredCourse = 0;
        String input;

        System.out.println("Please enter coursecode that you want to change index: ");
        input = sc.next();

        DatabaseManager databaseManager = new DatabaseManager();
        Student studentobj = (Student) databaseManager.getObjectbyUsername(username);
        ArrayList<StudentCourse> registeredcourse = studentobj.getRegisteredCourse();

        for (int i = 0; i < registeredcourse.size(); i++) {
            if (registeredcourse.get(i).getCourseCode().equals(input)) {
                studentCourse = registeredcourse.get(i);
                indexOfRegisteredCourse = i;
            }
            break;
        }

        System.out.println("Your Current index: " + studentCourse.getIndex().getIndex());
        System.out.println("New index: ");
        input = sc.next();

        Cindex newindex = databaseManager.searchCindex(studentCourse.getCourseCode(), input);
        Cindex oldindex = studentCourse.getIndex();
        if (newindex.getCurrentVacancy() == 0) {

            newindex.getWaitList().add(studentobj);
            registeredcourse.remove(indexOfRegisteredCourse);
            System.out.println(
                    "index is removed from your registered course, you are placed in waitlist for your new index");
        } else {
            newindex.getRegisteredStudents().add(studentobj);
            oldindex.getRegisteredStudents().remove(studentobj);

            studentCourse.setIndex(newindex);
            System.out.println("you have changed your index successfully");
        }

    }

    private static void swapIndexWithAnotherStudent(String username) {
        Scanner sc = new Scanner(System.in);
        StudentCourse studentCourse = null;
        StudentCourse studentCoursePeer = null;

        System.out.println("Enter coursecode that you want to swap index: ");
        String courseCode = sc.next();

        System.out.println("Enter peer's username: ");
        String peerUsername = sc.next();

        System.out.println("Enter peer's password: ");
        String peerPassword = sc.next();

        Login login = new Login();

        if (login.verifyUser(peerUsername, peerPassword) == null) {
            System.out.println("peer's username and password incorrect");

            System.out.println("Enter peer's username: ");
            peerUsername = sc.next();

            System.out.println("Enter peer's password: ");
            peerPassword = sc.next();
        } else {
            System.out.println("peer's username and password verified");

            DatabaseManager databaseManager = new DatabaseManager();

            Student studentobj = (Student) databaseManager.getObjectbyUsername(username);
            ArrayList<StudentCourse> registeredcourseList = studentobj.getRegisteredCourse();

            int indexRegisterCourseList = 0;
            for (int i = 0; i < registeredcourseList.size(); i++) {
                if (registeredcourseList.get(i).getCourseCode().equals(courseCode)) {
                    studentCourse = registeredcourseList.get(i);
                    indexRegisterCourseList = i;
                    break;
                }
            }

            Student studentobjPeer = (Student) databaseManager.getObjectbyUsername(username);
            ArrayList<StudentCourse> registeredcoursePeerList = studentobjPeer.getRegisteredCourse();

            int indexRegisterCourseListpeer = 0;
            for (int i = 0; i < registeredcoursePeerList.size(); i++) {
                if (registeredcoursePeerList.get(i).getCourseCode().equals(courseCode)) {
                    studentCoursePeer = registeredcoursePeerList.get(i);
                    indexRegisterCourseListpeer = i;
                    break;
                }
            }

            System.out.println("your index: " + studentCourse.getIndex().getIndex());
            System.out.println("peer's index: " + studentCoursePeer.getIndex().getIndex());
            System.out.println("confirm swap?[y/n]");
            String confirm = sc.next();

            if (confirm.equals("y")) {
                Cindex newindex = databaseManager.searchCindex(studentCourse.getCourseCode(),
                studentCoursePeer.getIndex().getIndex());
                Cindex oldindex = databaseManager.searchCindex(studentCourse.getCourseCode(),
                studentCourse.getIndex().getIndex());

                newindex.getRegisteredStudents().add(studentobj);

                int index = oldindex.getIndexofStudent(username);
                oldindex.getRegisteredStudents().remove(index);

                int peerindex = newindex.getIndexofStudent(peerUsername);
                newindex.getRegisteredStudents().remove(peerindex);

                oldindex.getRegisteredStudents().add(studentobjPeer);

                studentCourse.setIndex(newindex);
                studentCoursePeer.setIndex(oldindex);

                registeredcourseList.set(indexRegisterCourseList,studentCourse );
                registeredcoursePeerList.set(indexRegisterCourseListpeer, studentCoursePeer);

                studentobj.setRegisteredCourse(registeredcourseList);
                studentobjPeer.setRegisteredCourse(registeredcoursePeerList);

                databaseManager.updateDatabase(studentobj);
                databaseManager.updateDatabase(studentobjPeer);

                Course courseObj = databaseManager.searchCourse(courseCode);
                ArrayList<Cindex> CourseCindexList = courseObj.getListCindex();
                
                int indexCindex = courseObj.getIndexOfCindex(newindex.getIndex());
                CourseCindexList.set(indexCindex, newindex);

                int indexCindexPeer = courseObj.getIndexOfCindex(oldindex.getIndex());
                CourseCindexList.set(indexCindexPeer, oldindex);

                courseObj.setListCindex(CourseCindexList);
                databaseManager.updateDatabase(courseObj);

                System.out.println("you have swapped index successfully");
            }
        }

    }

}