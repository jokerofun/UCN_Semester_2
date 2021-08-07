package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.DataAccessException;
import controller.ProcedureController;
import model.Procedure;

@SuppressWarnings("serial")
public class UpdateProcedureDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ProcedureController procedureController;
	private SynchronizationController synchronizationController;
	private JTextField procedureNameTextField;
	private JTextField pricePerHourTextField;
	private JTextField employeeTypeRequiredTextField;

	/**
	 * Create the dialog.
	 */
	public UpdateProcedureDialog(Procedure procedure, SynchronizationController synchronizationController) {
		try {
			procedureController = new ProcedureController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Procedure Edit Menu - Procedure name: " + procedure.getProcedureName());
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
			gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel procedureNameLabel = new JLabel("Procedure Name:");
				GridBagConstraints gbc_procedureNameLabel = new GridBagConstraints();
				gbc_procedureNameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_procedureNameLabel.gridx = 0;
				gbc_procedureNameLabel.gridy = 0;
				panel.add(procedureNameLabel, gbc_procedureNameLabel);
			}
			{
				procedureNameTextField = new JTextField("" + procedure.getProcedureName());
				GridBagConstraints gbc_procedureNameTextField = new GridBagConstraints();
				gbc_procedureNameTextField.gridwidth = 3;
				gbc_procedureNameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_procedureNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_procedureNameTextField.gridx = 1;
				gbc_procedureNameTextField.gridy = 0;
				panel.add(procedureNameTextField, gbc_procedureNameTextField);
				procedureNameTextField.setColumns(10);
			}
			{
				JLabel pricePerHourLabel = new JLabel("Price/Hour:");
				GridBagConstraints gbc_pricePerHourLabel = new GridBagConstraints();
				gbc_pricePerHourLabel.insets = new Insets(0, 0, 5, 5);
				gbc_pricePerHourLabel.gridx = 0;
				gbc_pricePerHourLabel.gridy = 1;
				panel.add(pricePerHourLabel, gbc_pricePerHourLabel);
			}
			{
				pricePerHourTextField = new JTextField("" + procedure.getPricePerHour());
				GridBagConstraints gbc_pricePerHourTextField = new GridBagConstraints();
				gbc_pricePerHourTextField.gridwidth = 3;
				gbc_pricePerHourTextField.insets = new Insets(0, 0, 5, 5);
				gbc_pricePerHourTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_pricePerHourTextField.gridx = 1;
				gbc_pricePerHourTextField.gridy = 1;
				panel.add(pricePerHourTextField, gbc_pricePerHourTextField);
				pricePerHourTextField.setColumns(10);
			}
			{
				JLabel employeeTypeRequiredLabel = new JLabel("Worker:");
				GridBagConstraints gbc_employeeTypeRequiredLabel = new GridBagConstraints();
				gbc_employeeTypeRequiredLabel.insets = new Insets(0, 0, 0, 5);
				gbc_employeeTypeRequiredLabel.gridx = 0;
				gbc_employeeTypeRequiredLabel.gridy = 2;
				panel.add(employeeTypeRequiredLabel, gbc_employeeTypeRequiredLabel);
			}
			{
				employeeTypeRequiredTextField = new JTextField("" + procedure.getEmployeeTypeRequired());
				GridBagConstraints gbc_employeeTypeRequiredTextField = new GridBagConstraints();
				gbc_employeeTypeRequiredTextField.gridwidth = 3;
				gbc_employeeTypeRequiredTextField.insets = new Insets(0, 0, 0, 5);
				gbc_employeeTypeRequiredTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_employeeTypeRequiredTextField.gridx = 1;
				gbc_employeeTypeRequiredTextField.gridy = 2;
				panel.add(employeeTypeRequiredTextField, gbc_employeeTypeRequiredTextField);
				employeeTypeRequiredTextField.setColumns(10);
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
				confirmButton.addActionListener(e -> confirm(procedure.getCode()));

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

	private void confirm(int code) {
		try {
			if (regexChecker()) {
				procedureController.updateProcedure(procedureNameTextField.getText(),
						BigDecimal.valueOf(Double.parseDouble(pricePerHourTextField.getText())), code,
						employeeTypeRequiredTextField.getText());
				synchronizationController.set();
			}
			this.dispose();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	private void goBack() {
		this.dispose();
	}

	private boolean regexChecker() {
		int res = 0;
		Border defaultBorder = UIManager.getBorder("TextField.border");

		if (RegexEnum.LETTERONLY.isMatch(procedureNameTextField.getText())) {
			procedureNameTextField.setBorder(defaultBorder);
			res++;
		} else {
			procedureNameTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.LETTERONLY.isMatch(employeeTypeRequiredTextField.getText())) {
			employeeTypeRequiredTextField.setBorder(defaultBorder);
			res++;
		} else {
			employeeTypeRequiredTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.PRICE.isMatch(pricePerHourTextField.getText())) {
			pricePerHourTextField.setBorder(defaultBorder);
			res++;
		} else {
			pricePerHourTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		return res > 2;
	}

}
