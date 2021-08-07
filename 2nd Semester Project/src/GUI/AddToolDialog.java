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

import controller.DataAccessException;
import controller.ToolController;

public class AddToolDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	// private ProductContainer productContainer =
	// ProductContainer.getProductContainer();
	private ToolController ToolController = new ToolController();
	private JTextField type;
	private JTextField length;
	private JTextField diameter;
	private JTextField wear;
	private JTextField quantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddToolDialog dialog = new AddToolDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddToolDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 16, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Name");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 3;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			name = new JTextField();
			GridBagConstraints gbc_name = new GridBagConstraints();
			gbc_name.insets = new Insets(0, 0, 5, 0);
			gbc_name.fill = GridBagConstraints.HORIZONTAL;
			gbc_name.gridx = 4;
			gbc_name.gridy = 0;
			contentPanel.add(name, gbc_name);
			name.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Type");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.gridx = 3;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			type = new JTextField();
			GridBagConstraints gbc_type = new GridBagConstraints();
			gbc_type.insets = new Insets(0, 0, 5, 0);
			gbc_type.fill = GridBagConstraints.HORIZONTAL;
			gbc_type.gridx = 4;
			gbc_type.gridy = 1;
			contentPanel.add(type, gbc_type);
			type.setColumns(10);
		}
		{
			JLabel length = new JLabel("Length");
			GridBagConstraints gbc_length = new GridBagConstraints();
			gbc_length.anchor = GridBagConstraints.NORTHEAST;
			gbc_length.insets = new Insets(0, 0, 5, 5);
			gbc_length.gridx = 3;
			gbc_length.gridy = 2;
			contentPanel.add(length, gbc_length);
		}
		{
			length = new JTextField();
			GridBagConstraints gbc_length = new GridBagConstraints();
			gbc_length.insets = new Insets(0, 0, 5, 0);
			gbc_length.fill = GridBagConstraints.HORIZONTAL;
			gbc_length.gridx = 4;
			gbc_length.gridy = 2;
			contentPanel.add(length, gbc_length);
			length.setColumns(10);
		}
		{
			JLabel diameter = new JLabel("Diameter");
			GridBagConstraints gbc_diameter = new GridBagConstraints();
			gbc_diameter.anchor = GridBagConstraints.EAST;
			gbc_diameter.insets = new Insets(0, 0, 5, 5);
			gbc_diameter.gridx = 3;
			gbc_diameter.gridy = 3;
			contentPanel.add(diameter, gbc_diameter);
		}
		{
			diameter = new JTextField();
			GridBagConstraints gbc_diameter = new GridBagConstraints();
			gbc_diameter.insets = new Insets(0, 0, 5, 0);
			gbc_diameter.fill = GridBagConstraints.HORIZONTAL;
			gbc_diameter.gridx = 4;
			gbc_diameter.gridy = 3;
			contentPanel.add(diameter, gbc_diameter);
			diameter.setColumns(10);
		}
		{
			JLabel wear = new JLabel("Wear");
			GridBagConstraints gbc_wear = new GridBagConstraints();
			gbc_wear.anchor = GridBagConstraints.EAST;
			gbc_wear.insets = new Insets(0, 0, 5, 5);
			gbc_wear.gridx = 3;
			gbc_wear.gridy = 4;
			contentPanel.add(wear, gbc_wear);
		}
		{
			wear = new JTextField();
			GridBagConstraints gbc_wear = new GridBagConstraints();
			gbc_wear.insets = new Insets(0, 0, 5, 0);
			gbc_wear.fill = GridBagConstraints.HORIZONTAL;
			gbc_wear.gridx = 4;
			gbc_wear.gridy = 4;
			contentPanel.add(wear, gbc_wear);
			wear.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Qty");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.gridx = 3;
			gbc_lblNewLabel_2.gridy = 5;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			quantity = new JTextField();
			GridBagConstraints gbc_quantity = new GridBagConstraints();
			gbc_quantity.fill = GridBagConstraints.HORIZONTAL;
			gbc_quantity.gridx = 4;
			gbc_quantity.gridy = 5;
			contentPanel.add(quantity, gbc_quantity);
			quantity.setColumns(10);
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
						addTool(name.getText(), type.getText(), Float.parseFloat(length.getText()),
								Float.parseFloat(diameter.getText()), Float.parseFloat(wear.getText()),
								Integer.parseInt(quantity.getText()));
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

	private void addTool(String name, String type, float length, float diameter, float wear, int inStockQuantity) {
		try {
			ToolController.createTool(name, type, length, diameter, wear, inStockQuantity);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		this.dispose();
	}

}
