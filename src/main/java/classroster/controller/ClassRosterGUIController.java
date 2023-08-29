package classroster.controller;

import classroster.dao.ClassRosterPersistenceException;
import classroster.dto.Assignment;
import classroster.dto.Student;
import classroster.service.ClassRosterDataValidationException;
import classroster.service.ClassRosterDatabaseOverfillException;
import classroster.service.ClassRosterServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class ClassRosterGUIController{

    private ClassRosterServiceLayer service;

    @Autowired
    public ClassRosterGUIController(ClassRosterServiceLayer service) {
        this.service = service;
    }

    public void run() {
        try {
            new GUI();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
    GUI view is built into the controller as subclass
    due to tight coupling between GUI and Service
     */
    private class GUI implements ActionListener {
        public static final int WIDTH = 750;
        public static final int HEIGHT = 500;
        public final static Color MAIN_BG = new Color(206, 212, 218);
        public final static Color SUB_BG = new Color(108, 117, 125);
        public final static Color TEXT_COLOR = new Color(33, 37, 41);

        // COMPONENTS
        public JFrame frame;
        public JPanel mainPanel, headerPanel, actionPanel, entityPanel;
        public JLabel title;
        public JButton addButton, removeButton, changeViewButton, gradeAssignmentButton;
        public JTable studentTable, assignmentTable;
        public JScrollPane studentPane, assignmentPane;
        public DefaultTableModel studentTableModel, assignmentTableModel;

        // flags
        private boolean studentView = true;  // student view is default

        public GUI() throws ClassRosterPersistenceException {
            // CONSTANTS
            final String[] STUDENT_TABLE_COLUMN_HEADERS = {"ID", "First Name", "Last Name", "Cohort"};
            final Object[][] STUDENT_DATA = service.retrieveStudents()
                    .stream()
                    .map(Student::toArray)
                    .toArray(Object[][]::new);

            final String[] ASSIGNMENT_TABLE_COLUMN_HEADERS = {"ID", "Max Score", "Due Date", "Title"};
            final Object[][] ASSIGNMENT_DATA = service.retrieveAssignments()
                    .stream()
                    .map(Assignment::toArray)
                    .toArray(Object[][]::new);

            // MAIN CONTAINER
            frame = new JFrame("Class Roster Program");
            frame.setIconImage(new ImageIcon("src\\main\\resources\\images\\classrosterlogo.png").getImage());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.setBackground(MAIN_BG);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            mainPanel = new JPanel(new BorderLayout(10, 10)); // set content pane to custom JPanel
            mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
            actionPanel = new JPanel(new GridLayout(4, 1, 25, 25));
            actionPanel.setPreferredSize(new Dimension(250, 500));
            actionPanel.setBackground(SUB_BG);

            addButton = new JButton("Create Student", new ImageIcon("src\\main\\resources\\images\\addicon.png"));
            addButton.setFocusable(false);
            addButton.setBackground(SUB_BG);
            addButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            addButton.setForeground(TEXT_COLOR);
            addButton.setHorizontalTextPosition(JButton.CENTER);
            addButton.setVerticalTextPosition(JButton.BOTTOM);

            removeButton = new JButton("Remove Student", new ImageIcon("src\\main\\resources\\images\\minusicon.png"));
            removeButton.setFocusable(false);
            removeButton.setBackground(SUB_BG);
            removeButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            removeButton.setForeground(TEXT_COLOR);
            removeButton.setHorizontalTextPosition(JButton.CENTER);
            removeButton.setVerticalTextPosition(JButton.BOTTOM);

            changeViewButton = new JButton("View Assignments");
            changeViewButton.setFocusable(false);
            changeViewButton.setBackground(SUB_BG);
            changeViewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            changeViewButton.setForeground(TEXT_COLOR);
            changeViewButton.setHorizontalTextPosition(JButton.CENTER);

            gradeAssignmentButton = new JButton("Grade Assignment");
            gradeAssignmentButton.setFocusable(false);
            gradeAssignmentButton.setBackground(SUB_BG);
            gradeAssignmentButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            gradeAssignmentButton.setForeground(TEXT_COLOR);
            gradeAssignmentButton.setHorizontalTextPosition(JButton.CENTER);
            gradeAssignmentButton.setVisible(!studentView);


            actionPanel.add(addButton);
            addButton.addActionListener(this);
            actionPanel.add(removeButton);
            removeButton.addActionListener(this);
            actionPanel.add(changeViewButton);
            changeViewButton.addActionListener(this);
            actionPanel.add(gradeAssignmentButton);
            gradeAssignmentButton.addActionListener(this);

            // ENTITY PANEL
            entityPanel = new JPanel(new GridLayout(1, 1));
            entityPanel.setPreferredSize(new Dimension(450, 500));
            entityPanel.setBackground(SUB_BG);
            studentTableModel = new DefaultTableModel(STUDENT_DATA, STUDENT_TABLE_COLUMN_HEADERS) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            assignmentTableModel = new DefaultTableModel(ASSIGNMENT_DATA, ASSIGNMENT_TABLE_COLUMN_HEADERS) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            studentTable = new JTable(studentTableModel);
            studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            studentTable.getTableHeader().setReorderingAllowed(false);
            studentTable.getTableHeader().setResizingAllowed(false);
            studentTable.setAutoCreateRowSorter(true);
            studentPane = new JScrollPane(studentTable);

            assignmentTable = new JTable(assignmentTableModel);
            assignmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            assignmentTable.getTableHeader().setReorderingAllowed(false);
            assignmentTable.getTableHeader().setResizingAllowed(false);
            assignmentTable.setAutoCreateRowSorter(true);
            assignmentPane = new JScrollPane(assignmentTable);

            entityPanel.add(studentPane);

            // FINAL COMPONENTS STAGE
            frame.add(headerPanel, BorderLayout.NORTH);
            frame.add(actionPanel, BorderLayout.EAST);
            frame.add(entityPanel, BorderLayout.WEST);
            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int idxToDelete;

            if (e.getSource() == addButton) {
                if (studentView) {
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
                        try{
                            service.createStudent(newStu);
                            studentTableModel.addRow(newStu.toArray());
                        } catch (ClassRosterDatabaseOverfillException | ClassRosterPersistenceException |
                                 ClassRosterDataValidationException ex) {
                            JOptionPane.showMessageDialog(frame,
                                    ex.getMessage(), "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    Assignment assignmentToAdd = new Assignment();
                    try {
                        assignmentToAdd.setMaxScore(Integer.parseInt(
                                        JOptionPane.showInputDialog(frame, "Enter Max Score:")
                                )
                        );
                        assignmentToAdd.setTitle(JOptionPane.showInputDialog(frame, "Enter Assignment Title:"));
                        assignmentToAdd.setDueDate(
                                Date.valueOf(LocalDate.parse(JOptionPane.showInputDialog(frame, "Enter Due Date: (YYYY-MM-DD)")))
                        );
                        service.createAssignment(assignmentToAdd);
                        assignmentTableModel.addRow(assignmentToAdd.toArray());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "A field was incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(ex.getMessage());
                    }
                }
            }
            if (e.getSource() == removeButton) {
                int checkDelete;
                int entityID;
                if (studentView) {
                    idxToDelete = studentTable.getSelectedRow();
                    entityID = Integer.parseInt(
                            String.valueOf(
                                    studentTable.getValueAt(idxToDelete, 0)
                            )
                    );
                    checkDelete = JOptionPane.showConfirmDialog(frame,
                            "Are you sure you want to delete this student?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION);
                    if (checkDelete == 0) {
                        try {
                            studentTableModel.removeRow(idxToDelete);
                            service.removeStudent(entityID);
                        } catch (ClassRosterPersistenceException ex) {
                            JOptionPane.showMessageDialog(frame,
                                    ex.getMessage(),
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    idxToDelete = assignmentTable.getSelectedRow();
                    entityID = Integer.parseInt(
                            String.valueOf(
                                    assignmentTable.getValueAt(idxToDelete, 0)
                            )
                    );
                    checkDelete = JOptionPane.showConfirmDialog(frame,
                            "Are you sure you want to delete this assignment?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION);
                    if (checkDelete == 0) {
                        assignmentTableModel.removeRow(idxToDelete);
                        service.removeAssignment(entityID);

                    }
                }
            }
            if (e.getSource() == changeViewButton) {
                studentView = !studentView;

                if (studentView) {
                    entityPanel.remove(assignmentPane);
                    entityPanel.add(studentPane);
                } else {
                    entityPanel.remove(studentPane);
                    entityPanel.add(assignmentPane);
                }
                addButton.setText(studentView ? "Add Student" : "Add Assignment");
                removeButton.setText(studentView ? "Remove Student" : "Remove Assignment");
                changeViewButton.setText(studentView ? "View Assignments" : "View Students");
                gradeAssignmentButton.setVisible(!studentView);
            }
            if (e.getSource() == gradeAssignmentButton) {
                idxToDelete = assignmentTable.getSelectedRow();
                int assignmentID = Integer.parseInt(
                        String.valueOf(
                        assignmentTable.getValueAt(idxToDelete, 0)
                    )
                );
                Assignment assignmentToGrade = service.retrieveAssignment(assignmentID);
                try {
                    new GradeAssignmentPage(assignmentToGrade);
                } catch (ClassRosterPersistenceException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    /*
     * GradeAssignmentPage class for grading a specific assignment
     */

    private class GradeAssignmentPage {

        private JFrame frame;

        private JPanel mainPanel;
        private DefaultTableModel gradedAssignmentTableModel;

        public GradeAssignmentPage(Assignment assignment) throws ClassRosterPersistenceException {

            // CONSTANTS
            final String[] GRADED_ASSIGNMENT_HEADER_COLUMN = {"Student ID", "First Name", "Last Name", "Assignment ID", "Max Score", "Received Score"};
            final Object[][] GRADED_ASSIGNMENT_DATA = service.retrieveStudents()
                    .stream()
                    .map(s -> new Object[] {s.getStudentID(), s.getFirstName(), s.getLastName(),
                            assignment.getAssignmentID(), assignment.getMaxScore(),
                            (double) 0})
                    .toArray(Object[][]::new);

            // MAIN PANEL
            frame = new JFrame("Grade Assignment Page");
            frame.setIconImage(new ImageIcon("src\\main\\resources\\images\\classrosterlogo.png").getImage());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(GUI.WIDTH, GUI.HEIGHT);
            frame.setBackground(GUI.MAIN_BG);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            mainPanel = new JPanel(new BorderLayout(10, 10)); // set content pane to custom JPanel
            mainPanel.setPreferredSize(new Dimension(GUI.WIDTH, GUI.HEIGHT));
            mainPanel.setBackground(GUI.MAIN_BG);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.setContentPane(mainPanel);



            frame.setVisible(true);
        }
    }
}
