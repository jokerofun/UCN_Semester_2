package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.WarehouseController;

public class MoveProduct extends JDialog {

	private WarehouseController warehouseController = new WarehouseController();
	private JComboBox<String> comboBoxFrom = new JComboBox<String>(warehouseController.getWarehousesNames());
	private JComboBox<String> comboBoxTo = new JComboBox<String>(warehouseController.getWarehousesNames());
	private JLabel errorLabel = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MoveProduct dialog = new MoveProduct();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MoveProduct() {
		this.setTitle("Move Product");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 242, 436, 21);
			getContentPane().add(buttonPane);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] { 316, 63, 47, 0 };
			gbl_buttonPane.rowHeights = new int[] { 21, 0 };
			gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
				gbc_cancelButton.gridx = 0;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createTransferList();
					}
				});
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_okButton.gridx = 2;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

		JLabel lblWarehouseFrom = new JLabel("Warehouse from:");
		lblWarehouseFrom.setBounds(50, 64, 108, 21);
		getContentPane().add(lblWarehouseFrom);

		JLabel lblWarehouseTo = new JLabel("Warehouse to:");
		lblWarehouseTo.setBounds(50, 145, 108, 21);
		getContentPane().add(lblWarehouseTo);

		comboBoxFrom.setBounds(202, 64, 117, 21);
		getContentPane().add(comboBoxFrom);

		comboBoxTo.setBounds(202, 145, 117, 21);
		getContentPane().add(comboBoxTo);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);

		errorLabel.setBounds(0, 202, 436, 13);
		getContentPane().add(errorLabel);
	}

	private void cancel() {
		this.dispose();
	}

	private void createTransferList() {
		if (comboBoxFrom.getSelectedItem() != comboBoxTo.getSelectedItem()) {
			TransferList transferList = new TransferList((String) comboBoxFrom.getSelectedItem(),
					(String) comboBoxTo.getSelectedItem());
			transferList.setVisible(true);
			this.dispose();
		} else {
			errorLabel.setText("You have to select two different warehouses!");
		}
	}
}
