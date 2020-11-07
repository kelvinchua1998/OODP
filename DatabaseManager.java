public class DatabaseManager {

    Const FILEPATH= "database\\";

    
    private void SerializeCourseList(List<Course> courseList){
        FileOutputStream fileOut ;
        ObjectOutputStream out;
        try {
            fileOut = new FileOutputStream(FILEPATH+"course.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(courseList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved\n");
          } catch (IOException i) {
            i.printStackTrace();
          }
    
    }
    
    private List<Course> DeserializeCourseList() {
        try{
         List<Course> courseList2;
         FileInputStream fileIn = new FileInputStream(FILEPATH+"course.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         courseList2 = (List<Course>) in.readObject();
         in.close();
         fileIn.close();
          return courseList2;
       } catch (IOException i) {
         i.printStackTrace();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }
       return null;
    }
    
    private void SerializeCindexList(List<Cindex> cindexList){
        FileOutputStream fileOut ;
        ObjectOutputStream out;
        try {
            fileOut = new FileOutputStream(FILEPATH+"cindex.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(cindexList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved\n");
          } catch (IOException i) {
            i.printStackTrace();
          }
    
    }
    
    private List<Cindex> DeserializeCindexList() {
        try{
         List<Cindex> cindexList2;
         FileInputStream fileIn = new FileInputStream(FILEPATH+"cindex.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         cindexList2 = (List<Cindex>) in.readObject();
         in.close();
         fileIn.close();
          return cindexList2;
       } catch (IOException i) {
         i.printStackTrace();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }
       return null;
    }

    
   public void SerializeAdminList(List<Admin> adminList) {
    try {
       FileOutputStream fileOut = new FileOutputStream(FILEPATH+"admin.ser");
       ObjectOutputStream out = new ObjectOutputStream(fileOut);
       out.writeObject(adminList);
       out.close();
       fileOut.close();
       System.out.printf("Serialized data is saved\n");
    } catch (IOException i) {
       i.printStackTrace();
    }
 }
 
 public List<Admin> DeserializeAdminList() {
    try {
       List<Admin> adminList2;
       FileInputStream fileIn = new FileInputStream(FILEPATH+"admin.ser");
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

 
 private void SerializeLabsList(List<Labs> labsList) {
    FileOutputStream fileOut ;
    ObjectOutputStream out;
  try {
     fileOut = new FileOutputStream(FILEPATH+"labs.ser");
    out = new ObjectOutputStream(fileOut);
     out.writeObject(labsList);
     out.close();
     fileOut.close();
     System.out.printf("Serialized data is saved\n");
  } catch (IOException i) {
     i.printStackTrace();
  }
}

private List<Labs> DeserializeLabsList() { 
   try{
     List<Labs> labsList2;
     FileInputStream fileIn = new FileInputStream(FILEPATH+"labs.ser");
     ObjectInputStream in = new ObjectInputStream(fileIn);
     labsList2 = (List<Labs>) in.readObject();
     in.close();
     fileIn.close();
     return labsList2;
  } catch (IOException i) {
     i.printStackTrace();
  } catch (ClassNotFoundException e) {
     e.printStackTrace();
  }
  return null;
}


private void SerializeLectureList(List<Lecture> lectureList) {
    FileOutputStream fileOut ;
    ObjectOutputStream out;
  try {
    fileOut = new FileOutputStream(FILEPATH+"lecture.ser");
    out = new ObjectOutputStream(fileOut);
    out.writeObject(lectureList);
    out.close();
    fileOut.close();
    System.out.printf("Serialized data is saved\n");
  } catch (IOException i) {
    i.printStackTrace();
  }
}

private List<Lecture> DeserializeLectureList() {
   try{
    List<Lecture> lectureList2;
    FileInputStream fileIn = new FileInputStream(FILEPATH+"lecture.ser");
    ObjectInputStream in = new ObjectInputStream(fileIn);
    lectureList2 = (List<Lecture>) in.readObject();
    in.close();
    fileIn.close();
     return lectureList2;
  } catch (IOException i) {
    i.printStackTrace();
  } catch (ClassNotFoundException e) {
    e.printStackTrace();
  }
  return null;
}


public void SerializeStudentList(List<Student> studentList) {
    try {
        FileOutputStream fileOut = new FileOutputStream(FILEPATH+"student.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(studentList);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved\n");
    } catch (IOException i) {
        i.printStackTrace();
    }
}

public List<Student> DeserializeStudentList() {
    try {
        List<Student> studentsList2;
        FileInputStream fileIn = new FileInputStream(FILEPATH+"student.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        studentsList2 = (List<Student>) in.readObject();
        in.close();
        fileIn.close();
        return studentsList2;
    } catch (IOException i) {
        i.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    return null;
}


private void SerializeTutorialList(List<Tutorial> tutorialList) {
    FileOutputStream fileOut ;
    ObjectOutputStream out;
  try {
     fileOut = new FileOutputStream(FILEPATH+"tutorial.ser");
    out = new ObjectOutputStream(fileOut);
     out.writeObject(tutorialList);
     out.close();
     fileOut.close();
     System.out.printf("Serialized data is saved\n");
  } catch (IOException i) {
     i.printStackTrace();
  }
}

private List<Tutorial> DeserializeTutorialList() {
   try{
     List<Tutorial> tutorialList2;
     FileInputStream fileIn = new FileInputStream(FILEPATH+"tutorial.ser");
     ObjectInputStream in = new ObjectInputStream(fileIn);
     tutorialList2 = (List<Tutorial>) in.readObject();
     in.close();
     fileIn.close();
     return tutorialList2;
  } catch (IOException i) {
     i.printStackTrace();
  } catch (ClassNotFoundException e) {
     e.printStackTrace();
  }
  return null;
}
}
