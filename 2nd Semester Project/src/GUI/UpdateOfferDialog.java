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
import controller.OfferController;
import model.Offer;

@SuppressWarnings("serial")
public class UpdateOfferDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private OfferController offerController;
	private SynchronizationController synchronizationController;
	private JTextField dueDateTextField;
	private JTextField materialPriceTextField;
	private JTextField totalPriceTextField;

	/**
	 * Create the dialog.
	 */
	public UpdateOfferDialog(Offer offer, SynchronizationController synchronizationController) {
		try {
			offerController = new OfferController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Offer Edit Menu - Offer number: " + offer.getOfferNumber());
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
				dueDateTextField = new JTextField("" + offer.getDueDate());
				GridBagConstraints gbc_dueDateTextField = new GridBagConstraints();
				gbc_dueDateTextField.insets = new Insets(0, 0, 5, 0);
				gbc_dueDateTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_dueDateTextField.gridx = 4;
				gbc_dueDateTextField.gridy = 1;
				panel.add(dueDateTextField, gbc_dueDateTextField);
				dueDateTextField.setColumns(10);
			}
			{
				JLabel materialPriceLabel = new JLabel("Material Price:");
				GridBagConstraints gbc_materialPriceLabel = new GridBagConstraints();
				gbc_materialPriceLabel.gridwidth = 2;
				gbc_materialPriceLabel.insets = new Insets(0, 0, 5, 5);
				gbc_materialPriceLabel.gridx = 1;
				gbc_materialPriceLabel.gridy = 2;
				panel.add(materialPriceLabel, gbc_materialPriceLabel);
			}
			{
				materialPriceTextField = new JTextField("" + offer.getMaterialPrice());
				GridBagConstraints gbc_materialPriceTextField = new GridBagConstraints();
				gbc_materialPriceTextField.insets = new Insets(0, 0, 5, 0);
				gbc_materialPriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_materialPriceTextField.gridx = 4;
				gbc_materialPriceTextField.gridy = 2;
				panel.add(materialPriceTextField, gbc_materialPriceTextField);
				materialPriceTextField.setColumns(10);
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
				totalPriceTextField = new JTextField("" + offer.getTotalPrice());
				GridBagConstraints gbc_totalPriceTextField = new GridBagConstraints();
				gbc_totalPriceTextField.insets = new Insets(0, 0, 5, 0);
				gbc_totalPriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_totalPriceTextField.gridx = 4;
				gbc_totalPriceTextField.gridy = 3;
				panel.add(totalPriceTextField, gbc_totalPriceTextField);
				totalPriceTextField.setColumns(10);
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
					// TODO better way of doing this
					BigDecimal oldMaterialPrice = offer.getMaterialPrice();

					offer.setDueDate(Date.valueOf(dueDateTextField.getText()));
					offer.setMaterialPrice(BigDecimal.valueOf(Double.parseDouble(materialPriceTextField.getText())));
					offer.setTotalPrice(BigDecimal.valueOf(Double.parseDouble(totalPriceTextField.getText())));

					BigDecimal newMaterialPrice = offer.getMaterialPrice();

					if (newMaterialPrice.compareTo(oldMaterialPrice) > 0) {
						offer.setTotalPrice(offer.getTotalPrice().add(newMaterialPrice.subtract(oldMaterialPrice)));
					} else if (oldMaterialPrice.compareTo(newMaterialPrice) > 0) {
						offer.setTotalPrice(
								offer.getTotalPrice().subtract((oldMaterialPrice).subtract(newMaterialPrice)));
					}

					confirm(offer);
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

	private void confirm(Offer offer) {
		try {
			if (regexChecker()) {
				offerController.updateOffer(offer);
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

		if (RegexEnum.PRICE.isMatch(materialPriceTextField.getText())) {
			materialPriceTextField.setBorder(defaultBorder);
			res++;
		} else {
			materialPriceTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.PRICE.isMatch(totalPriceTextField.getText())) {
			totalPriceTextField.setBorder(defaultBorder);
			res++;
		} else {
			totalPriceTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		return res > 2;
	}

}
