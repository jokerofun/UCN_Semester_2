package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.CustomerController;
import controller.DataAccessException;

@SuppressWarnings("serial")
public class CreateCustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private CustomerController customerController;
	private JTextField customerNameTextField;
	private JTextField CUITextField;
	private JTextField emailTextField;
	private JTextField phoneNumberTextField;
	private JTextField zipcodeTextField;
	private JTextField streetTextField;
	private JTextField cityTextField;
	private SynchronizationController synchronizationController;

	/**
	 * Create the dialog.
	 */
	public CreateCustomerDialog(SynchronizationController synchronizationController) {
		try {
			customerController = new CustomerController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Create Customer");
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
				JLabel customerNameLabel = new JLabel("Customer Name:");
				GridBagConstraints gbc_customerNameLabel = new GridBagConstraints();
				gbc_customerNameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_customerNameLabel.gridx = 0;
				gbc_customerNameLabel.gridy = 0;
				panel.add(customerNameLabel, gbc_customerNameLabel);
			}
			{
				customerNameTextField = new JTextField();
				GridBagConstraints gbc_customerNameTextField = new GridBagConstraints();
				gbc_customerNameTextField.gridwidth = 3;
				gbc_customerNameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_customerNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_customerNameTextField.gridx = 1;
				gbc_customerNameTextField.gridy = 0;
				panel.add(customerNameTextField, gbc_customerNameTextField);
				customerNameTextField.setColumns(10);
			}
			{
				JLabel streetLabel = new JLabel("Street:");
				GridBagConstraints gbc_streetLabel = new GridBagConstraints();
				gbc_streetLabel.anchor = GridBagConstraints.EAST;
				gbc_streetLabel.insets = new Insets(0, 0, 5, 5);
				gbc_streetLabel.gridx = 4;
				gbc_streetLabel.gridy = 0;
				panel.add(streetLabel, gbc_streetLabel);
			}
			{
				streetTextField = new JTextField();
				GridBagConstraints gbc_streetTextField = new GridBagConstraints();
				gbc_streetTextField.gridwidth = 3;
				gbc_streetTextField.insets = new Insets(0, 0, 5, 0);
				gbc_streetTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_streetTextField.gridx = 5;
				gbc_streetTextField.gridy = 0;
				panel.add(streetTextField, gbc_streetTextField);
				streetTextField.setColumns(10);
			}
			{
				JLabel customerCUILabel = new JLabel("CUI:");
				GridBagConstraints gbc_customerCUILabel = new GridBagConstraints();
				gbc_customerCUILabel.insets = new Insets(0, 0, 5, 5);
				gbc_customerCUILabel.gridx = 0;
				gbc_customerCUILabel.gridy = 1;
				panel.add(customerCUILabel, gbc_customerCUILabel);
			}
			{
				CUITextField = new JTextField();
				GridBagConstraints gbc_CUITextField = new GridBagConstraints();
				gbc_CUITextField.gridwidth = 3;
				gbc_CUITextField.insets = new Insets(0, 0, 5, 5);
				gbc_CUITextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_CUITextField.gridx = 1;
				gbc_CUITextField.gridy = 1;
				panel.add(CUITextField, gbc_CUITextField);
				CUITextField.setColumns(10);
			}
			{
				JLabel cityLabel = new JLabel("City:");
				GridBagConstraints gbc_cityLabel = new GridBagConstraints();
				gbc_cityLabel.anchor = GridBagConstraints.EAST;
				gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
				gbc_cityLabel.gridx = 4;
				gbc_cityLabel.gridy = 1;
				panel.add(cityLabel, gbc_cityLabel);
			}
			{
				cityTextField = new JTextField();
				GridBagConstraints gbc_cityTextField = new GridBagConstraints();
				gbc_cityTextField.gridwidth = 3;
				gbc_cityTextField.insets = new Insets(0, 0, 5, 0);
				gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cityTextField.gridx = 5;
				gbc_cityTextField.gridy = 1;
				panel.add(cityTextField, gbc_cityTextField);
				cityTextField.setColumns(10);
			}
			{
				JLabel emailLabel = new JLabel("Email:");
				GridBagConstraints gbc_emailLabel = new GridBagConstraints();
				gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
				gbc_emailLabel.gridx = 0;
				gbc_emailLabel.gridy = 2;
				panel.add(emailLabel, gbc_emailLabel);
			}
			{
				emailTextField = new JTextField();
				GridBagConstraints gbc_emailTextField = new GridBagConstraints();
				gbc_emailTextField.gridwidth = 3;
				gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
				gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_emailTextField.gridx = 1;
				gbc_emailTextField.gridy = 2;
				panel.add(emailTextField, gbc_emailTextField);
				emailTextField.setColumns(10);
			}
			{
				JLabel phoneNumberLabel = new JLabel("Phone Number:");
				GridBagConstraints gbc_phoneNumberLabel = new GridBagConstraints();
				gbc_phoneNumberLabel.anchor = GridBagConstraints.EAST;
				gbc_phoneNumberLabel.insets = new Insets(0, 0, 5, 5);
				gbc_phoneNumberLabel.gridx = 0;
				gbc_phoneNumberLabel.gridy = 3;
				panel.add(phoneNumberLabel, gbc_phoneNumberLabel);
			}
			{
				phoneNumberTextField = new HintTextField("+###########");
				GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
				gbc_phoneNumberTextField.gridwidth = 3;
				gbc_phoneNumberTextField.insets = new Insets(0, 0, 5, 5);
				gbc_phoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_phoneNumberTextField.gridx = 1;
				gbc_phoneNumberTextField.gridy = 3;
				panel.add(phoneNumberTextField, gbc_phoneNumberTextField);
				phoneNumberTextField.setColumns(10);
			}
			{
				JLabel zipcodeLabel = new JLabel("Zipcode:");
				GridBagConstraints gbc_zipcodeLabel = new GridBagConstraints();
				gbc_zipcodeLabel.anchor = GridBagConstraints.EAST;
				gbc_zipcodeLabel.insets = new Insets(0, 0, 0, 5);
				gbc_zipcodeLabel.gridx = 0;
				gbc_zipcodeLabel.gridy = 4;
				panel.add(zipcodeLabel, gbc_zipcodeLabel);
			}
			{
				zipcodeTextField = new JTextField();
				GridBagConstraints gbc_zipcodeTextField = new GridBagConstraints();
				gbc_zipcodeTextField.gridwidth = 3;
				gbc_zipcodeTextField.insets = new Insets(0, 0, 0, 5);
				gbc_zipcodeTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_zipcodeTextField.gridx = 1;
				gbc_zipcodeTextField.gridy = 4;
				panel.add(zipcodeTextField, gbc_zipcodeTextField);
				zipcodeTextField.setColumns(10);
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
	}

	private void confirm() {
		try {
			if (regexChecker()) {
				customerController.createCustomer(customerNameTextField.getText(), streetTextField.getText(),
						zipcodeTextField.getText(), cityTextField.getText(), CUITextField.getText(),
						phoneNumberTextField.getText(), emailTextField.getText());
				synchronizationController.set();
				this.dispose();
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
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

		if (RegexEnum.LETTERANDNUMBER.isMatch(customerNameTextField.getText())) {
			customerNameTextField.setBorder(defaultBorder);
			res++;
		} else {
			customerNameTextField.setBorder(new LineBorder(Color.RED, 2));
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

		if (RegexEnum.LETTERANDNUMBER.isMatch(CUITextField.getText())) {
			CUITextField.setBorder(defaultBorder);
			res++;
		} else {
			CUITextField.setBorder(new LineBorder(Color.RED, 2));
		}

		return res > 6;
	}

	private void goBack() {
		this.dispose();
	}

}
