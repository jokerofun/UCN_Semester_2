package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.sql.Date;

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
import controller.SaleOrderController;
import model.SaleOrder;

@SuppressWarnings("serial")
public class UpdateSaleOrderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SaleOrderController saleOrderController;
	private SynchronizationController synchronizationController;
	private JTextField dueDateTextField;
	private JTextField orderDateTextField;
	private JTextField totalPriceTextField;
	private JTextField customerNameTextField;

	/**
	 * Create the dialog.
	 */
	public UpdateSaleOrderDialog(SaleOrder saleOrder, SynchronizationController synchronizationController) {
		try {
			saleOrderController = new SaleOrderController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Sale Order Edit Menu - Sale Order number: " + saleOrder.getSaleOrderNumber());
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
			gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel dueDateLabel = new JLabel("Due Date:");
				GridBagConstraints gbc_dueDateLabel = new GridBagConstraints();
				gbc_dueDateLabel.gridwidth = 2;
				gbc_dueDateLabel.insets = new Insets(0, 0, 5, 5);
				gbc_dueDateLabel.gridx = 1;
				gbc_dueDateLabel.gridy = 1;
				panel.add(dueDateLabel, gbc_dueDateLabel);
			}
			{
				dueDateTextField = new JTextField("" + saleOrder.getDueDate());
				GridBagConstraints gbc_dueDateTextField = new GridBagConstraints();
				gbc_dueDateTextField.insets = new Insets(0, 0, 5, 0);
				gbc_dueDateTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_dueDateTextField.gridx = 4;
				gbc_dueDateTextField.gridy = 1;
				panel.add(dueDateTextField, gbc_dueDateTextField);
				dueDateTextField.setColumns(10);
			}
			{
				JLabel orderDateLabel = new JLabel("Order Date:");
				GridBagConstraints gbc_orderDateLabel = new GridBagConstraints();
				gbc_orderDateLabel.gridwidth = 2;
				gbc_orderDateLabel.insets = new Insets(0, 0, 5, 5);
				gbc_orderDateLabel.gridx = 1;
				gbc_orderDateLabel.gridy = 2;
				panel.add(orderDateLabel, gbc_orderDateLabel);
			}
			{
				orderDateTextField = new JTextField("" + saleOrder.getOrderDate());
				GridBagConstraints gbc_orderDateTextField = new GridBagConstraints();
				gbc_orderDateTextField.insets = new Insets(0, 0, 5, 0);
				gbc_orderDateTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_orderDateTextField.gridx = 4;
				gbc_orderDateTextField.gridy = 2;
				panel.add(orderDateTextField, gbc_orderDateTextField);
				orderDateTextField.setColumns(10);
			}
			{
				JLabel totalPriceLabel = new JLabel("Total Price:");
				GridBagConstraints gbc_totalPriceLabel = new GridBagConstraints();
				gbc_totalPriceLabel.gridwidth = 2;
				gbc_totalPriceLabel.insets = new Insets(0, 0, 5, 5);
				gbc_totalPriceLabel.gridx = 1;
				gbc_totalPriceLabel.gridy = 3;
				panel.add(totalPriceLabel, gbc_totalPriceLabel);
			}
			{
				totalPriceTextField = new JTextField("" + saleOrder.getTotalPrice());
				GridBagConstraints gbc_totalPriceTextField = new GridBagConstraints();
				gbc_totalPriceTextField.insets = new Insets(0, 0, 5, 0);
				gbc_totalPriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_totalPriceTextField.gridx = 4;
				gbc_totalPriceTextField.gridy = 3;
				panel.add(totalPriceTextField, gbc_totalPriceTextField);
				totalPriceTextField.setColumns(10);
			}
			{
				JLabel customerNameLabel = new JLabel("Customer Name:");
				GridBagConstraints gbc_customerNameLabel = new GridBagConstraints();
				gbc_customerNameLabel.gridwidth = 2;
				gbc_customerNameLabel.insets = new Insets(0, 0, 0, 5);
				gbc_customerNameLabel.gridx = 1;
				gbc_customerNameLabel.gridy = 4;
				panel.add(customerNameLabel, gbc_customerNameLabel);
			}
			{
				customerNameTextField = new JTextField("" + saleOrder.getCustomerName());
				GridBagConstraints gbc_customerNameTextField = new GridBagConstraints();
				gbc_customerNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_customerNameTextField.gridx = 4;
				gbc_customerNameTextField.gridy = 4;
				panel.add(customerNameTextField, gbc_customerNameTextField);
				customerNameTextField.setColumns(10);
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
				confirmButton.addActionListener(e -> {

					saleOrder.setDueDate(Date.valueOf(dueDateTextField.getText()));
					saleOrder.setOrderDate(Date.valueOf(orderDateTextField.getText()));
					saleOrder.setTotalPrice(BigDecimal.valueOf(Double.parseDouble(totalPriceTextField.getText())));
					saleOrder.setCustomerName(customerNameTextField.getText());

					confirm(saleOrder);
				});

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

	private void confirm(SaleOrder saleOrder) {
		try {
			if (regexChecker()) {
				saleOrderController.updateSaleOrder(saleOrder);
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

		if (RegexEnum.DATE.isMatch(dueDateTextField.getText())) {
			dueDateTextField.setBorder(defaultBorder);
			res++;
		} else {
			dueDateTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.DATE.isMatch(orderDateTextField.getText())) {
			orderDateTextField.setBorder(defaultBorder);
			res++;
		} else {
			orderDateTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.PRICE.isMatch(totalPriceTextField.getText())) {
			totalPriceTextField.setBorder(defaultBorder);
			res++;
		} else {
			totalPriceTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.LETTERANDNUMBER.isMatch(customerNameTextField.getText())) {
			customerNameTextField.setBorder(defaultBorder);
			res++;
		} else {
			customerNameTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		return res > 3;
	}

}
