import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJFrame extends JFrame implements ActionListener {
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel firstName;
    private JTextField firstName_field;
    private JLabel lastName;
    private JTextField lastName_field;
    private JLabel marks;
    private JTextField marks_field;
    private JLabel grade;
    private JTextField grade_field;
    private JLabel roll;
    private JTextField roll_field;
    private JButton insert;
    private JButton delete;
    private JButton modify;
    private JButton search;
    private JButton view;
    private JTextArea result;

    public MyJFrame() {
        setTitle("Student Information System");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Student Information System");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(500, 30);
        title.setLocation(300, 30);
        c.add(title);

        firstName = new JLabel("First Name");
        firstName.setFont(new Font("Arial", Font.PLAIN, 20));
        firstName.setSize(100, 20);
        firstName.setLocation(100, 100);
        c.add(firstName);

        firstName_field = new JTextField();
        firstName_field.setFont(new Font("Arial", Font.PLAIN, 15));
        firstName_field.setSize(190, 20);
        firstName_field.setLocation(200, 100);
        c.add(firstName_field);

        lastName = new JLabel("Last Name");
        lastName.setFont(new Font("Arial", Font.PLAIN, 20));
        lastName.setSize(100, 20);
        lastName.setLocation(100, 150);
        c.add(lastName);

        lastName_field = new JTextField();
        lastName_field.setFont(new Font("Arial", Font.PLAIN, 15));
        lastName_field.setSize(190, 20);
        lastName_field.setLocation(200, 150);
        c.add(lastName_field);

        marks = new JLabel("Marks");
        marks.setFont(new Font("Arial", Font.PLAIN, 20));
        marks.setSize(100, 20);
        marks.setLocation(100, 200);
        c.add(marks);

        marks_field = new JTextField();
        marks_field.setFont(new Font("Arial", Font.PLAIN, 15));
        marks_field.setSize(190, 20);
        marks_field.setLocation(200, 200);
        c.add(marks_field);

        grade = new JLabel("Grade");
        grade.setFont(new Font("Arial", Font.PLAIN, 20));
        grade.setSize(100, 20);
        grade.setLocation(100, 250);
        c.add(grade);

        grade_field = new JTextField();
        grade_field.setFont(new Font("Arial", Font.PLAIN, 15));
        grade_field.setSize(190, 20);
        grade_field.setLocation(200, 250);
        c.add(grade_field);

        roll = new JLabel("<html> Roll<br>[Not required for insertion] </html>");
        roll.setFont(new Font("Arial", Font.PLAIN, 14));
        roll.setSize(100, 45);
        roll.setLocation(100, 300);
        c.add(roll);

        roll_field = new JTextField();
        roll_field.setFont(new Font("Arial", Font.PLAIN, 15));
        roll_field.setSize(190, 20);
        roll_field.setLocation(200, 300);
        c.add(roll_field);

        result = new JTextArea();
        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setSize(200, 350);
        result.setLocation(580, 100);
        result.setLineWrap(true);
        c.add(result);

        insert = new JButton("INSERT");
        insert.setFont(new Font("Arial", Font.PLAIN, 15));
        insert.setSize(100, 20);
        insert.setLocation(150, 350);
        insert.addActionListener(this);
        c.add(insert);

        delete = new JButton("DELETE");
        delete.setFont(new Font("Arial", Font.PLAIN, 15));
        delete.setSize(100, 20);
        delete.setLocation(300, 350);
        delete.addActionListener(this);
        c.add(delete);

        modify = new JButton("MODIFY");
        modify.setFont(new Font("Arial", Font.PLAIN, 15));
        modify.setSize(100, 20);
        modify.setLocation(150, 400);
        modify.addActionListener(this);
        c.add(modify);

        search = new JButton("SEARCH");
        search.setFont(new Font("Arial", Font.PLAIN, 15));
        search.setSize(100, 20);
        search.setLocation(300, 400);
        search.addActionListener(this);
        c.add(search);

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {

        String FName = firstName_field.getText();
        String LName = lastName_field.getText();
        int mks = 0;
        int roll = 0;
        String grd = grade_field.getText();
        try {
            mks = Integer.valueOf(marks_field.getText());
            roll = Integer.valueOf(roll_field.getText());
        } catch (Exception exception) {

        }

        Student st;
        if (e.getSource() == insert) {
            Data.getInstance().addStudent(new Student(FName, LName, mks, grd));
        }
        if (e.getSource() == delete) {
            Data.getInstance().deleteStudent(FName, LName, roll);
        }
        if (e.getSource() == modify) {
            Data.getInstance().modifyStudentByRoll(new Student(FName, LName, roll, mks, grd));
        }

        if (e.getSource() == search) {
            st = Data.getInstance().searchStudent(FName, LName, roll);
            result.setText("Student Found: \n" + st.toString());
        }else{
            result.setText(Data.getInstance().getAllStudents());
        }

    }
}
