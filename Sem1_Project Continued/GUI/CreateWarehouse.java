package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.WarehouseController;

public class CreateWarehouse extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField address;
	private JTextField capacity;
	private JLabel errorLabel = new JLabel("Hello!");
	private WarehouseController warehouseController = new WarehouseController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateWarehouse dialog = new CreateWarehouse();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateWarehouse() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel_3 = new JLabel("Create Warehouse");
			lblNewLabel_3.setFont(new Font("Viner Hand ITC", Font.BOLD, 15));
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.gridwidth = 2;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_3.gridx = 0;
			gbc_lblNewLabel_3.gridy = 0;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			JLabel lblNewLabel = new JLabel("Name:");
			lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			name = new JTextField();
			GridBagConstraints gbc_name = new GridBagConstraints();
			gbc_name.insets = new Insets(0, 0, 5, 0);
			gbc_name.fill = GridBagConstraints.HORIZONTAL;
			gbc_name.gridx = 1;
			gbc_name.gridy = 1;
			contentPanel.add(name, gbc_name);
			name.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Address:");
			lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 2;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			address = new JTextField();
			GridBagConstraints gbc_address = new GridBagConstraints();
			gbc_address.insets = new Insets(0, 0, 5, 0);
			gbc_address.fill = GridBagConstraints.HORIZONTAL;
			gbc_address.gridx = 1;
			gbc_address.gridy = 2;
			contentPanel.add(address, gbc_address);
			address.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Capacity:");
			lblNewLabel_2.setFont(new Font("Monospaced", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 3;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			capacity = new JTextField();
			GridBagConstraints gbc_capacity = new GridBagConstraints();
			gbc_capacity.insets = new Insets(0, 0, 5, 0);
			gbc_capacity.fill = GridBagConstraints.HORIZONTAL;
			gbc_capacity.gridx = 1;
			gbc_capacity.gridy = 3;
			contentPanel.add(capacity, gbc_capacity);
			capacity.setColumns(10);
		}
		{
			errorLabel.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 13));
			errorLabel.setForeground(new Color(255, 0, 0));

			errorLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			GridBagConstraints gbc_errorLabel = new GridBagConstraints();
			gbc_errorLabel.gridx = 1;
			gbc_errorLabel.gridy = 6;
			contentPanel.add(errorLabel, gbc_errorLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] { 316, 47, 63, 0 };
			gbl_buttonPane.rowHeights = new int[] { 21, 0 };
			gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			buttonPane.setLayout(gbl_buttonPane);
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBackground(new Color(173, 216, 230));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancel();
				}
			});
			cancelButton.setActionCommand("Cancel");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
			gbc_cancelButton.anchor = GridBagConstraints.NORTHWEST;
			gbc_cancelButton.gridx = 0;
			gbc_cancelButton.gridy = 0;
			buttonPane.add(cancelButton, gbc_cancelButton);
			{
				JButton okButton = new JButton("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.gridx = 2;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				okButton.setBackground(new Color(173, 216, 230));
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createWarehouse();
					}
				});
			}
		}
	}

	private void cancel() {
		EditWarehouse w = new EditWarehouse();
		w.setVisible(true);
		this.dispose();
	}

	private void createWarehouse() {
		if (name.getText().equals("") || address.getText().equals("") || capacity.getText().equals("")) {
			errorLabel.setText("Please fill all the fields!");
		} else {
			if (warehouseController.fromWarehouse(name.getText())) {
				errorLabel.setText("Warehouse already exists!");
			} else {
				warehouseController.addWarehouse(name.getText(), address.getText(), capacity.getText());
				EditWarehouse w = new EditWarehouse();
				w.setVisible(true);
				this.dispose();
			}
		}

	}

}
