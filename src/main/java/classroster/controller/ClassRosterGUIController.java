package classroster.controller;

import classroster.dao.ClassRosterPersistenceException;
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
        // COMPONENTS
        private JFrame frame;
        private JPanel mainPanel, headerPanel, actionPanel, studentPanel;
        private JLabel title;
        private JButton addButton, removeButton;
        private JTable studentTable;
        private JScrollPane studentPane;
        private DefaultTableModel studentTableModel;

        public GUI() throws ClassRosterPersistenceException {
            // CONSTANTS
            final Color MAIN_BG = new Color(206, 212, 218);
            final Color SUB_BG = new Color(108, 117, 125);
            final Color TEXT_COLOR = new Color(33, 37, 41);
            final String[] STUDENT_TABLE_COLUMN_HEADERS = {"ID", "First Name", "Last Name", "Cohort"};
            final Object[][] STUDENT_DATA = service.retrieveStudents()
                    .stream()
                    .map(Student::toArray)
                    .toArray(Object[][]::new);

            // MAIN CONTAINER
            frame = new JFrame("Class Roster Program");
            frame.setIconImage(new ImageIcon("src\\main\\resources\\images\\classrosterlogo.png").getImage());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(750, 500);
            frame.setBackground(MAIN_BG);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            mainPanel = new JPanel(new BorderLayout(10, 10)); // set content pane to custom JPanel
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
            addButton.setBounds(50, 50, 150, 75);
            addButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            addButton.setForeground(TEXT_COLOR);
            addButton.setHorizontalTextPosition(JButton.CENTER);
            addButton.setVerticalTextPosition(JButton.BOTTOM);

            removeButton = new JButton("Remove Student", new ImageIcon("src\\main\\resources\\images\\minusicon.png"));
            removeButton.setFocusable(false);
            removeButton.setBackground(SUB_BG);
            removeButton.setBounds(50, 150, 150, 75);
            removeButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            removeButton.setForeground(TEXT_COLOR);
            removeButton.setHorizontalTextPosition(JButton.CENTER);
            removeButton.setVerticalTextPosition(JButton.BOTTOM);

            actionPanel.add(addButton);
            addButton.addActionListener(this);
            actionPanel.add(removeButton);
            removeButton.addActionListener(this);

            // STUDENT PANEL
            studentPanel = new JPanel(new BorderLayout());
            studentPanel.setPreferredSize(new Dimension(450, 500));
            studentPanel.setBackground(SUB_BG);
            studentTableModel = new DefaultTableModel(STUDENT_DATA, STUDENT_TABLE_COLUMN_HEADERS) {
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
            studentPane.setPreferredSize(new Dimension(450, 500));
            studentPanel.add(studentPane, BorderLayout.CENTER);

            // FINAL COMPONENTS STAGE
            frame.add(headerPanel, BorderLayout.NORTH);
            frame.add(actionPanel, BorderLayout.EAST);
            frame.add(studentPanel, BorderLayout.WEST);
            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == removeButton) {
                int idxToDelete = studentTable.getSelectedRow();
                String studentID = String.valueOf(studentTable.getValueAt(idxToDelete, 0));
                int checkDelete = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete this student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
                if (checkDelete == 0)
                    try {
                        studentTableModel.removeRow(idxToDelete);
                        service.removeStudent(Integer.parseInt(studentID));
                    } catch (ClassRosterPersistenceException ex) {
                        JOptionPane.showMessageDialog(frame,
                                ex.getMessage(),
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
            }

            if (e.getSource() == addButton) {
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
            }
        }
    }
}
