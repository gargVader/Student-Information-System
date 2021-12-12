import java.io.Serializable;

public class Student implements Comparable, Serializable {

    String firstName, lastName;
    int roll;
    int marks;
    String grade;

    public Student(String firstName, String lastName, int marks, String grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
        this.grade = grade;
    }

    public Student(String firstName, String lastName, int roll, int marks, String grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roll = roll;
        this.marks = marks;
        this.grade = grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Compares a student progressively based on firstName, lastName and roll
    @Override
    public int compareTo(Object o) {
        Student student = (Student) o;
        if (student != null) {
            if (student.firstName != firstName) {
                return firstName.compareTo(student.firstName);
            } else if (student.lastName != lastName) {
                return lastName.compareTo(student.lastName);
            } else if (student.roll != roll) {
                return roll - student.roll;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return firstName
                + " "
                + lastName
                + " "
                + roll
                + " "
                + marks
                + " "
                + grade;
    }
}
