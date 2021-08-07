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

import controller.DataAccessException;
import controller.PositionController;

@SuppressWarnings("serial")
public class CreatePositionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PositionController positionController;
	private JTextField positionNameTextField;
	private JTextField clearanceTextField;
	private SynchronizationController synchronizationController;

	/**
	 * Create the dialog.
	 */
	public CreatePositionDialog(SynchronizationController synchronizationController) {
		try {
			positionController = new PositionController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Create Position");
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
				JLabel positionNameLabel = new JLabel("Position Name:");
				GridBagConstraints gbc_positionNameLabel = new GridBagConstraints();
				gbc_positionNameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_positionNameLabel.gridx = 0;
				gbc_positionNameLabel.gridy = 0;
				panel.add(positionNameLabel, gbc_positionNameLabel);
			}
			{
				positionNameTextField = new JTextField();
				GridBagConstraints gbc_positionNameTextField = new GridBagConstraints();
				gbc_positionNameTextField.gridwidth = 3;
				gbc_positionNameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_positionNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_positionNameTextField.gridx = 1;
				gbc_positionNameTextField.gridy = 0;
				panel.add(positionNameTextField, gbc_positionNameTextField);
				positionNameTextField.setColumns(10);
			}
			{
				JLabel clearanceLabel = new JLabel("Worker:");
				GridBagConstraints gbc_clearanceLabel = new GridBagConstraints();
				gbc_clearanceLabel.insets = new Insets(0, 0, 0, 5);
				gbc_clearanceLabel.gridx = 0;
				gbc_clearanceLabel.gridy = 2;
				panel.add(clearanceLabel, gbc_clearanceLabel);
			}
			{
				clearanceTextField = new JTextField();
				GridBagConstraints gbc_clearanceTextField = new GridBagConstraints();
				gbc_clearanceTextField.gridwidth = 3;
				gbc_clearanceTextField.insets = new Insets(0, 0, 0, 5);
				gbc_clearanceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_clearanceTextField.gridx = 1;
				gbc_clearanceTextField.gridy = 2;
				panel.add(clearanceTextField, gbc_clearanceTextField);
				clearanceTextField.setColumns(10);
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
				positionController.createPosition(positionNameTextField.getText(), clearanceTextField.getText());
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

	private boolean regexChecker() {
		int res = 0;
		Border defaultBorder = UIManager.getBorder("TextField.border");

		if (RegexEnum.LETTERONLY.isMatch(positionNameTextField.getText())) {
			positionNameTextField.setBorder(defaultBorder);
			res++;
		} else {
			positionNameTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.LETTERONLY.isMatch(clearanceTextField.getText())) {
			clearanceTextField.setBorder(defaultBorder);
			res++;
		} else {
			clearanceTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		return res > 1;
	}

}
