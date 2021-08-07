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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.WarehouseController;

public class CreateProduct extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField barcodeTextField;
	private JTextField descriptionTextField;
	private JTextField priceTextField;
	private JTextField amountTextField;
	private JTextField minimalAmountTextField;
	private JTextField maximalAmountTextField;
	private JLabel errorLabel = new JLabel("Hello!");
	private WarehouseController warehouseController = new WarehouseController();
	private JComboBox<String> comboBoxWarehouses = new JComboBox<String>(warehouseController.getWarehousesNames());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateProduct dialog = new CreateProduct();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateProduct() {
		this.setTitle("Add Product");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblWarehouse = new JLabel("Warehouse:");
			GridBagConstraints gbc_lblWarehouse = new GridBagConstraints();
			gbc_lblWarehouse.insets = new Insets(0, 0, 5, 5);
			gbc_lblWarehouse.anchor = GridBagConstraints.EAST;
			gbc_lblWarehouse.gridx = 1;
			gbc_lblWarehouse.gridy = 1;
			contentPanel.add(lblWarehouse, gbc_lblWarehouse);
		}
		{
			GridBagConstraints gbc_comboBoxWarehouses = new GridBagConstraints();
			gbc_comboBoxWarehouses.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxWarehouses.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxWarehouses.gridx = 2;
			gbc_comboBoxWarehouses.gridy = 1;
			contentPanel.add(comboBoxWarehouses, gbc_comboBoxWarehouses);
		}
		{
			JLabel lblBarCode = new JLabel("Bar code:");
			GridBagConstraints gbc_lblBarCode = new GridBagConstraints();
			gbc_lblBarCode.anchor = GridBagConstraints.EAST;
			gbc_lblBarCode.insets = new Insets(0, 0, 5, 5);
			gbc_lblBarCode.gridx = 1;
			gbc_lblBarCode.gridy = 2;
			contentPanel.add(lblBarCode, gbc_lblBarCode);
		}
		{
			barcodeTextField = new JTextField();
			GridBagConstraints gbc_barcodeTextField = new GridBagConstraints();
			gbc_barcodeTextField.gridwidth = 4;
			gbc_barcodeTextField.insets = new Insets(0, 0, 5, 5);
			gbc_barcodeTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_barcodeTextField.gridx = 2;
			gbc_barcodeTextField.gridy = 2;
			contentPanel.add(barcodeTextField, gbc_barcodeTextField);
			barcodeTextField.setColumns(10);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			GridBagConstraints gbc_lblDescription = new GridBagConstraints();
			gbc_lblDescription.anchor = GridBagConstraints.EAST;
			gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescription.gridx = 1;
			gbc_lblDescription.gridy = 3;
			contentPanel.add(lblDescription, gbc_lblDescription);
		}
		{
			descriptionTextField = new JTextField();
			GridBagConstraints gbc_descriptionTextField = new GridBagConstraints();
			gbc_descriptionTextField.gridwidth = 4;
			gbc_descriptionTextField.insets = new Insets(0, 0, 5, 5);
			gbc_descriptionTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_descriptionTextField.gridx = 2;
			gbc_descriptionTextField.gridy = 3;
			contentPanel.add(descriptionTextField, gbc_descriptionTextField);
			descriptionTextField.setColumns(10);
		}
		{
			JLabel lblPrice = new JLabel("Price:");
			GridBagConstraints gbc_lblPrice = new GridBagConstraints();
			gbc_lblPrice.anchor = GridBagConstraints.EAST;
			gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrice.gridx = 1;
			gbc_lblPrice.gridy = 4;
			contentPanel.add(lblPrice, gbc_lblPrice);
		}
		{
			priceTextField = new JTextField();
			GridBagConstraints gbc_priceTextField = new GridBagConstraints();
			gbc_priceTextField.gridwidth = 4;
			gbc_priceTextField.insets = new Insets(0, 0, 5, 5);
			gbc_priceTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_priceTextField.gridx = 2;
			gbc_priceTextField.gridy = 4;
			contentPanel.add(priceTextField, gbc_priceTextField);
			priceTextField.setColumns(10);
		}
		{
			JLabel lblAmount = new JLabel("Amount");
			GridBagConstraints gbc_lblAmount = new GridBagConstraints();
			gbc_lblAmount.anchor = GridBagConstraints.EAST;
			gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
			gbc_lblAmount.gridx = 1;
			gbc_lblAmount.gridy = 5;
			contentPanel.add(lblAmount, gbc_lblAmount);
		}
		{
			amountTextField = new JTextField();
			GridBagConstraints gbc_amountTextField = new GridBagConstraints();
			gbc_amountTextField.gridwidth = 4;
			gbc_amountTextField.insets = new Insets(0, 0, 5, 5);
			gbc_amountTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_amountTextField.gridx = 2;
			gbc_amountTextField.gridy = 5;
			contentPanel.add(amountTextField, gbc_amountTextField);
			amountTextField.setColumns(10);
		}
		{
			JLabel lblMinimaleAmount = new JLabel("Minimale Amount:");
			GridBagConstraints gbc_lblMinimaleAmount = new GridBagConstraints();
			gbc_lblMinimaleAmount.anchor = GridBagConstraints.EAST;
			gbc_lblMinimaleAmount.insets = new Insets(0, 0, 5, 5);
			gbc_lblMinimaleAmount.gridx = 1;
			gbc_lblMinimaleAmount.gridy = 6;
			contentPanel.add(lblMinimaleAmount, gbc_lblMinimaleAmount);
		}
		{
			minimalAmountTextField = new JTextField();
			GridBagConstraints gbc_minimalAmountTextField = new GridBagConstraints();
			gbc_minimalAmountTextField.gridwidth = 4;
			gbc_minimalAmountTextField.insets = new Insets(0, 0, 5, 5);
			gbc_minimalAmountTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_minimalAmountTextField.gridx = 2;
			gbc_minimalAmountTextField.gridy = 6;
			contentPanel.add(minimalAmountTextField, gbc_minimalAmountTextField);
			minimalAmountTextField.setColumns(10);
		}
		{
			JLabel lblMaximialAmount = new JLabel("Maximial Amount:");
			GridBagConstraints gbc_lblMaximialAmount = new GridBagConstraints();
			gbc_lblMaximialAmount.anchor = GridBagConstraints.EAST;
			gbc_lblMaximialAmount.insets = new Insets(0, 0, 5, 5);
			gbc_lblMaximialAmount.gridx = 1;
			gbc_lblMaximialAmount.gridy = 7;
			contentPanel.add(lblMaximialAmount, gbc_lblMaximialAmount);
		}
		{
			maximalAmountTextField = new JTextField();
			GridBagConstraints gbc_maximalAmountTextField = new GridBagConstraints();
			gbc_maximalAmountTextField.gridwidth = 4;
			gbc_maximalAmountTextField.insets = new Insets(0, 0, 5, 5);
			gbc_maximalAmountTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_maximalAmountTextField.gridx = 2;
			gbc_maximalAmountTextField.gridy = 7;
			contentPanel.add(maximalAmountTextField, gbc_maximalAmountTextField);
			maximalAmountTextField.setColumns(10);
		}
		{
			errorLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			errorLabel.setForeground(Color.RED);
			errorLabel.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 11));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.gridheight = 2;
			gbc_label.gridwidth = 7;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 8;
			contentPanel.add(errorLabel, gbc_label);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] { 316, 63, 47, 0 };
			gbl_buttonPane.rowHeights = new int[] { 21, 0 };
			gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
				gbc_cancelButton.gridx = 0;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
				cancelButton.setActionCommand("Cancel");
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addProduct();
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
	}

	private void cancel() {
		this.dispose();
	}

	private void addProduct() {
		try {
			if (check()) {
				double price = Double.parseDouble(priceTextField.getText());
				int amount = Integer.parseInt(amountTextField.getText());
				int minAmount = Integer.parseInt(minimalAmountTextField.getText());
				int maxAmount = Integer.parseInt(maximalAmountTextField.getText());
				if (secondCheck(price, amount, minAmount, maxAmount)) {
					warehouseController.addSellableProduct(barcodeTextField.getText(), descriptionTextField.getText(),
							price, amount, minAmount, maxAmount, false);
					errorLabel.setText("Product created successfully.");
				}
			}
		} catch (NumberFormatException e) {
			errorLabel.setText("Price, amount, maximal and minimal amount must only consist of number");
			// TODO error label not visible
		}

	}

	private boolean check() {
		boolean good = true;
		warehouseController.toWarehouse((String) comboBoxWarehouses.getSelectedItem());
		if (barcodeTextField.getText().equals("") || descriptionTextField.getText().equals("")
				|| priceTextField.getText().equals("") || amountTextField.getText().equals("")
				|| minimalAmountTextField.getText().equals("") || maximalAmountTextField.getText().equals("")) {
			errorLabel.setText("Please fill all the fields!");
			good = false;
		} else if (warehouseController.existSellableProduct(barcodeTextField.getText()) != null) {
			errorLabel.setText("Product with this Barcode already exists!");
			good = false;
		}
		return good;

	}

	private boolean secondCheck(double price, int amount, int minAmount, int maxAmount) {
		boolean good = true;
		if (price < 0 || amount < 0 || minAmount < 0 || maxAmount < 0) {
			errorLabel.setText("The numbers must be positive!");
			good = false;
		} else if (minAmount > maxAmount) {
			errorLabel.setText("Maximal amount must be greater than minimal amount!");
			good = false;
		}
		// TODO amount < maxAmount ????
		return good;
	}
}
