import java.io.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Data {

    private static Data instance = null;
    private static LinkedList<Student> studentList;
    private static Stack<Student> recycleBin;
    private static File studentDataFile = new File("StudentData.txt");
    private static File recycleBinFile = new File("RecycleBin.txt");
    private static FileHandler logFileHandler;
    private static ObjectInputStream studentIS = null;
    private static ObjectOutputStream studentOS = null;
    private static ObjectInputStream recycleBinIS = null;
    private static ObjectOutputStream recycleBinOS = null;
    private static Logger logger = Logger.getLogger("Mylog");

    static {
        try {
            logFileHandler = new FileHandler("logFile.log");
            studentIS = new ObjectInputStream(new FileInputStream(studentDataFile));
            studentOS = new ObjectOutputStream(new FileOutputStream(studentDataFile));
            studentIS = new ObjectInputStream(new FileInputStream(studentDataFile));
            studentOS = new ObjectOutputStream(new FileOutputStream(studentDataFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Data() {
        initStudentList();
        logger.addHandler(logFileHandler);
        recycleBin = new Stack<>();
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    private static void updateLog(String action) {
        logger.info(action);
    }

    private static void updateFile() {
        try {
            studentOS.writeObject(studentList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStudentList() {
        studentList = new LinkedList<>();
        try {
            studentList = (LinkedList<Student>) studentIS.readObject();
        } catch (Exception e) {
        }
    }

    public void addStudent(Student student) {
        if (student != null) {
            student.setRoll(studentList.size() + 1);
            int idx = 0;
            for (int i = 0; i < studentList.size(); i++) {
                Student currStudent = studentList.get(i);
                if (currStudent.compareTo(student) > 0) {
                    break;
                }
                idx++;
            }
            studentList.add(idx, student);
        }
        updateLog("Added student");
        updateFile();
    }

    public void deleteStudent(String firstName, String lastName, int roll) {
        for (int i = 0; i < studentList.size(); i++) {
            Student currStudent = studentList.get(i);
            if (currStudent.firstName.equals(firstName) && currStudent.lastName.equals(lastName) && currStudent.roll == roll) {
                studentList.remove(i);
                recycleBin.push(currStudent);
                updateLog("Deleted Student");
                return;
            }
        }
    }

    public Student searchStudent(String firstName, String lastName, int roll) {
        for (Student student : studentList) {
            if (student.firstName.equals(firstName) && student.lastName.equals(lastName) && student.roll == roll) {
                return student;
            }
        }
        updateLog("Search Student");
        return null;
    }

    public void modifyStudentByRoll(Student student) {
        for (int i = 0; i < studentList.size(); i++) {
            Student currStudent = studentList.get(i);
            if (student.roll == currStudent.roll) {
                studentList.set(i, student);
                return;
            }
        }
    }

    public void printAllStudents() {
        System.out.println(studentList);
    }

    public String getAllStudents() {

        String result = "";
        for (Student student : studentList) {
            result += student.toString() + "\n";
        }
        return result;
    }

    public Stack getRecycleBin() {
        return recycleBin;
    }


}
