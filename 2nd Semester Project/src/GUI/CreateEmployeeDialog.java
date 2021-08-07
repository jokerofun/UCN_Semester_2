package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import GUI.CreateSaleOrderDialog.AddedProductsListThread;
import GUI.CreateSaleOrderDialog.GetCustomerListWorker;
import GUI.CreateSaleOrderDialog.GetProductListWorker;
import controller.DataAccessException;
import controller.EmployeeController;
import controller.PositionController;
import model.Customer;
import model.Position;

@SuppressWarnings("serial")
public class CreateEmployeeDialog extends JDialog {

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
	private JTextField salaryPerHourTextField;
	JComboBox<String> positionTextField;
	private SynchronizationController synchronizationController;
	PositionController positionController;

	/**
	 * Create the dialog.
	 */
	public CreateEmployeeDialog(SynchronizationController synchronizationController) {
		try {
			employeeController = new EmployeeController();
			positionController = new PositionController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Create Employee");
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
				employeeFirstNameTextField = new JTextField();
				GridBagConstraints gbc_employeeFirstNameTextField = new GridBagConstraints();
				gbc_employeeFirstNameTextField.gridwidth = 5;
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
				gbc_zipcodeLabel.gridx = 6;
				gbc_zipcodeLabel.gridy = 0;
				panel.add(zipcodeLabel, gbc_zipcodeLabel);
			}
			{
				zipcodeTextField = new JTextField();
				GridBagConstraints gbc_zipcodeTextField = new GridBagConstraints();
				gbc_zipcodeTextField.insets = new Insets(0, 0, 5, 0);
				gbc_zipcodeTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_zipcodeTextField.gridx = 7;
				gbc_zipcodeTextField.gridy = 0;
				panel.add(zipcodeTextField, gbc_zipcodeTextField);
				zipcodeTextField.setColumns(10);
			}
			{
				JLabel employeeLastNameLabel = new JLabel("Employee Middle Name:");
				GridBagConstraints gbc_employeeLastNameLabel = new GridBagConstraints();
				gbc_employeeLastNameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_employeeLastNameLabel.gridx = 0;
				gbc_employeeLastNameLabel.gridy = 1;
				panel.add(employeeLastNameLabel, gbc_employeeLastNameLabel);
			}
			{
				employeeMiddleNameTextField = new JTextField();
				GridBagConstraints gbc_employeeMiddleNameTextField = new GridBagConstraints();
				gbc_employeeMiddleNameTextField.gridwidth = 5;
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
				gbc_streetLabel.gridx = 6;
				gbc_streetLabel.gridy = 1;
				panel.add(streetLabel, gbc_streetLabel);
			}
			{
				streetTextField = new JTextField();
				GridBagConstraints gbc_streetTextField = new GridBagConstraints();
				gbc_streetTextField.insets = new Insets(0, 0, 5, 0);
				gbc_streetTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_streetTextField.gridx = 7;
				gbc_streetTextField.gridy = 1;
				panel.add(streetTextField, gbc_streetTextField);
				streetTextField.setColumns(10);
			}
			{
				JLabel lastNameLabel = new JLabel("Employee Last Name:");
				GridBagConstraints gbc_streetLabel = new GridBagConstraints();
				gbc_streetLabel.insets = new Insets(0, 0, 5, 5);
				gbc_streetLabel.gridx = 0;
				gbc_streetLabel.gridy = 2;
				panel.add(lastNameLabel, gbc_streetLabel);
			}
			{
				employeeLastNameTextField = new JTextField();
				GridBagConstraints gbc_employeeLastNameTextField = new GridBagConstraints();
				gbc_employeeLastNameTextField.gridwidth = 5;
				gbc_employeeLastNameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_employeeLastNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_employeeLastNameTextField.gridx = 1;
				gbc_employeeLastNameTextField.gridy = 2;
				panel.add(employeeLastNameTextField, gbc_employeeLastNameTextField);
				employeeLastNameTextField.setColumns(10);
			}
			{
				JLabel cityLabel = new JLabel("City:");
				GridBagConstraints gbc_cityLabel = new GridBagConstraints();
				gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
				gbc_cityLabel.gridx = 6;
				gbc_cityLabel.gridy = 2;
				panel.add(cityLabel, gbc_cityLabel);
			}
			{
				cityTextField = new JTextField();
				GridBagConstraints gbc_cityTextField = new GridBagConstraints();
				gbc_cityTextField.insets = new Insets(0, 0, 5, 0);
				gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cityTextField.gridx = 7;
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
				emailTextField = new JTextField();
				GridBagConstraints gbc_emailTextField = new GridBagConstraints();
				gbc_emailTextField.gridwidth = 5;
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
				gbc_positionLabel.gridx = 6;
				gbc_positionLabel.gridy = 3;
				panel.add(positionLabel, gbc_positionLabel);
			}
			{
				positionTextField = new JComboBox<String>();
				GridBagConstraints gbc_positionTextField = new GridBagConstraints();
				gbc_positionTextField.insets = new Insets(0, 0, 5, 0);
				gbc_positionTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_positionTextField.gridx = 7;
				gbc_positionTextField.gridy = 3;
				panel.add(positionTextField, gbc_positionTextField);
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
				phoneNumberTextField = new HintTextField("+###########");
				GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
				gbc_phoneNumberTextField.gridwidth = 5;
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
				gbc_salaryPerHourLabel.insets = new Insets(0, 0, 0, 5);
				gbc_salaryPerHourLabel.gridx = 6;
				gbc_salaryPerHourLabel.gridy = 4;
				panel.add(salaryPerHourLabel, gbc_salaryPerHourLabel);
			}
			{
				salaryPerHourTextField = new JTextField();
				GridBagConstraints gbc_salaryPerHourTextField = new GridBagConstraints();
				gbc_salaryPerHourTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_salaryPerHourTextField.gridx = 7;
				gbc_salaryPerHourTextField.gridy = 4;
				panel.add(salaryPerHourTextField, gbc_salaryPerHourTextField);
				salaryPerHourTextField.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] { 313, 41, 63, 0 };
			gbl_buttonPane.rowHeights = new int[] { 20, 0 };
			gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			buttonPane.setLayout(gbl_buttonPane);
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
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
			{
				JButton confirmButton = new JButton("Confirm");
				confirmButton.addActionListener(e -> confirm());

				confirmButton.setActionCommand("OK");
				GridBagConstraints gbc_confirmButton = new GridBagConstraints();
				gbc_confirmButton.ipady = 10;
				gbc_confirmButton.ipadx = 10;
				gbc_confirmButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_confirmButton.gridx = 2;
				gbc_confirmButton.gridy = 0;
				buttonPane.add(confirmButton, gbc_confirmButton);
				getRootPane().setDefaultButton(confirmButton);
			}
		}
		
		
			new GetPositionListWorker().execute();
		
	}

	private void confirm() {
		try {
			if (regexChecker()) {
				employeeController.createEmployee(employeeFirstNameTextField.getText(),
						employeeMiddleNameTextField.getText(), employeeLastNameTextField.getText(),
						streetTextField.getText(), zipcodeTextField.getText(), cityTextField.getText(),
						phoneNumberTextField.getText(), emailTextField.getText(),BigDecimal.valueOf(Double.parseDouble(salaryPerHourTextField.getText())) ,positionTextField.getItemAt(positionTextField.getSelectedIndex()));
				synchronizationController.set();
				this.dispose();
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	private void goBack() {
		this.dispose();
	}

	class GetPositionListWorker extends SwingWorker<List<Position>, Void> {

		public GetPositionListWorker() {
		}

		@Override
		protected List<Position> doInBackground() throws Exception {
			List<Position> modelList = new ArrayList<Position>();

			modelList = positionController.getAllPositions();

			return modelList;
		}

		protected void done() {
			try {
				for (Position position : get()) {
					positionTextField.addItem(position.getPositionName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean regexChecker() {
		int res = 0;
		Border defaultBorder = UIManager.getBorder("TextField.border");

		if (RegexEnum.EMAIL.isMatch(emailTextField.getText())) {
			emailTextField.setBorder(defaultBorder);
			res++;
		} else {
			emailTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.PHONENUMBER.isMatch(phoneNumberTextField.getText())) {
			phoneNumberTextField.setBorder(defaultBorder);
			res++;
		} else {
			phoneNumberTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.ZIPCODE.isMatch(zipcodeTextField.getText())) {
			zipcodeTextField.setBorder(defaultBorder);
			res++;
		} else {
			zipcodeTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.LETTERONLY.isMatch(employeeFirstNameTextField.getText())) {
			employeeFirstNameTextField.setBorder(defaultBorder);
			res++;
		} else {
			employeeFirstNameTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.LETTERONLY.isMatch(employeeMiddleNameTextField.getText())) {
			employeeMiddleNameTextField.setBorder(defaultBorder);
			res++;
		} else {
			employeeMiddleNameTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.LETTERONLY.isMatch(employeeLastNameTextField.getText())) {
			employeeLastNameTextField.setBorder(defaultBorder);
			res++;
		} else {
			employeeLastNameTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.LETTERANDNUMBER.isMatch(streetTextField.getText())) {
			streetTextField.setBorder(defaultBorder);
			res++;
		} else {
			streetTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.LETTERANDNUMBER.isMatch(cityTextField.getText())) {
			cityTextField.setBorder(defaultBorder);
			res++;
		} else {
			cityTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.PRICE.isMatch(salaryPerHourTextField.getText())) {
			salaryPerHourTextField.setBorder(defaultBorder);
			res++;
		} else {
			salaryPerHourTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		return res > 8;
	}

}