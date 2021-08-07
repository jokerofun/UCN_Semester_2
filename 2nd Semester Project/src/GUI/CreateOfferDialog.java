package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.DataAccessException;
import controller.OfferController;
import controller.ProcedureController;
import model.OfferEntry;
import model.Procedure;

@SuppressWarnings("serial")
public class CreateOfferDialog extends JDialog {
	private JTextField productNameTextField;
	private JTextField quantityTextField;
	private JTextField materialPriceTextField;
	private JTextField hoursTextField;
	private JTextField dueDateTextField;
	private JList<Procedure> proceduresList;
	private JList<String> addedProceduresList;
	private JList<OfferEntry> addedProductsList;
	JTabbedPane tabbedPane;
	private ProcedureController procedureController;
	private OfferController offerController;
	SynchronizationController synchronizationController;
	private List<OfferEntry> offerEntriesList;
	private HashMap<Procedure, Integer> hash;

	/**
	 * Create the dialog.
	 */
	public CreateOfferDialog(SynchronizationController synchronizationController) {
		try {
			procedureController = new ProcedureController();
			offerController = new OfferController();
			this.synchronizationController = synchronizationController;
			offerEntriesList = new ArrayList<OfferEntry>();
			hash = new HashMap<Procedure, Integer>();
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Create Offer");
		setMinimumSize(new Dimension(710, 430));
		setBounds(100, 100, 710, 433);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gbl_bottomPanel.rowHeights = new int[] { 0, 0 };
		gbl_bottomPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_bottomPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		bottomPanel.setLayout(gbl_bottomPanel);
		{
			JButton goBackButton = new JButton("Go Back");
			goBackButton.addActionListener(e -> goBack());

			GridBagConstraints gbc_goBackButton = new GridBagConstraints();
			gbc_goBackButton.ipady = 10;
			gbc_goBackButton.ipadx = 10;
			gbc_goBackButton.insets = new Insets(0, 0, 0, 5);
			gbc_goBackButton.gridx = 2;
			gbc_goBackButton.gridy = 0;
			bottomPanel.add(goBackButton, gbc_goBackButton);
		}

		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(e -> {
			try {
				if (regexCheckerConfirmOffer()) {
					confirmOfferCreation(Date.valueOf(dueDateTextField.getText()),
							BigDecimal.valueOf(Double.parseDouble(materialPriceTextField.getText())),
							(ArrayList<OfferEntry>) offerEntriesList);
				}
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (DataAccessException e1) {
				e1.printStackTrace();
			}
		});
		{
			Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
			GridBagConstraints gbc_rigidArea = new GridBagConstraints();
			gbc_rigidArea.weightx = 1.0;
			gbc_rigidArea.gridwidth = 18;
			gbc_rigidArea.insets = new Insets(0, 0, 0, 5);
			gbc_rigidArea.gridx = 3;
			gbc_rigidArea.gridy = 0;
			bottomPanel.add(rigidArea, gbc_rigidArea);
		}

		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.ipady = 10;
		gbc_confirmButton.ipadx = 10;
		gbc_confirmButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmButton.gridx = 21;
		gbc_confirmButton.gridy = 0;
		bottomPanel.add(confirmButton, gbc_confirmButton);

		{
			JPanel leftPanel = new JPanel();
			getContentPane().add(leftPanel, BorderLayout.WEST);
			GridBagLayout gbl_leftPanel = new GridBagLayout();
			gbl_leftPanel.columnWidths = new int[] { 0, 0 };
			gbl_leftPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_leftPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			gbl_leftPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, Double.MIN_VALUE };
			leftPanel.setLayout(gbl_leftPanel);
			{
				JLabel productNameLabel = new JLabel("Product name:");
				GridBagConstraints gbc_productNameLabel = new GridBagConstraints();
				gbc_productNameLabel.insets = new Insets(0, 0, 5, 0);
				gbc_productNameLabel.gridx = 0;
				gbc_productNameLabel.gridy = 1;
				leftPanel.add(productNameLabel, gbc_productNameLabel);
			}
			{
				productNameTextField = new JTextField();
				GridBagConstraints gbc_productNameTextField = new GridBagConstraints();
				gbc_productNameTextField.insets = new Insets(0, 0, 5, 0);
				gbc_productNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_productNameTextField.gridx = 0;
				gbc_productNameTextField.gridy = 2;
				leftPanel.add(productNameTextField, gbc_productNameTextField);
				productNameTextField.setColumns(14);
			}
			{
				JLabel productQuantityLabel = new JLabel("Product quantity:");
				GridBagConstraints gbc_productQuantityLabel = new GridBagConstraints();
				gbc_productQuantityLabel.insets = new Insets(0, 0, 5, 0);
				gbc_productQuantityLabel.gridx = 0;
				gbc_productQuantityLabel.gridy = 4;
				leftPanel.add(productQuantityLabel, gbc_productQuantityLabel);
			}
			{
				quantityTextField = new JTextField();
				GridBagConstraints gbc_quantityTextField = new GridBagConstraints();
				gbc_quantityTextField.insets = new Insets(0, 0, 5, 0);
				gbc_quantityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_quantityTextField.gridx = 0;
				gbc_quantityTextField.gridy = 5;
				leftPanel.add(quantityTextField, gbc_quantityTextField);
				quantityTextField.setColumns(10);
			}
			{
				JLabel materialPriceLabel = new JLabel("Material price:");
				GridBagConstraints gbc_materialPriceLabel = new GridBagConstraints();
				gbc_materialPriceLabel.insets = new Insets(0, 0, 5, 0);
				gbc_materialPriceLabel.gridx = 0;
				gbc_materialPriceLabel.gridy = 7;
				leftPanel.add(materialPriceLabel, gbc_materialPriceLabel);
			}
			{
				materialPriceTextField = new JTextField();
				GridBagConstraints gbc_materialPriceTextField = new GridBagConstraints();
				gbc_materialPriceTextField.insets = new Insets(0, 0, 5, 0);
				gbc_materialPriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_materialPriceTextField.gridx = 0;
				gbc_materialPriceTextField.gridy = 8;
				leftPanel.add(materialPriceTextField, gbc_materialPriceTextField);
				materialPriceTextField.setColumns(10);
			}
			{
				JLabel dueDateLabel = new JLabel("Year-Month-Day");
				GridBagConstraints gbc_dueDateLabel = new GridBagConstraints();
				gbc_dueDateLabel.insets = new Insets(0, 0, 5, 0);
				gbc_dueDateLabel.gridx = 0;
				gbc_dueDateLabel.gridy = 10;
				leftPanel.add(dueDateLabel, gbc_dueDateLabel);
			}
			{

				dueDateTextField = new JTextField();
				GridBagConstraints gbc_dueDateTextField = new GridBagConstraints();
				gbc_dueDateTextField.insets = new Insets(0, 0, 5, 0);
				gbc_dueDateTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_dueDateTextField.gridx = 0;
				gbc_dueDateTextField.gridy = 11;
				leftPanel.add(dueDateTextField, gbc_dueDateTextField);
				dueDateTextField.setColumns(10);
			}
		}

		JPanel rightPanel = new JPanel();
		getContentPane().add(rightPanel, BorderLayout.EAST);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] { 114, 0 };
		gbl_rightPanel.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_rightPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_rightPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		rightPanel.setLayout(gbl_rightPanel);
		{
			JLabel hoursLabel = new JLabel("Hours:");
			GridBagConstraints gbc_hoursLabel = new GridBagConstraints();
			gbc_hoursLabel.insets = new Insets(0, 0, 5, 0);
			gbc_hoursLabel.gridx = 0;
			gbc_hoursLabel.gridy = 3;
			rightPanel.add(hoursLabel, gbc_hoursLabel);
		}
		{
			hoursTextField = new JTextField();
			GridBagConstraints gbc_hoursTextField = new GridBagConstraints();
			gbc_hoursTextField.insets = new Insets(0, 0, 5, 0);
			gbc_hoursTextField.anchor = GridBagConstraints.NORTHWEST;
			gbc_hoursTextField.gridx = 0;
			gbc_hoursTextField.gridy = 4;
			rightPanel.add(hoursTextField, gbc_hoursTextField);
			hoursTextField.setColumns(14);
		}

		JButton addProcedureButton = new JButton("Add Procedure");
		addProcedureButton.addActionListener(e -> addProcedure());

		GridBagConstraints gbc_addProcedureButton = new GridBagConstraints();
		gbc_addProcedureButton.insets = new Insets(0, 0, 5, 0);
		gbc_addProcedureButton.ipady = 30;
		gbc_addProcedureButton.ipadx = 10;
		gbc_addProcedureButton.gridx = 0;
		gbc_addProcedureButton.gridy = 5;
		rightPanel.add(addProcedureButton, gbc_addProcedureButton);

		JButton finishProductButton = new JButton("Finish Product");
		finishProductButton.addActionListener(e -> finishProduct());

		GridBagConstraints gbc_finishProductButton = new GridBagConstraints();
		gbc_finishProductButton.ipady = 30;
		gbc_finishProductButton.ipadx = 10;
		gbc_finishProductButton.gridx = 0;
		gbc_finishProductButton.gridy = 8;
		rightPanel.add(finishProductButton, gbc_finishProductButton);
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane, BorderLayout.CENTER);
			{
				JScrollPane procedureListScrollPane = new JScrollPane();
				tabbedPane.addTab("Procedures List", null, procedureListScrollPane, null);
				{
					proceduresList = new JList<Procedure>();
					proceduresList.setCellRenderer(new ProcedureListCellRenderer());
					procedureListScrollPane.setViewportView(proceduresList);
				}
			}
			{
				JScrollPane addedProceduresListScrollPane = new JScrollPane();
				tabbedPane.addTab("Added Procedures", null, addedProceduresListScrollPane, null);
				{
					addedProceduresList = new JList<String>();
					addedProceduresListScrollPane.setViewportView(addedProceduresList);
				}
			}
			{
				JScrollPane addedProductsListScrollPane = new JScrollPane();
				tabbedPane.addTab("Finished Products", null, addedProductsListScrollPane, null);
				{
					addedProductsList = new JList<OfferEntry>();
					addedProductsList.setCellRenderer(new OfferEntryListCellRenderer());
					addedProductsListScrollPane.setViewportView(addedProductsList);
				}
			}
		}

		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (hash.isEmpty()) {
					finishProductButton.setEnabled(false);
				} else {
					finishProductButton.setEnabled(true);
				}

				if (offerEntriesList.isEmpty()) {
					confirmButton.setEnabled(false);
				} else {
					confirmButton.setEnabled(true);
				}

				if (proceduresList.isSelectionEmpty() || tabbedPane.getSelectedIndex() != 0) {
					addProcedureButton.setEnabled(false);
				} else {
					addProcedureButton.setEnabled(true);
				}
			}
		});
		timer.setRepeats(true);
		timer.start();

		try {
			new AddedProceduresAndProductsListThread().start();
			new GetProcedureListWorker().execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void addedFillList() {
		DefaultListModel<String> addedProceduresListRepresentation = new DefaultListModel<>();
		DefaultListModel<OfferEntry> addedProductsListRepresentation = new DefaultListModel<>();

		for (Map.Entry<Procedure, Integer> entry : hash.entrySet()) {
			Procedure key = entry.getKey();
			Integer value = entry.getValue();
			addedProceduresListRepresentation.addElement("" + key.getCode() + " - " + key.getProcedureName() + " - "
					+ key.getPricePerHour() + " - " + key.getEmployeeTypeRequired() + " - " + value);
		}

		for (OfferEntry offerEntry : offerEntriesList) {
			addedProductsListRepresentation.addElement(offerEntry);
		}

		addedProceduresList.setModel(addedProceduresListRepresentation);
		addedProductsList.setModel(addedProductsListRepresentation);
	}

	private void addProcedure() {
		if (!proceduresList.isSelectionEmpty() && regexCheckerAddProcedure()) {
			hash.put(proceduresList.getSelectedValue(), Integer.parseInt(hoursTextField.getText()));

			proceduresList.clearSelection();
			hoursTextField.setText("");
			synchronizationController.set();
		}
	}

	private void finishProduct() {
		if (regexCheckerFinishProduct()) {
			OfferEntry offerEntry = new OfferEntry(productNameTextField.getText(),
					Integer.parseInt(quantityTextField.getText()));
			offerEntry.setProcedureHour(hash);
			offerEntriesList.add(offerEntry);

			proceduresList.clearSelection();
			productNameTextField.setText("");
			quantityTextField.setText("");
			hoursTextField.setText("");
			hash = new HashMap<Procedure, Integer>();
			synchronizationController.set();
		}
	}

	private void confirmOfferCreation(Date dueDate, BigDecimal materialPrice, ArrayList<OfferEntry> offerEntries)
			throws DataAccessException {

		offerController.createOffer(dueDate, materialPrice, offerEntries);
		synchronizationController.set();
		this.dispose();
	}

	private void goBack() {
		this.dispose();
	}

	class AddedProceduresAndProductsListThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronizationController.get();
				addedFillList();
			}
		}
	}

	class GetProcedureListWorker extends SwingWorker<DefaultListModel<Procedure>, Void> {
		int input = -1;

		public GetProcedureListWorker() {
		}

		@Override
		protected DefaultListModel<Procedure> doInBackground() throws Exception {
			DefaultListModel<Procedure> listRepresentation = new DefaultListModel<>();
			List<Procedure> modelList = new ArrayList<Procedure>();
			modelList = procedureController.getAllProcedures();

			for (Procedure p : modelList) {
				listRepresentation.addElement(p);
			}
			return listRepresentation;
		}

		protected void done() {
			try {
				proceduresList.setModel(get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean regexCheckerAddProcedure() {
		int res = 0;
		Border defaultBorder = UIManager.getBorder("TextField.border");

		if (RegexEnum.NUMBERONLY.isMatch(hoursTextField.getText())) {
			hoursTextField.setBorder(defaultBorder);
			res++;
		} else {
			hoursTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		return res > 0;
	}

	private boolean regexCheckerFinishProduct() {
		int res = 0;
		Border defaultBorder = UIManager.getBorder("TextField.border");

		if (RegexEnum.NUMBERONLY.isMatch(quantityTextField.getText())) {
			quantityTextField.setBorder(defaultBorder);
			res++;
		} else {
			quantityTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.LETTERANDNUMBER.isMatch(productNameTextField.getText())) {
			productNameTextField.setBorder(defaultBorder);
			res++;
		} else {
			productNameTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		return res > 1;
	}

	private boolean regexCheckerConfirmOffer() {
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

		return res > 1;
	}

}
