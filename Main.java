import com.sun.tools.jconsole.JConsoleContext;

import java.io.Console;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * Main class for running the program
 * @author kelvin Melvin
 * 
 */
public class Main {
    enum DAY_OF_WEEK {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    enum GENDER {
        MALE, FEMALE
    }

    enum ODD_EVEN {
        ODD, EVEN, OOD_AND_EVEN
    }

    enum LESSON_TYPE {
        LECTURE, TUTORIAL, LAB
    }

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
                password = String.valueOf(System.console().readPassword());
                
                userType = login.verifyUser(username, password);
            }

            switch (userType) {
                case "admin": {
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
                        System.out.println("==================================================");

                        boolean validchoice = false;
                        int choice = -1;
                        while (!validchoice) {
                            System.out.println("Enter choice: ");
                            try {
                                choice = sc.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Enter Integer only!");
                                sc.nextLine();
                                continue;
                            } catch (Exception e) {
                                System.out.println("invalid input! please try again!");
                                sc.nextLine();
                                continue;
                            }
                            validchoice = true;
                        }

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
                case "student": {
                    // verify access Time
                    if (verifyAccessTime(username)) {
                        while (runnning) {
                            System.out.println("==================================================");
                            System.out.println("Welcome Student!");
                            System.out.println("Select your option(1-6)");
                            System.out.println("1. Add Course");
                            System.out.println("2. Drop Course");
                            System.out.println("3. Check/Print Courses Registered");
                            System.out.println("4. Check Vacancies Available");
                            System.out.println("5. Change Index Number of Course");
                            System.out.println(
                                    "6. Swop Index Number with Another Student[refer to STARSPlanner STARSUserGuidev1_extracted.pdf for details of functions - ignore the Graphical display]");
                            System.out.println("7. Logout");
                            System.out.println("==================================================");

                            boolean validchoice = false;
                            int choice = -1;
                            while (!validchoice) {
                                System.out.println("Enter choice: ");
                                try {
                                    choice = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter Integer only!");
                                    sc.nextLine();
                                    continue;
                                } catch (Exception e) {
                                    System.out.println("invalid input! please try again!");
                                    sc.nextLine();
                                    continue;
                                }
                                validchoice = true;
                            }

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
                        int month = cal.get(Calendar.MONTH); // NOTE!!! : Month from 0 to 11
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        int minute = cal.get(Calendar.MINUTE);
                        int second = cal.get(Calendar.SECOND);

                        DatabaseManager databaseManager = new DatabaseManager();
                        Student studentObj = (Student) databaseManager.getObjectbyUsername(username);

                        System.out.printf("Now is %4d/%02d/%02d %02d:%02d:%02d, Login at your access Time period! \n",
                                year, month + 1, day, hour, minute, second);
                        // System.out.printf("Your access Time period: %4d/%02d/%02d %02d:%02d:%02d to
                        // %4d/%02d/%02d %02d:%02d:%02d", );
                        long accessStartTimems = studentObj.getAccessStartTime();
                        long accessEndTimems = studentObj.getAccessEndTime();

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(accessStartTimems);
                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.setTimeInMillis(accessEndTimems);
                        System.out.printf("Your access Time period: %d-%d-%d %d:%d to %d-%d-%d %d:%d \n",
                                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1,
                                calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE), calendar2.get(Calendar.DAY_OF_MONTH),
                                calendar2.get(Calendar.MONTH) + 1, calendar2.get(Calendar.YEAR),
                                calendar2.get(Calendar.HOUR_OF_DAY), calendar2.get(Calendar.MINUTE));
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
            System.out.println("------------------------------------------");
            System.out.println("Course Code: " + courseObj.getCourseCode());
            System.out.println("Course Name: " + courseObj.getCourseName());
            System.out.println("school: " + courseObj.getSchool());
            System.out.println("Course Description: " + courseObj.getCourseDescription());
            System.out.println("Number of AUs: " + courseObj.getAU());

            ArrayList<Cindex> CindexList = courseObj.getListCindex();
            for (int i = 0; i < CindexList.size(); i++) {
                System.out.println(
                        "Index " + CindexList.get(i).getIndexName() + " Capacity: " + CindexList.get(i).getCapacity());
            }
            System.out.println("------------------------------------------");
            int choice = -1;
            ArrayList<Cindex> cindexList = courseObj.getListCindex();
            while (choice != 0) {
                System.out.println("===========================================");
                System.out.println("update options:");
                System.out.println("0. exit");
                System.out.println("1. Update Course Code");
                System.out.println("2. Update School");
                System.out.println("3. Update Index Capacity");
                System.out.println("4. Update Index Number");
                System.out.println("5. Add new Index");
                System.out.println("6. Delete Index");
                System.out.println("===========================================");

                boolean validchoice = false;
                while (!validchoice) {
                    System.out.println("Enter choice: ");
                    try {
                        choice = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Enter Integers Only!");
                        sc.nextLine();
                        continue;
                    } catch (Exception e) {
                        System.out.println("Exception Error");
                        sc.nextLine();
                        continue;
                    }
                    validchoice = true;
                }

                switch (choice) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        System.out.println("Enter new Course Code:");
                        String newCourseCode = sc.next();

                        ArrayList<Course> courselist = databaseManager.DeserializeCourseList();

                        if (databaseManager.verifyUniqueCourseCode(newCourseCode)) {
                            for (int i = 0; i < courselist.size(); i++) {
                                if (courselist.get(i).getCourseCode().equals(courseObj.getCourseCode())) {
                                    courselist.remove(i);
                                    break;
                                }
                            }

                            databaseManager.updateStudentCourseNewCourseCode(courseObj.getCourseCode(), newCourseCode);
                            courseObj.setCourseCode(newCourseCode);

                            courselist.add(courseObj);


                            databaseManager.SerializeCourseList(courselist);
                            courseCode = newCourseCode;

                        } else {
                            System.out.println("Course Code not unique");
                        }

                        choice = -1;
                        break;
                    }
                    case 2: {
                        System.out.println("Enter new School:");
                        String newSchool = sc.next();

                        courseObj.setSchool(newSchool);
                        databaseManager.updateDatabase(courseObj);
                        choice = -1;
                        break;
                    }
                    case 3: {

                        System.out.println("Enter the index to change Capacity:");
                        String index = sc.next();

                        Cindex cindexobj = databaseManager.searchCindex(courseCode, index);

                        if (cindexobj == null) {
                            System.out.println("Index does not exist!");
                            System.out.println();
                            break;
                        } else {

                            boolean validCapacity = false;
                            int newCapacity = -1;
                            while (!validCapacity) {
                                System.out.println("Enter new Capacity:");
                                try {
                                    newCapacity = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter Integers Only!");
                                    sc.nextLine();
                                    continue;
                                } catch (Exception e) {
                                    System.out.println("Exception Error");
                                    sc.nextLine();
                                    continue;
                                }
                                validCapacity = true;
                            }

                            for (int i = 0; i < cindexList.size(); i++) {
                                if (cindexList.get(i).getIndexName().equals(index)) {
                                    cindexList.remove(i);
                                    break;
                                }
                            }

                            cindexobj.setCapacity(newCapacity);
                            cindexList.add(cindexobj);

                            courseObj.setListCindex(cindexList);
                            databaseManager.updateDatabase(courseObj);
                        }
                        choice = -1;
                        break;
                    }
                    case 4: {

                        System.out.println("Enter the Index to change new Index Number:");
                        String index = sc.next();

                        Cindex cindexobj = databaseManager.searchCindex(courseCode, index);

                        if (cindexobj == null) {
                            System.out.println("Index does not exist!");
                            System.out.println();
                            break;
                        } else {

                            boolean uniqueIndex = false;
                            String newIndex = "";
                            while (!uniqueIndex) {
                                System.out.println("Enter new Index:");
                                newIndex = sc.next();

                                boolean found = false;
                                for (int i = 0; i < courseObj.getListCindex().size(); i++) {
                                    if (courseObj.getListCindex().get(i).getIndexName().equals(newIndex)) {
                                        System.out.println("index not unique! ");
                                        found = true;
                                        break;
                                    } 
                                }

                                if(found == false){
                                    uniqueIndex = true;
                                }
                            }

                            for (int i = 0; i < cindexList.size(); i++) {
                                if (cindexList.get(i).getIndexName().equals(index)) {
                                    cindexList.remove(i);
                                    break;
                                }
                            }
                            databaseManager.updateStudentCourseNewIndexName(courseCode, cindexobj.getIndexName(),
                                    newIndex);

                            cindexobj.setIndex(newIndex);
                            cindexList.add(cindexobj);

                            courseObj.setListCindex(cindexList);
                            databaseManager.updateDatabase(courseObj);
                        }
                        choice = -1;
                        break;
                    }
                    case 5: {

                        boolean uniqueIndex = false;
                        String index = "";
                        while (!uniqueIndex) {
                            System.out.println("New Index: ");
                            index = sc.next();
                            if (databaseManager.searchCindex(courseCode, index) == null) {
                                uniqueIndex = true;
                            } else {
                                System.out.println("Index not unique!");
                            }
                        }

                        int capacity = 0;
                        boolean validCapacity = false;
                        while (!validCapacity) {
                            System.out.println("Capacity: ");
                            try {
                                capacity = sc.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Enter Integers Only!");
                                sc.nextLine();
                                continue;
                            } catch (Exception e) {
                                System.out.println("Exception Error");
                                sc.nextLine();
                                continue;
                            }
                            validCapacity = true;
                        }

                        Cindex CindexObj = new Cindex(index, capacity);

                        ArrayList<Lesson> schedule = new ArrayList<Lesson>();
                        DateFormat timeformat = new SimpleDateFormat("HHmm");
                        int choice2 = -1;
                        while (choice2 != 0) {
                            System.out.println("Add new lesson: ");
                            System.out.println("0.Stop adding lesson ");
                            System.out.println("1.Add new Lecture ");
                            System.out.println("2.Add new Tutorial ");
                            System.out.println("3.Add new Lab ");

                            boolean validchoice2 = false;
                            while (!validchoice2) {
                                System.out.println("Enter choice: ");
                                try {
                                    choice2 = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter Integers Only!");
                                    sc.nextLine();
                                    continue;
                                } catch (Exception e) {
                                    System.out.println("Exception Error");
                                    sc.nextLine();
                                    continue;
                                }
                                validchoice2 = true;
                            }

                            switch (choice2) {
                                case 0: {
                                    break;
                                }
                                case 1: {
                                    Date startTimeParsedLect = null;
                                    boolean validStartTime = false;
                                    while (!validStartTime) {
                                        System.out.println("Start Time: ");
                                        String startTimeLect = sc.next();
                                        try {
                                            if (isValidTime(startTimeLect)) {
                                                startTimeParsedLect = timeformat.parse(startTimeLect);
                                            } else {
                                                System.out.println("Enter time in 24hr Format!");
                                                continue;
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Enter Integer Only!");
                                            continue;
                                        }
                                        validStartTime = true;
                                    }

                                    Date endTimeParsedLect = null;
                                    boolean endtime_After_starttime = false;
                                    while (!endtime_After_starttime) {

                                        boolean validEndTime = false;
                                        while (!validEndTime) {
                                            System.out.println("End Time: ");
                                            String endTimeLect = sc.next();
                                            try {
                                                if (isValidTime(endTimeLect)) {
                                                    endTimeParsedLect = timeformat.parse(endTimeLect);
                                                } else {
                                                    System.out.println("Enter time in 24hr Format!");
                                                    continue;
                                                }
                                            } catch (ParseException e) {
                                                System.out.println("Enter Integer Only!");
                                                continue;
                                            }
                                            validEndTime = true;
                                        }

                                        if (endTimeParsedLect.after(startTimeParsedLect)) {
                                            endtime_After_starttime = true;
                                        } else {
                                            System.out.println("End time must be later than Start Time!");
                                        }

                                    }

                                    System.out.println("Venue: ");
                                    String venueLect = sc.next();

                                    boolean validDayOfWeek = false;
                                    String input = "";
                                    Main.DAY_OF_WEEK dayOfweekLect = null;
                                    while (!validDayOfWeek) {
                                        System.out.println("Day Of Week: ");
                                        input = sc.next();

                                        switch (input.toLowerCase()) {
                                            case "monday": {
                                                dayOfweekLect = DAY_OF_WEEK.MONDAY;
                                                break;
                                            }
                                            case "tuesday": {
                                                dayOfweekLect = DAY_OF_WEEK.TUESDAY;
                                                break;
                                            }
                                            case "wednesday": {
                                                dayOfweekLect = DAY_OF_WEEK.WEDNESDAY;
                                                break;
                                            }
                                            case "thursday": {
                                                dayOfweekLect = DAY_OF_WEEK.THURSDAY;
                                                break;
                                            }
                                            case "friday": {
                                                dayOfweekLect = DAY_OF_WEEK.FRIDAY;
                                                break;
                                            }
                                            case "saturday": {
                                                dayOfweekLect = DAY_OF_WEEK.SATURDAY;
                                                break;
                                            }
                                            case "sunday": {
                                                dayOfweekLect = DAY_OF_WEEK.SUNDAY;
                                                break;
                                            }
                                            default: {
                                                System.out.println(
                                                        "Enter day of the Week ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') only!");
                                                continue;
                                            }
                                        }
                                        validDayOfWeek = true;
                                    }

                                    Lecture lecture = new Lecture(startTimeParsedLect, endTimeParsedLect, venueLect,
                                            dayOfweekLect);

                                    schedule.add(lecture);
                                    break;
                                }
                                case 2: {

                                    Date startTimeParsedTut = null;
                                    Boolean ValidStartTime2 = false;
                                    while (!ValidStartTime2) {
                                        System.out.println("Start Time: ");
                                        String startTimeTut = sc.next();
                                        try {
                                            if (isValidTime(startTimeTut)) {
                                                startTimeParsedTut = timeformat.parse(startTimeTut);
                                            } else {
                                                System.out.println("Enter time in 24hr Format!");
                                                continue;
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Enter Integer Only!");
                                            continue;
                                        }
                                        ValidStartTime2 = true;
                                    }

                                    Date endTimeParsedTut = null;
                                    boolean endtime_After_starttime = false;
                                    while (!endtime_After_starttime) {

                                        Boolean validEndTime2 = false;
                                        while (!validEndTime2) {
                                            System.out.println("End Time: ");
                                            String endTimeTut = sc.next();
                                            try {
                                                if (isValidTime(endTimeTut))
                                                    endTimeParsedTut = timeformat.parse(endTimeTut);
                                                else {
                                                    System.out.println("Enter time in 24hr Format!");
                                                    continue;
                                                }
                                            } catch (ParseException e) {
                                                System.out.println("Enter Integer Only!");
                                                continue;
                                            }
                                            validEndTime2 = true;
                                        }

                                        if (endTimeParsedTut.after(startTimeParsedTut)) {
                                            endtime_After_starttime = true;
                                        } else {
                                            System.out.println("End time must be later than Start Time!");
                                        }

                                    }

                                    System.out.println("Venue: ");
                                    String venueTut = sc.next();

                                    boolean validDayOfWeek2 = false;
                                    Main.DAY_OF_WEEK dayOfweekTut = null;
                                    while (!validDayOfWeek2) {
                                        System.out.println("Day Of Week: ");
                                        String input = sc.next();

                                        switch (input.toLowerCase()) {
                                            case "monday": {
                                                dayOfweekTut = DAY_OF_WEEK.MONDAY;
                                                break;
                                            }
                                            case "tuesday": {
                                                dayOfweekTut = DAY_OF_WEEK.TUESDAY;
                                                break;
                                            }
                                            case "wednesday": {
                                                dayOfweekTut = DAY_OF_WEEK.WEDNESDAY;
                                                break;
                                            }
                                            case "thursday": {
                                                dayOfweekTut = DAY_OF_WEEK.THURSDAY;
                                                break;
                                            }
                                            case "friday": {
                                                dayOfweekTut = DAY_OF_WEEK.FRIDAY;
                                                break;
                                            }
                                            case "saturday": {
                                                dayOfweekTut = DAY_OF_WEEK.SATURDAY;
                                                break;
                                            }
                                            case "sunday": {
                                                dayOfweekTut = DAY_OF_WEEK.SUNDAY;
                                                break;
                                            }
                                            default: {
                                                System.out.println(
                                                        "Enter day of the Week ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') only!");
                                                continue;
                                            }
                                        }
                                        validDayOfWeek2 = true;
                                    }

                                    Tutorial tutorial = new Tutorial(startTimeParsedTut, endTimeParsedTut, venueTut,
                                            dayOfweekTut);

                                    schedule.add(tutorial);
                                    break;
                                }
                                case 3: {

                                    Date startTimeParsedLab = null;
                                    boolean validStartTime3 = false;
                                    while (!validStartTime3) {
                                        System.out.println("Start Time: ");
                                        String startTimeLab = sc.next();
                                        try {
                                            if (isValidTime(startTimeLab)) {
                                                startTimeParsedLab = timeformat.parse(startTimeLab);
                                            } else {
                                                System.out.println("Enter time in 24hr Format!");
                                                continue;
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Enter Integer Only!");
                                            continue;
                                        }
                                        validStartTime3 = true;
                                    }

                                    Date endTimeParsedLab = null;
                                    boolean endtime_After_starttime = false;
                                    while (!endtime_After_starttime) {

                                        boolean validEndTime3 = false;
                                        while (!validEndTime3) {
                                            System.out.println("End Time: ");
                                            String endTimeLab = sc.next();
                                            try {
                                                if (isValidTime(endTimeLab)) {
                                                    endTimeParsedLab = timeformat.parse(endTimeLab);
                                                } else {
                                                    System.out.println("Enter time in 24hr Format!");
                                                    continue;
                                                }
                                            } catch (ParseException e) {
                                                System.out.println("Enter Integer Only!");
                                                continue;
                                            }
                                            validEndTime3 = true;
                                        }

                                        if (endTimeParsedLab.after(startTimeParsedLab)) {
                                            endtime_After_starttime = true;
                                        } else {
                                            System.out.println("End time must be later than Start Time!");
                                        }

                                    }

                                    System.out.println("Venue: ");
                                    String venueLab = sc.next();

                                    boolean validDayOfWeek3 = false;
                                    String input = "";
                                    Main.DAY_OF_WEEK dayOfweekLab = null;
                                    while (!validDayOfWeek3) {
                                        System.out.println("Day Of Week: ");
                                        input = sc.next();
                                        switch (input.toLowerCase()) {
                                            case "monday": {
                                                dayOfweekLab = DAY_OF_WEEK.MONDAY;
                                                break;
                                            }
                                            case "tuesday": {
                                                dayOfweekLab = DAY_OF_WEEK.TUESDAY;
                                                break;
                                            }
                                            case "wednesday": {
                                                dayOfweekLab = DAY_OF_WEEK.WEDNESDAY;
                                                break;
                                            }
                                            case "thursday": {
                                                dayOfweekLab = DAY_OF_WEEK.THURSDAY;
                                                break;
                                            }
                                            case "friday": {
                                                dayOfweekLab = DAY_OF_WEEK.FRIDAY;
                                                break;
                                            }
                                            case "saturday": {
                                                dayOfweekLab = DAY_OF_WEEK.SATURDAY;
                                                break;
                                            }
                                            case "sunday": {
                                                dayOfweekLab = DAY_OF_WEEK.SUNDAY;
                                                break;
                                            }
                                            default: {
                                                System.out.println(
                                                        "Enter day of the Week ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') only!");
                                                continue;
                                            }
                                        }
                                        validDayOfWeek3 = true;
                                    }

                                    boolean validOddOrEven = false;
                                    Main.ODD_EVEN oddOrEvenLab = null;
                                    input = "";
                                    while (!validOddOrEven) {
                                        System.out.println("Odd or Even: ");
                                        input = sc.next();

                                        switch (input.toLowerCase()) {
                                            case "odd": {
                                                oddOrEvenLab = ODD_EVEN.ODD;
                                                break;
                                            }
                                            case "even": {
                                                oddOrEvenLab = ODD_EVEN.EVEN;
                                                break;
                                            }
                                            case "both": {
                                                oddOrEvenLab = ODD_EVEN.OOD_AND_EVEN;
                                                break;
                                            }
                                            default: {
                                                System.out.println("Enter 'ODD' or 'EVEN' only!");
                                                continue;
                                            }
                                        }
                                        validOddOrEven = true;
                                    }

                                    Labs lab = new Labs(startTimeParsedLab, endTimeParsedLab, venueLab, dayOfweekLab,
                                            oddOrEvenLab);

                                    schedule.add(lab);
                                    break;
                                }
                                default: {
                                    System.out.println("Choice from 0 to 3 only!");
                                }
                            }
                        }
                        CindexObj.setSchedule(schedule);
                        databaseManager.addcindex(courseCode, CindexObj);

                        courseObj = databaseManager.searchCourse(courseCode);

                        choice = -1;
                        break;
                    }
                    case 6: {
                        System.out.println("Enter the Index that you want to delete:");
                        String index = sc.next();

                        if (databaseManager.searchCindex(courseCode, index) != null) {
                            for (int i = 0; i < cindexList.size(); i++) {
                                if (cindexList.get(i).getIndexName().equals(index)) {
                                    cindexList.remove(i);
                                    break;
                                }
                            }

                            courseObj.setListCindex(cindexList);
                            databaseManager.updateDatabase(courseObj);
                        } else {
                            System.out.println("Index does not exists in selected course");
                        }

                        choice = -1;
                        break;
                    }
                    default: {
                        System.out.println("Choice from 0 to 6 Only!");
                    }
                }
            }
        } else {
            System.out.println("Course Code does not exists in DataBase");
        }
    }

    private static void adminAddCourse() {
        ArrayList<Cindex> CindexList = new ArrayList<Cindex>();

        DateFormat timeformat = new SimpleDateFormat("HHmm");
        Scanner sc = new Scanner(System.in);

        System.out.println("Add new Course ");
        System.out.println("Course Code: "); // check for duplicates
        String CourseCode = sc.next().toUpperCase();
        sc.nextLine();

        DatabaseManager databaseManager = new DatabaseManager();
        Course checkCourse = databaseManager.searchCourse(CourseCode);

        if (checkCourse != null) {
            System.out.println("Course already exists!");
            return;
        } else {
            System.out.println("Course Name: ");
            String CourseName = sc.nextLine();

            System.out.println("Course Description: ");
            String CourseDescription = sc.nextLine();

            System.out.println("Course School: ");
            String school = sc.nextLine();

            boolean validAU = false;
            int AU = -1;
            while (!validAU) {
                System.out.println("Course AUs: ");
                try {
                    AU = sc.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("Enter Integers Only!");
                    sc.nextLine();
                    continue;
                } catch (Exception e) {
                    System.out.println("Exception Error");
                    sc.nextLine();
                    continue;
                }
                if (AU <= 0) {
                    System.out.println("AUs cannot be 0 or Negative!");
                    sc.nextLine();
                    continue;
                }
                validAU = true;
            }

            Course courseObj = new Course(CourseCode, CourseName, CourseDescription, school, AU, CindexList);

            int choice = -1;

            ArrayList<Cindex> cindexArrayList = new ArrayList<>();

            while (choice != 0) {
                System.out.println("--------------------------------------");
                System.out.println("Add new index: ");
                System.out.println("0.Quit");
                System.out.println("1.Add new index");

                boolean validchoice = false;
                while (!validchoice) {
                    System.out.println("Enter choice: ");
                    try {
                        choice = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Enter Integers Only!");
                        sc.nextLine();
                        continue;
                    } catch (Exception e) {
                        System.out.println("Exception Error");
                        sc.nextLine();
                        continue;
                    }
                    validchoice = true;
                }

                System.out.println("--------------------------------------");
                switch (choice) {
                    case 0:
                        break;
                    case 1:

                        boolean uniqueIndex = false;
                        String index = "";
                        while (!uniqueIndex) {
                            System.out.println("Index: ");
                            index = sc.next();

                            if (cindexArrayList.size() != 0) {
                                boolean found=false;
                                for (int i = 0; i < cindexArrayList.size(); i++) {
                                    if (cindexArrayList.get(i).getIndexName().equals(index)) {
                                        System.out.println("index not unique! ");
                                        found=true;
                                        break;
                                    }
                                }

                                if(found==false){
                                    uniqueIndex=true;
                                }
                            } else {
                                uniqueIndex = true;
                            }
                        }

                        int capacity = 0;
                        boolean validCapacity = false;
                        while (!validCapacity) {
                            System.out.println("Capacity: ");
                            try {
                                capacity = sc.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Enter Integers Only!");
                                sc.nextLine();
                                continue;
                            } catch (Exception e) {
                                System.out.println("Exception Error");
                                sc.nextLine();
                                continue;
                            }
                            validCapacity = true;
                        }

                        Cindex CindexObj = new Cindex(index, capacity);

                        ArrayList<Lesson> schedule = new ArrayList<Lesson>();
                        int choice2 = -1;
                        while (choice2 != 0) {
                            System.out.println("Add new lesson: ");
                            System.out.println("0.Stop adding lesson ");
                            System.out.println("1.Add new Lecture ");
                            System.out.println("2.Add new Tutorial ");
                            System.out.println("3.Add new Lab ");

                            boolean validchoice2 = false;
                            while (!validchoice2) {
                                System.out.println("Enter choice: ");
                                try {
                                    choice2 = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter Integers Only!");
                                    sc.nextLine();
                                    continue;
                                } catch (Exception e) {
                                    System.out.println("Exception Error");
                                    sc.nextLine();
                                    continue;
                                }
                                validchoice2 = true;
                            }

                            switch (choice2) {
                                case 0:
                                    if (ScheduleContainLect(schedule)) {
                                        System.out.println(
                                                "A Index MUST contain either Lect only, Lect and Tut only ,Lect and Tut and lab only!");
                                        continue;
                                    }
                                    break;
                                case 1:
                                    Date startTimeParsedLect = null;
                                    boolean validStartTime = false;
                                    while (!validStartTime) {
                                        System.out.println("Start Time: ");
                                        String startTimeLect = sc.next();
                                        try {
                                            if (isValidTime(startTimeLect)) {
                                                startTimeParsedLect = timeformat.parse(startTimeLect);
                                            } else {
                                                System.out.println("Enter time in 24hr Format!");
                                                continue;
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Enter Integer Only!");
                                            continue;
                                        }
                                        validStartTime = true;
                                    }

                                    Date endTimeParsedLect = null;
                                    boolean endtime_After_starttime = false;
                                    while (!endtime_After_starttime) {

                                        boolean validEndTime = false;
                                        while (!validEndTime) {
                                            System.out.println("End Time: ");
                                            String endTimeLect = sc.next();
                                            try {
                                                if (isValidTime(endTimeLect)) {
                                                    endTimeParsedLect = timeformat.parse(endTimeLect);
                                                } else {
                                                    System.out.println("Enter time in 24hr Format!");
                                                    continue;
                                                }
                                            } catch (ParseException e) {
                                                System.out.println("Enter Integer Only!");
                                                continue;
                                            }
                                            validEndTime = true;
                                        }

                                        if (endTimeParsedLect.after(startTimeParsedLect)) {
                                            endtime_After_starttime = true;
                                        } else {
                                            System.out.println("End time must be later than Start Time!");
                                        }
                                    }

                                    System.out.println("Venue: ");
                                    String venueLect = sc.next();
                                    String input = "";
                                    boolean validDayOfWeek = false;
                                    Main.DAY_OF_WEEK dayOfweekLect = null;
                                    while (!validDayOfWeek) {
                                        System.out.println("Day Of Week: ");
                                        input = sc.next();

                                        switch (input.toLowerCase()) {
                                            case "monday": {
                                                dayOfweekLect = DAY_OF_WEEK.MONDAY;
                                                break;
                                            }
                                            case "tuesday": {
                                                dayOfweekLect = DAY_OF_WEEK.TUESDAY;
                                                break;
                                            }
                                            case "wednesday": {
                                                dayOfweekLect = DAY_OF_WEEK.WEDNESDAY;
                                                break;
                                            }
                                            case "thursday": {
                                                dayOfweekLect = DAY_OF_WEEK.THURSDAY;
                                                break;
                                            }
                                            case "friday": {
                                                dayOfweekLect = DAY_OF_WEEK.FRIDAY;
                                                break;
                                            }
                                            case "saturday": {
                                                dayOfweekLect = DAY_OF_WEEK.SATURDAY;
                                                break;
                                            }
                                            case "sunday": {
                                                dayOfweekLect = DAY_OF_WEEK.SUNDAY;
                                                break;
                                            }
                                            default: {
                                                System.out.println(
                                                        "Enter day of the Week ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') only!");
                                                continue;
                                            }
                                        }
                                        validDayOfWeek = true;
                                    }

                                    Lecture lecture = new Lecture(startTimeParsedLect, endTimeParsedLect, venueLect,
                                            dayOfweekLect);

                                    schedule.add(lecture);
                                    break;
                                case 2:

                                    Date startTimeParsedTut = null;
                                    Boolean ValidStartTime2 = false;
                                    while (!ValidStartTime2) {
                                        System.out.println("Start Time: ");
                                        String startTimeTut = sc.next();
                                        try {
                                            if (isValidTime(startTimeTut)) {
                                                startTimeParsedTut = timeformat.parse(startTimeTut);
                                            } else {
                                                System.out.println("Enter time in 24hr Format!");
                                                continue;
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Enter Integer Only!");
                                            continue;
                                        }
                                        ValidStartTime2 = true;
                                    }

                                    Date endTimeParsedTut = null;
                                    boolean endtime_After_starttime2 = false;
                                    while (!endtime_After_starttime2) {

                                        Boolean validEndTime2 = false;
                                        while (!validEndTime2) {
                                            System.out.println("End Time: ");
                                            String endTimeTut = sc.next();
                                            try {
                                                if (isValidTime(endTimeTut))
                                                    endTimeParsedTut = timeformat.parse(endTimeTut);
                                                else {
                                                    System.out.println("Enter time in 24hr Format!");
                                                    continue;
                                                }
                                            } catch (ParseException e) {
                                                System.out.println("Enter Integer Only!");
                                                continue;
                                            }
                                            validEndTime2 = true;
                                        }

                                        if (endTimeParsedTut.after(startTimeParsedTut)) {
                                            endtime_After_starttime2 = true;
                                        } else {
                                            System.out.println("End time must be later than Start Time!");
                                        }
                                    }

                                    System.out.println("Venue: ");
                                    String venueTut = sc.next();

                                    boolean validDayOfWeek2 = false;

                                    Main.DAY_OF_WEEK day_of_weekTut = null;
                                    while (!validDayOfWeek2) {
                                        System.out.println("Day Of Week: ");
                                        input = sc.next();

                                        switch (input.toLowerCase()) {
                                            case "monday": {
                                                day_of_weekTut = DAY_OF_WEEK.MONDAY;
                                                break;
                                            }
                                            case "tuesday": {
                                                day_of_weekTut = DAY_OF_WEEK.TUESDAY;
                                                break;
                                            }
                                            case "wednesday": {
                                                day_of_weekTut = DAY_OF_WEEK.WEDNESDAY;
                                                break;
                                            }
                                            case "thursday": {
                                                day_of_weekTut = DAY_OF_WEEK.THURSDAY;
                                                break;
                                            }
                                            case "friday": {
                                                day_of_weekTut = DAY_OF_WEEK.FRIDAY;
                                                break;
                                            }
                                            case "saturday": {
                                                day_of_weekTut = DAY_OF_WEEK.SATURDAY;
                                                break;
                                            }
                                            case "sunday": {
                                                day_of_weekTut = DAY_OF_WEEK.SUNDAY;
                                                break;
                                            }
                                            default: {
                                                System.out.println(
                                                        "Enter day of the Week ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') only!");
                                                continue;
                                            }
                                        }
                                        validDayOfWeek2 = true;
                                    }

                                    Tutorial tutorial = new Tutorial(startTimeParsedTut, endTimeParsedTut, venueTut,
                                            day_of_weekTut);

                                    schedule.add(tutorial);
                                    break;
                                case 3:

                                    Date startTimeParsedLab = null;
                                    boolean validStartTime3 = false;
                                    while (!validStartTime3) {
                                        System.out.println("Start Time: ");
                                        String startTimeLab = sc.next();
                                        try {
                                            if (isValidTime(startTimeLab)) {
                                                startTimeParsedLab = timeformat.parse(startTimeLab);
                                            } else {
                                                System.out.println("Enter time in 24hr Format!");
                                                continue;
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Enter Integer Only!");
                                            continue;
                                        }
                                        validStartTime3 = true;
                                    }

                                    Date endTimeParsedLab = null;
                                    boolean endtime_After_starttime3 = false;
                                    while (!endtime_After_starttime3) {

                                        boolean validEndTime3 = false;
                                        while (!validEndTime3) {
                                            System.out.println("End Time: ");
                                            String endTimeLab = sc.next();
                                            try {
                                                if (isValidTime(endTimeLab)) {
                                                    endTimeParsedLab = timeformat.parse(endTimeLab);
                                                } else {
                                                    System.out.println("Enter time in 24hr Format!");
                                                    continue;
                                                }
                                            } catch (ParseException e) {
                                                System.out.println("Enter Integer Only!");
                                                continue;
                                            }
                                            validEndTime3 = true;
                                        }

                                        if (endTimeParsedLab.after(startTimeParsedLab)) {
                                            endtime_After_starttime3 = true;
                                        } else {
                                            System.out.println("End time must be later than Start Time!");
                                        }
                                    }

                                    System.out.println("Venue: ");
                                    String venueLab = sc.next();

                                    boolean validDayOfWeek3 = false;
                                    input = "";
                                    Main.DAY_OF_WEEK dayOfweekLab = null;
                                    while (!validDayOfWeek3) {
                                        System.out.println("Day Of Week: ");
                                        input = sc.next();

                                        switch (input.toLowerCase()) {
                                            case "monday": {
                                                dayOfweekLab = DAY_OF_WEEK.MONDAY;
                                                break;
                                            }
                                            case "tuesday": {
                                                dayOfweekLab = DAY_OF_WEEK.TUESDAY;
                                                break;
                                            }
                                            case "wednesday": {
                                                dayOfweekLab = DAY_OF_WEEK.WEDNESDAY;
                                                break;
                                            }
                                            case "thursday": {
                                                dayOfweekLab = DAY_OF_WEEK.THURSDAY;
                                                break;
                                            }
                                            case "friday": {
                                                dayOfweekLab = DAY_OF_WEEK.FRIDAY;
                                                break;
                                            }
                                            case "saturday": {
                                                dayOfweekLab = DAY_OF_WEEK.SATURDAY;
                                                break;
                                            }
                                            case "sunday": {
                                                dayOfweekLab = DAY_OF_WEEK.SUNDAY;
                                                break;
                                            }
                                            default: {
                                                System.out.println(
                                                        "Enter day of the Week ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') only!");
                                                continue;
                                            }
                                        }
                                        validDayOfWeek3 = true;
                                    }

                                    boolean validOddOrEven = false;
                                    Main.ODD_EVEN oddOrEvenLab = null;
                                    while (!validOddOrEven) {
                                        System.out.println("odd or even or both: ");
                                        input = sc.next();

                                        switch (input.toLowerCase()) {
                                            case "odd": {
                                                oddOrEvenLab = ODD_EVEN.ODD;
                                                break;
                                            }
                                            case "even": {
                                                oddOrEvenLab = ODD_EVEN.EVEN;
                                                break;
                                            }
                                            case "both": {
                                                oddOrEvenLab = ODD_EVEN.OOD_AND_EVEN;
                                                break;
                                            }
                                            default: {
                                                System.out.println("Enter 'ODD' or 'EVEN' only!");
                                                continue;
                                            }
                                        }
                                        validOddOrEven = true;
                                    }

                                    Labs lab = new Labs(startTimeParsedLab, endTimeParsedLab, venueLab, dayOfweekLab,
                                            oddOrEvenLab);

                                    schedule.add(lab);
                                    break;

                            }
                        }

                        if (checkClashBetweenLessons(schedule)) {
                            System.out.println("The new added will not be added as it clash with previous index");
                            continue;
                        }

                        CindexObj.setSchedule(schedule);
                        cindexArrayList.add(CindexObj);
                }

            }

            courseObj.setListCindex(cindexArrayList);
            // DatabaseManager databaseManager = new DatabaseManager();
            ArrayList<Course> courseList = databaseManager.DeserializeCourseList();
            courseList.add(courseObj);
            databaseManager.SerializeCourseList(courseList);
            System.out.println("Course added");
        }
        System.out.println();
        databaseManager.printAllCourses();

    }

    private static void printStudentListByCIndex() {
        String coursecode;
        Course singleCourse;
        ArrayList<String> studentList;
        DatabaseManager databaseManager = new DatabaseManager();
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        while (running) {
            System.out.println("Enter \' # \'to return to main menu ");
            System.out.println("Please enter coursecode: ");
            coursecode = sc.next();
            if (coursecode.equals("#"))
                return;

            singleCourse = databaseManager.searchCourse(coursecode);
            if (singleCourse != null) {

                System.out.printf("%s %s \n", singleCourse.getCourseCode(), singleCourse.getCourseName());
                System.out.println("Description: " + singleCourse.getCourseDescription());

                // print list of indexes and vacancies in the course
                // shud show timetable clash for each index
                // show index lesson timings
                if (singleCourse.getListCindex().size() != 0) {
                    System.out.println("-------------------------------------");
                    System.out.println("index  /capacity /   vacacy   /    waitlist");
                    for (int i = 0; i < singleCourse.getListCindex().size(); i++) {
                        Cindex singleindex = singleCourse.getListCindex().get(i);
                        System.out.printf("%d.  %s  /  %d  /  %d  /  %d\n", i + 1, singleindex.getIndexName(),
                                singleindex.getCapacity(), singleindex.getCurrentVacancy(),
                                singleindex.getWaitList().size());
                    }
                } else {
                    System.out.println("No Cindex Available!");
                    System.out.println();
                }

            } else {
                System.out.println("course not found! please enter course code again ");
                System.out.println();
                continue;
            }

            System.out.println("Please enter choice: ");
            System.out.println("Enter \'#\'to go back to main menu");
            String choiceIndex = sc.next();

            if (choiceIndex.equals("#"))
                return;

            else if (Integer.parseInt(choiceIndex) - 1 >= 0
                    && Integer.parseInt(choiceIndex) - 1 < singleCourse.getListCindex().size()) {
                Cindex singleIndex = singleCourse.getListCindex().get(Integer.parseInt(choiceIndex) - 1);

                // print all the student in the index
                studentList = singleIndex.getRegisteredStudents();

                if (studentList.size() != 0) {
                    System.out.printf("student in %s %s\n", coursecode, singleIndex.getIndexName());

                    for (int i = 0; i < studentList.size(); i++) {
                        Student singleStudent = (Student) databaseManager.getObjectbyUsername(studentList.get(i));
                        System.out.printf("%d. %s %s, %s, %s \n", i + 1, singleStudent.getFirstName(),
                                singleStudent.getLastName(), singleStudent.getGender(), singleStudent.getNationality());
                    }

                } else if (studentList.size() == 0) {
                    System.out.printf("There are no registered students in %s %s\n", coursecode,
                            singleIndex.getIndexName());
                }
                System.out.println();
            } else {
                System.out.println("invalid choice!");
                System.out.println();
                continue;
            }

        }

    }

    private static void printStudentListByCourse() {
        String coursecode;
        ArrayList<String> studentList;
        DatabaseManager databaseManager = new DatabaseManager();

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter coursecode: ");
        coursecode = sc.next();

        studentList = databaseManager.getStudentListbyCourse(coursecode);
        if (studentList != null) {
            if (studentList.size() != 0) {
                System.out.println("--------------------------------------");
                System.out.printf("student in %s \n", coursecode);

                for (int i = 0; i < studentList.size(); i++) {
                    Student stud = (Student) databaseManager.getObjectbyUsername(studentList.get(i));

                    System.out.printf("%d. %s %s, %s, %s \n", i + 1, stud.getFirstName(), stud.getLastName(),
                            stud.getGender(), stud.getNationality());
                }
                System.out.println("--------------------------------------");
            } else if (studentList.size() == 0) {
                System.out.printf("There are no registered students in %s \n", coursecode);
            }
        }
    }

    private static void getAvailCourse() {
        ArrayList<Course> courseList = null;
        DatabaseManager databaseManager = new DatabaseManager();

        courseList = databaseManager.DeserializeCourseList();

        if (courseList.size() != 0) {
            System.out.println("courses: ");

            for (int i = 0; i < courseList.size(); i++) {
                System.out.printf("%d. %s : \n", i + 1, courseList.get(i).getCourseCode());

                System.out.printf("\t %s \n", courseList.get(i).getCourseDescription());

                Course singleCourse = courseList.get(i);

                System.out.println("-------------------------------------");
                System.out.println("\tindex\tvacacy\twaitlist");

                if (singleCourse.getListCindex() != null) {
                    for (int j = 0; j < singleCourse.getListCindex().size(); j++) {
                        Cindex singleindex = singleCourse.getListCindex().get(j);
                        System.out.printf("%d.\t%s\t%d\t%d\n", j + 1, singleindex.getIndexName(),
                                singleindex.getCurrentVacancy(), singleindex.getWaitList().size());
                    }
                } else {
                    System.out.println("no Cindex");
                }
                System.out.println();
            }
        } else {
            System.out.println("no courses added yet");
        }
    }

    private static void checkVacancy() {
        String coursecode;
        String cindex;

        DatabaseManager databaseManager = new DatabaseManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter coursecode: ");
        coursecode = sc.next();

        System.out.println("Please enter index: ");
        cindex = sc.next();

        int vacancy = databaseManager.getVacancyCindex(coursecode, cindex);

        if (vacancy != -1) {
            System.out.println("--------------------------------");
            System.out.printf("Course Code: %s\t\tIndex: %s\tVacancy: %d\n", coursecode, cindex, vacancy);
            System.out.println("--------------------------------");
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
        Cindex singleIndex = null;
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
                System.out.printf("%s %s\n", singleCourse.getCourseCode(), singleCourse.getCourseName());
                System.out.println("Description: " + singleCourse.getCourseDescription());

                // print list of indexes and vacancies in the course
                // shud show timetable clash for each index
                // show index lesson timings
                if (singleCourse.getListCindex().size() != 0) {
                    System.out.println("Option\t\tIndex\t\tVacacy\t\tWaitlist");
                    for (int i = 0; i < singleCourse.getListCindex().size(); i++) {
                        Cindex singleindex = singleCourse.getListCindex().get(i);
                        System.out.printf("%d.\t\t%s\t\t%d\t\t%d\n", i + 1, singleindex.getIndexName(),
                                singleindex.getCurrentVacancy(), singleindex.getWaitList().size());
                    }
                } else {
                    System.out.println("no Cindex Available!");
                }

            } else {
                System.out.println("course not found! please enter course code again ");
                continue;
            }

            String choiceIndex;

            boolean validchoice = false;

            while (!validchoice) {
                System.out.println("Enter choice: ");
                System.out.println("Enter \'#\'to go back to main menu");
                choiceIndex = sc.next();
                try {
                    if (choiceIndex.equals("#"))
                        return;

                    else if (Integer.parseInt(choiceIndex) - 1 >= 0
                            && Integer.parseInt(choiceIndex) - 1 < singleCourse.getListCindex().size()) {
                        singleIndex = singleCourse.getListCindex().get(Integer.parseInt(choiceIndex) - 1);
                    } else {
                        System.out.println("invalid input! please try again!");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Enter Integer only!");
                    sc.nextLine();
                    continue;
                } catch (Exception e) {
                    System.out.println("invalid input! please try again!");
                    sc.nextLine();
                    continue;
                }
                validchoice = true;
            }

            // check timetable clash

            Cindex courseIndex = databaseManager.searchCindex(coursecode, singleIndex.getIndexName());

            if (stud.checkClash(courseIndex)) {
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

                // ask for confirmation
                boolean validConfirmation = false;
                while (!validConfirmation) {
                    System.out.println("Are you sure? [y/n]");
                    String confirmation1 = sc.next();
                    confirmation1.toUpperCase();
                    if (confirmation1.toUpperCase().equals("N")) {
                        return;
                    } else if (confirmation1.toUpperCase().equals("Y")) {
                        validConfirmation = true;
                    } else {
                        System.out.println("invalid input! Please Enter again!");
                    }
                }

                if (singleIndex.getCurrentVacancy() > 0) {
                    // add course into student reg courses
                    // add studnet into courses reg stud list
                    // minus student s available aus

                    stud.minusAU(singleCourse);
                    singleIndex.addRegisteredStudent(stud.getUsername());

                    // create a new studentCourse
                    StudentCourse newlyregisteredCourse = new StudentCourse(singleCourse.getCourseCode(),
                            singleCourse.getCourseName(), singleCourse.getCourseDescription(),
                            singleIndex.getIndexName());
                    stud.addCourse(newlyregisteredCourse);

                    // update database
                    databaseManager.updateDatabase(stud);
                    databaseManager.updateDatabase(singleCourse);

                    SendMail sendMail = new SendMail();
                    String EmailContent = "Dear Sir/Mdm,\n This a confirmation email that your course "
                            + singleCourse.getCourseCode() + " " + singleCourse.getCourseName() + " index "
                            + singleIndex.getIndexName() + " have been successfully added\n Thank You\n NTU STARS";
                    sendMail.sendgmail("melvinchuaqwerty@gmail.com", "melvinchuaqwerty@gmail.com", "s9825202i",
                            stud.getEmail(), "Course Added", EmailContent);

                    System.out.println("Course added!");
                } else {
                    // check whether student already have the course on waitlist
                    // remove prev waitlist on the course if student want diff index
                    // add stud to waitlist
                    // add Cindex to student waitlist
                    StudentCourse studPrevWaitlistIndex = stud.checkWaitlist(singleCourse.getCourseCode());
                    if (studPrevWaitlistIndex != null) {

                        // course already in stud waitlist
                        // check if stud wan to be on a waitlist for a diff index
                        if (studPrevWaitlistIndex.getCourseIndex().equals(singleIndex.getIndexName())) {
                            System.out.println("you already have this index on waitlist!");
                        } else {
                            System.out.println("Are you sure u want to change waitlist to this index? [y/n]");
                            String confirmation = sc.next();
                            if (confirmation.equals("y")) {
                                StudentCourse newlyregisteredCourse = new StudentCourse(singleCourse.getCourseCode(),
                                        singleCourse.getCourseName(), singleCourse.getCourseDescription(),
                                        singleIndex.getIndexName());
                                // addwaitlist method will remove the prev index
                                stud.addWaitlist(newlyregisteredCourse);
                                singleCourse.removeStudentfromWailist(stud.getUsername());
                                singleIndex.addWaitlistStudent(stud.getUsername());

                                databaseManager.updateDatabase(stud);
                                databaseManager.updateDatabase(singleCourse);

                                System.out.println("Course index full! Adding to waitlist.");
                            }
                        }
                    } else {
                        // course is not in stud waitlist
                        StudentCourse newlyregisteredCourse = new StudentCourse(singleCourse.getCourseCode(),
                                singleCourse.getCourseName(), singleCourse.getCourseDescription(),
                                singleIndex.getIndexName());
                        stud.addWaitlist(newlyregisteredCourse);

                        singleIndex.addWaitlistStudent(stud.getUsername());

                        databaseManager.updateDatabase(stud);
                        databaseManager.updateDatabase(singleCourse);

                        System.out.println("Course index full! Adding to waitlist.");
                    }

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
            int year = -1;
            int month = -1;
            int day = -1;
            int hour = -1;
            int minute = -1;

            Calendar cal = Calendar.getInstance();
            int thisYear = cal.get(Calendar.YEAR);
            int thisMonth = cal.get(Calendar.MONTH);
            int today = cal.get(Calendar.DAY_OF_MONTH);

            // StartDateTime
            System.out.println("-----------------------------------------");
            System.out.println("Access Start Date and Time (MUST BE LATER THAN CURRENT DATE): ");
            System.out.println("Please enter year (YYYY): ");

            try {
                year = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if (year < thisYear || year > thisYear + 1) {
                System.out.println("Please enter an appropriate year!");
                return;
            }

            System.out.println("Please enter month (MM)JAN-1,FEB-2...DEC-12: ");

            try {
                month = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if ((year == thisYear && month < thisMonth + 1) || month < 1 || month > 12) {
                System.out.println("Invalid month!");
                return;
            }

            System.out.println("Please enter day (DD): ");

            try {
                day = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if ((year == thisYear && month == thisMonth + 1 && day < today) || day < 1 || day > 31) {
                System.out.println("Invalid day!");
                return;
            }

            System.out.println("Please enter hour (hh): ");

            try {
                hour = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if (hour < 0 || hour > 24) {
                System.out.println("Invalid hour!");
                return;
            }

            System.out.println("Please enter minute (mm): ");
            try {
                minute = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if (minute < 0 || minute > 59) {
                System.out.println("Invalid minutes!");
                return;
            }

            Calendar accessStartTime = new GregorianCalendar(year, month - 1, day, hour, minute);

            // EndDateTime
            System.out.println("-----------------------------------------");
            System.out.println("Access End Date and Time (MUST BE LATER THAN CURRENT DATE): ");
            System.out.println("Please enter year (YYYY): ");

            try {
                year = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if (year < thisYear || year > thisYear + 1) {
                System.out.println("Please enter an appropriate year!");
                return;
            }

            System.out.println("Please enter month (MM)JAN-1,FEB-2...DEC-12: ");

            try {
                month = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if ((year == thisYear && month < thisMonth + 1) || month < 1 || month > 12) {
                System.out.println("Invalid month!");
                return;
            }

            System.out.println("Please enter day (DD): ");

            try {
                day = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if ((year == thisYear && month == thisMonth + 1 && day < today) || day < 1 || day > 31) {
                System.out.println("Invalid day!");
                return;
            }

            System.out.println("Please enter hour (hh): ");

            try {
                hour = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if (hour < 0 || hour > 24) {
                System.out.println("Invalid hour!");
                return;
            }

            System.out.println("Please enter minute (mm): ");

            try {
                minute = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter Integers Only!");
                return;
            } catch (Exception e) {
                System.out.println("Exception Error");
            }

            if (minute < 0 || minute > 59) {
                System.out.println("Invalid minutes!");
                return;
            }

            Calendar accessEndTime = new GregorianCalendar(year, month - 1, day, hour, minute);

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

        if (coursecode.equals("#"))
            return;
        // else ... error checking
        if (databaseManager.checkStudentReg(username, coursecode)) {
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
        } else {
            System.out.println("cant find course in your registered courses! pls try agaain");
        }

    }

    private static void checkPrintCourseRegistered(String username) {
        Scanner sc = new Scanner(System.in);
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.printCourseRegistered(username);
    }

    private static void AddStudent() {
        DatabaseManager databaseManager = new DatabaseManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter MatricNum: ");

        String matricNum = sc.next().toUpperCase();
        boolean uniqueMatric = databaseManager.verifyUniqueMatricNum(matricNum);
        if (!uniqueMatric) {
            System.out.println("Matric number not unique!");
            return;
        }

        System.out.println("Please enter Username: ");
        String username = sc.next();

        boolean uniqueUsername = databaseManager.verifyUniqueUsername(username);
        if (!uniqueUsername) {
            System.out.println("Username not unique!");
            return;
        }

        System.out.println("Please enter Password: ");
        String password = sc.next();

        System.out.println("Please enter First Name: ");
        String firstname = sc.next();

        System.out.println("Please enter Last Name: ");
        String lastname = sc.next();

        boolean validgender = false;
        GENDER gender = null;
        while (!validgender) {
            System.out.println("Please enter Gender (m/f): ");
            String genderInput = sc.next();
            switch (genderInput.toLowerCase()) {
                case "m": {
                    gender = GENDER.MALE;
                    break;
                }
                case "f": {
                    gender = GENDER.FEMALE;
                    break;
                }
                default: {
                    System.out.println("Please enter 'm' or 'f'");
                    continue;
                }
            }
            validgender = true;
        }

        System.out.println("Please enter Nationality (Country name): ");
        String nationality = sc.next();

        System.out.println("Please enter email: ");
        String email = sc.next();

        System.out.println("----------------------------------");
        System.out.println("New Student Details Summary");
        System.out.println("Matric Number: " + matricNum);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("First Name: " + firstname);
        System.out.println("Last Name: " + lastname);
        System.out.println("Gender: " + gender);
        System.out.println("Nationality: " + nationality);
        System.out.println("Email: " + email);
        System.out.println("----------------------------------");
        System.out.println("Add new Student? [y/n]: ");
        String confirmation = sc.next();

        if (confirmation.equals("y")) {
            long accessStartDateTime = new GregorianCalendar(2020, 01, 01, 00, 00).getTimeInMillis();
            long accessEndDateTime = new GregorianCalendar(2021, 01, 01, 00, 00).getTimeInMillis();

            Student studentObj = new Student(firstname, lastname, gender, nationality, matricNum, username, password, accessStartDateTime, accessEndDateTime, email);

            databaseManager.addStudentintoStudentDB(studentObj);
            databaseManager.printAllStudents();
        }

    }

    private static void vacancyAvailable() {

        Scanner sc = new Scanner(System.in);
        DatabaseManager databaseManager = new DatabaseManager();
        Course singleCourse;

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
                    System.out.printf("%d.  %s  /  %d  /  %d\n", i + 1, singleindex.getIndexName(),
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

        DatabaseManager databaseManager = new DatabaseManager();
        Student studentobj = (Student) databaseManager.getObjectbyUsername(username);
        ArrayList<StudentCourse> registeredcourse = studentobj.getRegisteredCourse();

        while (studentCourse == null) {
            System.out.println("Please enter coursecode that you want to change index: ");
            System.out.println("Enter \'#\' to return to main menu ");
            input = sc.next();

            if (input.equals("#"))
                return;

            for (int i = 0; i < registeredcourse.size(); i++) {
                if (registeredcourse.get(i).getCourseCode().equals(input)) {
                    studentCourse = registeredcourse.get(i);
                    indexOfRegisteredCourse = i;
                }
            }

            if (studentCourse == null) {
                System.out.println("Can't find course in your registered courses! please enter Course Code again!");
            }
        }
        // shud check if whether the course only has one index
        Course coursefromDatabase = databaseManager.searchCourse(studentCourse.getCourseCode());
        if (coursefromDatabase.getListCindex().size() == 1) {
            System.out.println("There is only one index in the course! Cannot change!");
            return;
        }
        System.out.printf("Your Current index: %s \n", studentCourse.getCourseIndex());
        Cindex newindex = null;

        while (newindex == null) {
            System.out.println("Enter New index: ");
            System.out.println("Enter \'#\' to return to main menu ");
            input = sc.next();

            if (input.equals("#"))
                return;

            newindex = databaseManager.searchCindex(studentCourse.getCourseCode(), input);

            if (newindex == null) {
                System.out.println("Can't find the index the course! please enter index number again!");
            }
        }

        Cindex oldindex = databaseManager.searchCindex(studentCourse.getCourseCode(), studentCourse.getCourseIndex());
        // need to check whether the student entered the same index as the old one
        if (!newindex.getIndexName().equals(oldindex.getIndexName())) {
            // check clash for new index
            if (!studentobj.checkClashforSameCourseNewIndex(newindex, studentCourse.getCourseCode())) {
                // no clash with reg course or waitlist
                if (newindex.getCurrentVacancy() == 0) {

                    newindex.getWaitList().add(studentobj.getUsername());
                    registeredcourse.remove(indexOfRegisteredCourse);
                    System.out.println(
                            "index is removed from your registered course, you are placed in waitlist for your new index");
                } else {
                    newindex.getRegisteredStudents().add(studentobj.getUsername());
                    for (int i = 0; i < oldindex.getRegisteredStudents().size(); i++) {
                        if (oldindex.getRegisteredStudents().get(i).equals(studentobj.getUsername())) {
                            oldindex.getRegisteredStudents().remove(i);
                        }
                    }

                    studentCourse.setCourseIndex(newindex.getIndexName());
                    databaseManager.updateDatabase(studentobj);
                    databaseManager.updatecindex(studentCourse.getCourseCode(), oldindex);
                    databaseManager.updatecindex(studentCourse.getCourseCode(), newindex);

                    System.out.println("you have changed your index successfully");
                }
            }

        } else {
            System.out.println("you are already in the index!");
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
            peerPassword = String.valueOf(System.console().readPassword());
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

            Student studentobjPeer = (Student) databaseManager.getObjectbyUsername(peerUsername);
            ArrayList<StudentCourse> registeredcoursePeerList = studentobjPeer.getRegisteredCourse();

            int indexRegisterCourseListpeer = 0;
            for (int i = 0; i < registeredcoursePeerList.size(); i++) {
                if (registeredcoursePeerList.get(i).getCourseCode().equals(courseCode)) {
                    studentCoursePeer = registeredcoursePeerList.get(i);
                    indexRegisterCourseListpeer = i;
                    break;
                }
            }

            System.out.println("your index: " + studentCourse.getCourseIndex());
            System.out.println("peer's index: " + studentCoursePeer.getCourseIndex());
            System.out.println("confirm swap?[y/n]");
            String confirm = sc.next();

            if (confirm.equals("y")) {
                Cindex newindex = databaseManager.searchCindex(studentCourse.getCourseCode(),
                        studentCoursePeer.getCourseIndex());
                Cindex oldindex = databaseManager.searchCindex(studentCourse.getCourseCode(),
                        studentCourse.getCourseIndex());

                newindex.getRegisteredStudents().add(studentobj.getUsername());

                int index = oldindex.getIndexofStudent(username);
                oldindex.getRegisteredStudents().remove(index);

                int peerindex = newindex.getIndexofStudent(peerUsername);
                newindex.getRegisteredStudents().remove(peerindex);

                oldindex.getRegisteredStudents().add(studentobjPeer.getUsername());

                studentCourse.setCourseIndex(newindex.getIndexName());
                studentCoursePeer.setCourseIndex(oldindex.getIndexName());

                registeredcourseList.set(indexRegisterCourseList, studentCourse);
                registeredcoursePeerList.set(indexRegisterCourseListpeer, studentCoursePeer);

                studentobj.setRegisteredCourse(registeredcourseList);
                studentobjPeer.setRegisteredCourse(registeredcoursePeerList);

                databaseManager.updateDatabase(studentobj);
                databaseManager.updateDatabase(studentobjPeer);
                databaseManager.updatecindex(courseCode, newindex);
                databaseManager.updatecindex(courseCode, oldindex);

                System.out.println("you have swapped index successfully");
            }
        }

    }

    public static boolean isValidTime(String time) {
        // Regex to check valid time in 24-hour format.
        String regex = "([01]?[0-9]|2[0-3])[0-5][0-9]";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the time is empty
        // return false
        if (time == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given time
        // and regular expression.
        Matcher m = p.matcher(time);

        // Return if the time
        // matched the ReGex
        return m.matches();
    }

    public static boolean checkClashBetweenLessons(ArrayList<Lesson> schedule) {
        int clashCounter = 0;

        for (int w = 0; w < schedule.size() - 1; w++) {
            Lesson lesson1 = schedule.get(w);
            for (int j = w + 1; j < schedule.size(); j++) {
                Lesson lesson2 = schedule.get(j);
                if (lesson2.getDayoftheWeek().equals(lesson1.getDayoftheWeek())) {
                    if (lesson2.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN)
                            || lesson1.getOddorEven().equals(Main.ODD_EVEN.OOD_AND_EVEN)
                            || lesson1.getOddorEven().equals(lesson2.getDayoftheWeek())) {
                        if (lesson2.getStartTime().before(lesson1.getStartTime())) {

                            if (lesson2.getEndTime().before(lesson1.getStartTime())) {
                                // no clash
                            } else {
                                // clash
                                clashCounter++;
                                System.out.printf("%s %s %s %s - %s\n", lesson1.getLessonType(),
                                        lesson1.getDayoftheWeek(), lesson1.getOddorEven(), lesson1.getVenue(),
                                        lesson1.getStartTime(), lesson1.getEndTime());
                                System.out.println("clashes with ");
                                System.out.printf("%s %s %s %s - %s\n", lesson2.getLessonType(),
                                        lesson2.getDayoftheWeek(), lesson2.getOddorEven(), lesson2.getVenue(),
                                        lesson2.getStartTime(), lesson2.getEndTime());

                            }
                        } else {
                            if (lesson2.getStartTime().before(lesson1.getEndTime())) {
                                // clash
                                clashCounter++;
                                System.out.printf("%s %s %s %s - %s\n", lesson1.getLessonType(),
                                        lesson1.getDayoftheWeek(), lesson1.getOddorEven(), lesson1.getVenue(),
                                        lesson1.getStartTime(), lesson1.getEndTime());
                                System.out.println("clashes with ");
                                System.out.printf("%s %s %s %s - %s\n", lesson2.getLessonType(),
                                        lesson2.getDayoftheWeek(), lesson2.getOddorEven(), lesson2.getVenue(),
                                        lesson2.getStartTime(), lesson2.getEndTime());
                            } else {
                                // no clash

                            }
                        }
                    }
                }
            }

        }
        if (clashCounter > 0) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean ScheduleContainLect(ArrayList<Lesson> schedule) {
        int lectCount = 0;
        int tutCount = 0;
        int labCount = 0;
        for (int i = 0; i < schedule.size(); i++) {
            switch (schedule.get(i).getLessonType()) {
                case LECTURE: {
                    lectCount++;
                    break;
                }
                case TUTORIAL: {
                    tutCount++;
                    break;
                }
                case LAB: {
                    labCount++;
                    break;
                }
            }
        }

        if (lectCount > 0 && tutCount > 0 && labCount > 0) {
            return false;
        } else if (lectCount > 0 && tutCount > 0) {
            return false;
        } else if (lectCount > 0) {
            return false;
        } else {
            return true;
        }

    }

}