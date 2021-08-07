package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DataAccessException;
import controller.EmployeeController;
import model.Employee;
import model.Position;

@SuppressWarnings("serial")
public class UpdateEmployeeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private EmployeeController employeeController;
	private JTextField employeeFirstNameTextField;
	private JTextField employeeMiddleNameTextField;
	private JTextField employeeLastNameTextField;
	private JTextField zipcodeTextField;
	private JTextField cityTextField;
	private JTextField phoneNumberTextField;
	private JTextField emailTextField;
	private JTextField streetTextField;
	private SynchronizationController synchronizationController;

	public UpdateEmployeeDialog(Employee employee, SynchronizationController synchronizationController) {
		try {
			employeeController = new EmployeeController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Employee Edit Menu - Employee Last Name: " + employee.getLastName());
		setBounds(100, 100, 433, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel employeeFirstNameLabel = new JLabel("Employee First Name:");
				GridBagConstraints gbc_employeeFirstNameLabel = new GridBagConstraints();
				gbc_employeeFirstNameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_employeeFirstNameLabel.gridx = 0;
				gbc_employeeFirstNameLabel.gridy = 0;
				panel.add(employeeFirstNameLabel, gbc_employeeFirstNameLabel);
			}
			{
				employeeFirstNameTextField = new JTextField("" + employee.getFirstName());
				GridBagConstraints gbc_employeeFirstNameTextField = new GridBagConstraints();
				gbc_employeeFirstNameTextField.gridwidth = 3;
				gbc_employeeFirstNameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_employeeFirstNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_employeeFirstNameTextField.gridx = 1;
				gbc_employeeFirstNameTextField.gridy = 0;
				panel.add(employeeFirstNameTextField, gbc_employeeFirstNameTextField);
				employeeFirstNameTextField.setColumns(10);
			}
			{
				JLabel zipcodeLabel = new JLabel("Zipcode:");
				GridBagConstraints gbc_zipcodeLabel = new GridBagConstraints();
				gbc_zipcodeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_zipcodeLabel.gridx = 4;
				gbc_zipcodeLabel.gridy = 0;
				panel.add(zipcodeLabel, gbc_zipcodeLabel);
			}
			{
				zipcodeTextField = new JTextField("" + employee.getZipcode());
				GridBagConstraints gbc_zipcodeTextField = new GridBagConstraints();
				gbc_zipcodeTextField.gridwidth = 3;
				gbc_zipcodeTextField.insets = new Insets(0, 0, 5, 0);
				gbc_zipcodeTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_zipcodeTextField.gridx = 5;
				gbc_zipcodeTextField.gridy = 0;
				panel.add(zipcodeTextField, gbc_zipcodeTextField);
				zipcodeTextField.setColumns(10);
			}
			{
				JLabel employeeMiddleNameLabel = new JLabel("Employee Middle Name:");
				GridBagConstraints gbc_employeeMiddleNameLabel = new GridBagConstraints();
				gbc_employeeMiddleNameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_employeeMiddleNameLabel.gridx = 0;
				gbc_employeeMiddleNameLabel.gridy = 1;
				panel.add(employeeMiddleNameLabel, gbc_employeeMiddleNameLabel);
			}
			{
				employeeMiddleNameTextField = new JTextField("" + employee.getMiddleName());
				GridBagConstraints gbc_employeeMiddleNameTextField = new GridBagConstraints();
				gbc_employeeMiddleNameTextField.gridwidth = 3;
				gbc_employeeMiddleNameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_employeeMiddleNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_employeeMiddleNameTextField.gridx = 1;
				gbc_employeeMiddleNameTextField.gridy = 1;
				panel.add(employeeMiddleNameTextField, gbc_employeeMiddleNameTextField);
				employeeMiddleNameTextField.setColumns(10);
			}
			{
				JLabel streetLabel = new JLabel("Street:");
				GridBagConstraints gbc_streetLabel = new GridBagConstraints();
				gbc_streetLabel.insets = new Insets(0, 0, 5, 5);
				gbc_streetLabel.gridx = 4;
				gbc_streetLabel.gridy = 1;
				panel.add(streetLabel, gbc_streetLabel);
			}
			{
				streetTextField = new JTextField("" + employee.getStreet());
				GridBagConstraints gbc_streetTextField = new GridBagConstraints();
				gbc_streetTextField.gridwidth = 3;
				gbc_streetTextField.insets = new Insets(0, 0, 5, 0);
				gbc_streetTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_streetTextField.gridx = 5;
				gbc_streetTextField.gridy = 1;
				panel.add(streetTextField, gbc_streetTextField);
				streetTextField.setColumns(10);
			}
			{
				JLabel employeeLastNameLabel = new JLabel("Employee Last Name:");
				GridBagConstraints gbc_employeeLastNameLabel = new GridBagConstraints();
				gbc_employeeLastNameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_employeeLastNameLabel.gridx = 0;
				gbc_employeeLastNameLabel.gridy = 2;
				panel.add(employeeLastNameLabel, gbc_employeeLastNameLabel);
			}
			{
				employeeLastNameTextField = new JTextField("" + employee.getEmail());
				GridBagConstraints gbc_employeeLastNameTextField = new GridBagConstraints();
				gbc_employeeLastNameTextField.gridwidth = 3;
				gbc_employeeLastNameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_employeeLastNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_employeeLastNameTextField.gridx = 1;
				gbc_employeeLastNameTextField.gridy = 2;
				panel.add(employeeLastNameTextField, gbc_employeeLastNameTextField);
				employeeLastNameTextField.setColumns(10);
			}
			{
				JLabel cityLabel = new JLabel("City");
				GridBagConstraints gbc_cityLabel = new GridBagConstraints();
				gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
				gbc_cityLabel.gridx = 4;
				gbc_cityLabel.gridy = 2;
				panel.add(cityLabel, gbc_cityLabel);
			}
			{
				cityTextField = new JTextField("" + employee.getCity());
				GridBagConstraints gbc_cityTextField = new GridBagConstraints();
				gbc_cityTextField.gridwidth = 3;
				gbc_cityTextField.insets = new Insets(0, 0, 5, 0);
				gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cityTextField.gridx = 5;
				gbc_cityTextField.gridy = 2;
				panel.add(cityTextField, gbc_cityTextField);
				cityTextField.setColumns(10);
			}
			{
				JLabel emailLabel = new JLabel("Email:");
				GridBagConstraints gbc_emailLabel = new GridBagConstraints();
				gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
				gbc_emailLabel.gridx = 0;
				gbc_emailLabel.gridy = 3;
				panel.add(emailLabel, gbc_emailLabel);
			}
			{
				emailTextField = new JTextField("" + employee.getEmail());
				GridBagConstraints gbc_emailTextField = new GridBagConstraints();
				gbc_emailTextField.gridwidth = 3;
				gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
				gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_emailTextField.gridx = 1;
				gbc_emailTextField.gridy = 3;
				panel.add(emailTextField, gbc_emailTextField);
				emailTextField.setColumns(10);
			}
			{
				JLabel positionLabel = new JLabel("Position:");
				GridBagConstraints gbc_positionLabel = new GridBagConstraints();
				gbc_positionLabel.insets = new Insets(0, 0, 5, 5);
				gbc_positionLabel.gridx = 4;
				gbc_positionLabel.gridy = 3;
				panel.add(positionLabel, gbc_positionLabel);
			}
			{
				JComboBox<Position> positionComboBox = new JComboBox<Position>();
				positionComboBox.setEditable(true);
				GridBagConstraints gbc_positionComboBox = new GridBagConstraints();
				gbc_positionComboBox.gridwidth = 3;
				gbc_positionComboBox.insets = new Insets(0, 0, 5, 0);
				gbc_positionComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_positionComboBox.gridx = 5;
				gbc_positionComboBox.gridy = 3;
				panel.add(positionComboBox, gbc_positionComboBox);
			}
			{
				JLabel phoneNumberLabel = new JLabel("Phone Number:");
				GridBagConstraints gbc_phoneNumberLabel = new GridBagConstraints();
				gbc_phoneNumberLabel.insets = new Insets(0, 0, 0, 5);
				gbc_phoneNumberLabel.gridx = 0;
				gbc_phoneNumberLabel.gridy = 4;
				panel.add(phoneNumberLabel, gbc_phoneNumberLabel);
			}
			{
				phoneNumberTextField = new JTextField("" + employee.getPhoneNumber());
				GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
				gbc_phoneNumberTextField.gridwidth = 3;
				gbc_phoneNumberTextField.insets = new Insets(0, 0, 0, 5);
				gbc_phoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_phoneNumberTextField.gridx = 1;
				gbc_phoneNumberTextField.gridy = 4;
				panel.add(phoneNumberTextField, gbc_phoneNumberTextField);
				phoneNumberTextField.setColumns(10);
			}
			{
				JLabel salaryPerHourLabel = new JLabel("Salary Per Hour:");
				GridBagConstraints gbc_salaryPerHourLabel = new GridBagConstraints();
				gbc_salaryPerHourLabel.anchor = GridBagConstraints.EAST;
				gbc_salaryPerHourLabel.insets = new Insets(0, 0, 0, 5);
				gbc_salaryPerHourLabel.gridx = 4;
				gbc_salaryPerHourLabel.gridy = 4;
				panel.add(salaryPerHourLabel, gbc_salaryPerHourLabel);
			}
			{
				JTextField salaryTextField = new JTextField("" + employee.getSalaryPerHour());
				GridBagConstraints gbc_salaryTextField = new GridBagConstraints();
				gbc_salaryTextField.gridwidth = 3;
				gbc_salaryTextField.insets = new Insets(0, 0, 0, 5);
				gbc_salaryTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_salaryTextField.gridx = 5;
				gbc_salaryTextField.gridy = 4;
				panel.add(salaryTextField, gbc_salaryTextField);
				salaryTextField.setColumns(10);
			}
			{
				JPanel buttonPanel = new JPanel();
				getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				GridBagLayout gbl_buttonPanel = new GridBagLayout();
				gbl_buttonPanel.columnWidths = new int[] { 313, 41, 63, 0 };
				gbl_buttonPanel.rowHeights = new int[] { 20, 0 };
				gbl_buttonPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
				gbl_buttonPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
				buttonPanel.setLayout(gbl_buttonPanel);
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(e -> goBack());

					cancelButton.setActionCommand("Cancel");
					GridBagConstraints gbc_cancelButton = new GridBagConstraints();
					gbc_cancelButton.ipady = 10;
					gbc_cancelButton.ipadx = 10;
					gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
					gbc_cancelButton.anchor = GridBagConstraints.NORTHWEST;
					gbc_cancelButton.gridx = 0;
					gbc_cancelButton.gridy = 0;
					buttonPanel.add(cancelButton, gbc_cancelButton);
				}
				{
					JButton confirmButton = new JButton("Confirm");
					confirmButton.addActionListener(e -> {

						employee.setFirstName(employeeFirstNameTextField.getText());
						employee.setMiddleName(employeeMiddleNameTextField.getText());
						employee.setLastName(employeeLastNameTextField.getText());
						employee.setEmail(emailTextField.getText());
						employee.setPhoneNumber(phoneNumberTextField.getText());
						employee.setZipcode(zipcodeTextField.getText());
						employee.setStreet(streetTextField.getText());
						employee.setCity(cityTextField.getText());

						confirm(employee);
					});

					confirmButton.setActionCommand("OK");
					GridBagConstraints gbc_confirmButton = new GridBagConstraints();
					gbc_confirmButton.ipady = 10;
					gbc_confirmButton.ipadx = 10;
					gbc_confirmButton.anchor = GridBagConstraints.NORTHWEST;
					gbc_confirmButton.gridx = 2;
					gbc_confirmButton.gridy = 0;
					buttonPanel.add(confirmButton, gbc_confirmButton);
					getRootPane().setDefaultButton(confirmButton);

				}
			}
		}
	}

	private void confirm(Employee employee) {
		try {
			employeeController.updateEmployee(employee);
			synchronizationController.set();
			this.dispose();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	private void goBack() {
		this.dispose();
	}

}
