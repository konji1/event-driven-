import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoForm extends JFrame {

    private JTextField nameTextField, mobileTextField, addressTextField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JComboBox<String> dobYearComboBox, dobMonthComboBox, dobDayComboBox;
    private DefaultTableModel tableModel;

    public UserInfoForm() {
        // Set up the main frame
        setTitle("User Information Form");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        nameTextField = new JTextField(20);
        mobileTextField = new JTextField(20);
        addressTextField = new JTextField(20);

        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);

        // Initialize the date of birth dropdowns
        dobYearComboBox = new JComboBox<>(getYearRange());
        dobMonthComboBox = new JComboBox<>(getMonthRange());
        dobDayComboBox = new JComboBox<>(getDayRange());

        JButton addButton = new JButton("Add to Database");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToDatabase();
            }
        });

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Mobile");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Date of Birth");
        tableModel.addColumn("Address");

        JTable databaseTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(databaseTable);

        // Set up the layout
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameTextField);
        formPanel.add(new JLabel("Mobile:"));
        formPanel.add(mobileTextField);
        formPanel.add(new JLabel("Gender:"));
        formPanel.add(maleRadioButton);
        formPanel.add(new JLabel(""));
        formPanel.add(femaleRadioButton);
        formPanel.add(new JLabel("Date of Birth:"));
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.add(dobYearComboBox);
        dobPanel.add(new JLabel("/"));
        dobPanel.add(dobMonthComboBox);
        dobPanel.add(new JLabel("/"));
        dobPanel.add(dobDayComboBox);
        formPanel.add(dobPanel);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressTextField);
        formPanel.add(new JLabel(""));
        formPanel.add(addButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Set up the frame
        add(mainPanel);
        setVisible(true);
    }

    private String[] getYearRange() {
        String[] years = new String[27];
        for (int i = 0; i < 27; i++) {
            years[i] = String.valueOf(1984 + i);
        }
        return years;
    }

    private String[] getMonthRange() {
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }

    private String[] getDayRange() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        return days;
    }

    private void addToDatabase() {
        String name = nameTextField.getText();
        String mobile = mobileTextField.getText();
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";
        String dob = String.format("%s/%02d/%02d", dobYearComboBox.getSelectedItem(), dobMonthComboBox.getSelectedIndex() + 1, Integer.parseInt((String) dobDayComboBox.getSelectedItem()));
        String address = addressTextField.getText();

        // Validate input (you may add more validation)
        if (name.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add to the table model
        String[] rowData = {name, mobile, gender, dob, address};
        tableModel.addRow(rowData);

        // Clear the input fields
        nameTextField.setText("");
        mobileTextField.setText("");
        addressTextField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserInfoForm();
            }
        });
    }
}
