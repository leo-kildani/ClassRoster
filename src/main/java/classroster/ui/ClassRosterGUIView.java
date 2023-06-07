package classroster.ui;

import classroster.dto.Student;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ClassRosterGUIView implements ActionListener {

    // COMPONENTS
    private JFrame frame;
    private JPanel mainPanel, headerPanel, actionPanel, studentPanel;
    private JLabel title;
    private JButton addButton, removeButton;

    // DATA
    public Student runningStudentData;
    public Integer runningStudentId;

    public ClassRosterGUIView() {
        // CONSTANTS
        final Color MAIN_BG = new Color(206, 212, 218);
        final Color SUB_BG = new Color(108, 117, 125);
        final Color TEXT_COLOR = new Color(33, 37, 41);

        // MAIN CONTAINER
        frame = new JFrame("Class Roster Program");
        frame.setIconImage(new ImageIcon("src\\main\\resources\\images\\classrosterlogo.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setBackground(MAIN_BG);
        frame.setLocationRelativeTo(null);
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        mainPanel.setBackground(MAIN_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setContentPane(mainPanel);
        
        // HEADER CONTAINER
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(500, 60));
        headerPanel.setBackground(SUB_BG);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        title = new JLabel("Class Roster", new ImageIcon("src\\main\\resources\\images\\classrosterlogo.png"), JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Ariel Gothic", Font.BOLD, 25));
        title.setHorizontalTextPosition(JLabel.RIGHT);
        title.setForeground(TEXT_COLOR);

        headerPanel.add(title);

        // ACTIONS CONTAINER
        actionPanel = new JPanel(null);
        actionPanel.setPreferredSize(new Dimension(250, 500));
        actionPanel.setBackground(SUB_BG);

        addButton = new JButton("Add Student", new ImageIcon("src\\main\\resources\\images\\addicon.png"));
        addButton.setFocusable(false);
        addButton.setBackground(SUB_BG);
        addButton.setBounds(25, 50, 200, 75);
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        addButton.setForeground(TEXT_COLOR);
        addButton.setHorizontalTextPosition(JButton.RIGHT);
        addButton.setVerticalTextPosition(JButton.CENTER);

        removeButton = new JButton("Remove Student", new ImageIcon("src\\main\\resources\\images\\minusicon.png"));
        removeButton.setFocusable(false);
        removeButton.setBackground(SUB_BG);
        removeButton.setBounds(25, 150, 200, 75);
        removeButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        removeButton.setForeground(TEXT_COLOR);
        removeButton.setHorizontalTextPosition(JButton.RIGHT);
        removeButton.setVerticalTextPosition(JButton.CENTER);

        actionPanel.add(addButton);
        addButton.addActionListener(this);
        actionPanel.add(removeButton);

        // STUDENT PANEL
        studentPanel = new JPanel();
        studentPanel.setPreferredSize(new Dimension(450, 500));
        studentPanel.setBackground(SUB_BG);

        // FINAL COMPONENTS STAGE
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(actionPanel, BorderLayout.EAST);
        frame.add(studentPanel, BorderLayout.WEST);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            runningStudentData = null;  // reset runningStudentData
            String[] studentTokens = new String[3];  // {firstName, lastName, cohort}
            studentTokens[0] = JOptionPane.showInputDialog(frame, "Enter First Name.");
            studentTokens[1] = JOptionPane.showInputDialog(frame, "Enter Last Name.");
            studentTokens[2] = JOptionPane.showInputDialog(frame, "Enter Cohort");

            int checkData = JOptionPane.showConfirmDialog(frame, "Is the following information correct?\n" +
                    "First Name: " + studentTokens[0] + "\n" +
                    "Last Name: " + studentTokens[1] + "\n" +
                    "Cohort: " + studentTokens[2],
                    "Information Check",
                    JOptionPane.YES_NO_OPTION);
            if (checkData == 0) {
                Student newStu = new Student();
                newStu.setFirstName(studentTokens[0]);
                newStu.setLastName(studentTokens[1]);
                newStu.setCohort(studentTokens[2]);
                runningStudentData = newStu;
            }
        }
    }

    public static void main(String[] args) {
        new ClassRosterGUIView();
    }
}
