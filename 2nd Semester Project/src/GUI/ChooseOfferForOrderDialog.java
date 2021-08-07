package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import controller.DataAccessException;
import controller.OfferController;
import model.Offer;

@SuppressWarnings("serial")
public class ChooseOfferForOrderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JList<Offer> offersList;
	private OfferController offerController;
	private SynchronizationController synchronizationController;

	/**
	 * Create the dialog.
	 */
	public ChooseOfferForOrderDialog(SynchronizationController synchronizationController) {
		try {
			offerController = new OfferController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Choose an offer to continue!");
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "name_2903756751209100");
			{
				offersList = new JList<Offer>();
				offersList.setCellRenderer(new OfferListCellRenderer());
				offersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(offersList);
			}
			{
				JPanel labelPanel = new JPanel();
				scrollPane.setColumnHeaderView(labelPanel);
				GridBagLayout gbl_labelPanel = new GridBagLayout();
				gbl_labelPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
				gbl_labelPanel.rowHeights = new int[] { 0, 0 };
				gbl_labelPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				gbl_labelPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
				labelPanel.setLayout(gbl_labelPanel);
				{
					JLabel offerNumberLabel = new JLabel("Offer Nr |");
					GridBagConstraints gbc_offerNumberLabel = new GridBagConstraints();
					gbc_offerNumberLabel.insets = new Insets(0, 0, 0, 5);
					gbc_offerNumberLabel.gridx = 0;
					gbc_offerNumberLabel.gridy = 0;
					labelPanel.add(offerNumberLabel, gbc_offerNumberLabel);
				}
				{
					JLabel totalPriceLabel = new JLabel("Total Price |");
					GridBagConstraints gbc_totalPriceLabel = new GridBagConstraints();
					gbc_totalPriceLabel.insets = new Insets(0, 0, 0, 5);
					gbc_totalPriceLabel.gridx = 1;
					gbc_totalPriceLabel.gridy = 0;
					labelPanel.add(totalPriceLabel, gbc_totalPriceLabel);
				}
				{
					JLabel materialPriceLabel = new JLabel("Material Price |");
					GridBagConstraints gbc_materialPriceLabel = new GridBagConstraints();
					gbc_materialPriceLabel.insets = new Insets(0, 0, 0, 5);
					gbc_materialPriceLabel.gridx = 2;
					gbc_materialPriceLabel.gridy = 0;
					labelPanel.add(materialPriceLabel, gbc_materialPriceLabel);
				}
				{
					JLabel dueDateLabel = new JLabel("Due Date");
					GridBagConstraints gbc_dueDateLabel = new GridBagConstraints();
					gbc_dueDateLabel.gridx = 3;
					gbc_dueDateLabel.gridy = 0;
					labelPanel.add(dueDateLabel, gbc_dueDateLabel);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] { 290, 74, 70, 0 };
			gbl_buttonPane.rowHeights = new int[] { 20, 0 };
			gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton goBackButton = new JButton("Go Back");
				goBackButton.addActionListener(e -> goBack());

				goBackButton.setActionCommand("Cancel");
				GridBagConstraints gbc_goBackButton = new GridBagConstraints();
				gbc_goBackButton.ipady = 10;
				gbc_goBackButton.ipadx = 10;
				gbc_goBackButton.insets = new Insets(0, 0, 0, 5);
				gbc_goBackButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_goBackButton.gridx = 0;
				gbc_goBackButton.gridy = 0;
				buttonPane.add(goBackButton, gbc_goBackButton);
			}
			{
				JButton continueButton = new JButton("Continue");
				continueButton.addActionListener(e -> confirm(offersList.getSelectedValue()));

				continueButton.setActionCommand("OK");
				GridBagConstraints gbc_continueButton = new GridBagConstraints();
				gbc_continueButton.ipady = 10;
				gbc_continueButton.ipadx = 10;
				gbc_continueButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_continueButton.gridx = 2;
				gbc_continueButton.gridy = 0;
				buttonPane.add(continueButton, gbc_continueButton);
				getRootPane().setDefaultButton(continueButton);
			}
		}

		try {
			new GetOfferListWorker().execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	private void confirm(Offer offer) {
		CreateSaleOrderDialog createSaleOrderDialog = new CreateSaleOrderDialog(offer, synchronizationController);
		createSaleOrderDialog.setVisible(true);
		this.dispose();
	}

	private void goBack() {
		this.dispose();
	}

	class GetOfferListWorker extends SwingWorker<DefaultListModel<Offer>, Void> {

		public GetOfferListWorker() {
		}

		@Override
		protected DefaultListModel<Offer> doInBackground() throws Exception {
			DefaultListModel<Offer> listRepresentation = new DefaultListModel<>();
			List<Offer> modelList = new ArrayList<Offer>();
			modelList = offerController.getAllOffers();

			for (Offer o : modelList) {
				listRepresentation.addElement(o);
			}
			return listRepresentation;
		}

		protected void done() {
			try {
				offersList.setModel(get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
