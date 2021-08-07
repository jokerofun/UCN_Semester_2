package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.border.EmptyBorder;

import Model.Warehouse;
import Model.WarehouseContainer;

public class EditWarehouseDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField address;
	private JTextField capacity;
	private WarehouseContainer container = WarehouseContainer.getWarehouseContainer();
	private Warehouse warehouse;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			EditWarehouseDialog dialog = new EditWarehouseDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public EditWarehouseDialog(int index) {
		this.warehouse = container.getWarehouses().get(index);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel oldName = new JLabel(warehouse.getName());
			GridBagConstraints gbc_oldName = new GridBagConstraints();
			gbc_oldName.insets = new Insets(0, 0, 5, 5);
			gbc_oldName.gridx = 1;
			gbc_oldName.gridy = 1;
			contentPanel.add(oldName, gbc_oldName);
		}
		{
			JLabel newName = new JLabel("New Name:");
			GridBagConstraints gbc_newName = new GridBagConstraints();
			gbc_newName.anchor = GridBagConstraints.EAST;
			gbc_newName.insets = new Insets(0, 0, 5, 5);
			gbc_newName.gridx = 2;
			gbc_newName.gridy = 1;
			contentPanel.add(newName, gbc_newName);
		}
		{
			name = new JTextField();
			GridBagConstraints gbc_name = new GridBagConstraints();
			gbc_name.insets = new Insets(0, 0, 5, 0);
			gbc_name.fill = GridBagConstraints.HORIZONTAL;
			gbc_name.gridx = 3;
			gbc_name.gridy = 1;
			contentPanel.add(name, gbc_name);
			name.setColumns(10);
		}
		{
			JLabel oldAddress = new JLabel(warehouse.getAddress());
			GridBagConstraints gbc_oldAddress = new GridBagConstraints();
			gbc_oldAddress.insets = new Insets(0, 0, 5, 5);
			gbc_oldAddress.gridx = 1;
			gbc_oldAddress.gridy = 2;
			contentPanel.add(oldAddress, gbc_oldAddress);
		}
		{
			JLabel newAddress = new JLabel("New Address:");
			GridBagConstraints gbc_newAddress = new GridBagConstraints();
			gbc_newAddress.anchor = GridBagConstraints.EAST;
			gbc_newAddress.insets = new Insets(0, 0, 5, 5);
			gbc_newAddress.gridx = 2;
			gbc_newAddress.gridy = 2;
			contentPanel.add(newAddress, gbc_newAddress);
		}
		{
			address = new JTextField();
			GridBagConstraints gbc_address = new GridBagConstraints();
			gbc_address.insets = new Insets(0, 0, 5, 0);
			gbc_address.fill = GridBagConstraints.HORIZONTAL;
			gbc_address.gridx = 3;
			gbc_address.gridy = 2;
			contentPanel.add(address, gbc_address);
			address.setColumns(10);
		}
		{
			JLabel oldCapacity = new JLabel(warehouse.getCapacity());
			GridBagConstraints gbc_oldCapacity = new GridBagConstraints();
			gbc_oldCapacity.insets = new Insets(0, 0, 0, 5);
			gbc_oldCapacity.gridx = 1;
			gbc_oldCapacity.gridy = 3;
			contentPanel.add(oldCapacity, gbc_oldCapacity);
		}
		{
			JLabel newCapacity = new JLabel("New Capacity:");
			GridBagConstraints gbc_newCapacity = new GridBagConstraints();
			gbc_newCapacity.anchor = GridBagConstraints.EAST;
			gbc_newCapacity.insets = new Insets(0, 0, 0, 5);
			gbc_newCapacity.gridx = 2;
			gbc_newCapacity.gridy = 3;
			contentPanel.add(newCapacity, gbc_newCapacity);
		}
		{
			capacity = new JTextField();
			GridBagConstraints gbc_capacity = new GridBagConstraints();
			gbc_capacity.fill = GridBagConstraints.HORIZONTAL;
			gbc_capacity.gridx = 3;
			gbc_capacity.gridy = 3;
			contentPanel.add(capacity, gbc_capacity);
			capacity.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						edit();
						close();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
			}
		}
	}

	private void edit() {
		if (!name.getText().isEmpty()) {
			warehouse.setName(name.getText());
		}
		if (!address.getText().isEmpty()) {
			warehouse.setAddress(address.getText());
		}
		if (!capacity.getText().isEmpty()) {
			warehouse.setCapacity(capacity.getText());
		}
	}

	private void close() {
		EditWarehouse w = new EditWarehouse();
		w.setVisible(true);
		// EditWarehouse.ref2();
		this.dispose();
	}

}
