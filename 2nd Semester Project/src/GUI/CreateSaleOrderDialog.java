package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.CustomerController;
import controller.DataAccessException;
import controller.ProductController;
import controller.SaleOrderController;
import controller.SchedulerController;
import model.Customer;
import model.Offer;
import model.Product;

@SuppressWarnings("serial")
public class CreateSaleOrderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField offerNumberTextField;
	private JTextField dueDateTextField;
	private JTextField totalPriceTextField;
	private JTextField orderDateTextField;
	private JComboBox<String> customerNameTextField;
	JList<Product> productsList;
	ProductController productController;
	SaleOrderController saleOrderController;
	CustomerController customerController;
	private SynchronizationController synchronizationController;
	private SchedulerController schedulerController;
	private List<Product> products;
	private HashMap<String, Integer> hash;
	private JTextField productQuantityTextField;
	private JTextField productDescriptionTextField;
	private JTextField productPriceTextField;
	JComboBox<String> productNameComboBox;

	/**
	 * Create the dialog.
	 */
	public CreateSaleOrderDialog(Offer offer, SynchronizationController synchronizationController) {
		try {
			productController = new ProductController();
			saleOrderController = new SaleOrderController();
			customerController = new CustomerController();
			this.synchronizationController = synchronizationController;
			schedulerController = SchedulerController.getSchedulerControllerInstance();
			products = new ArrayList<Product>();
			hash = new HashMap<String, Integer>();
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Create Sale Order");
		setMinimumSize(new Dimension(710, 430));
		setBounds(100, 100, 710, 433);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel leftPanel = new JPanel();
			contentPanel.add(leftPanel, BorderLayout.WEST);
			GridBagLayout gbl_leftPanel = new GridBagLayout();
			gbl_leftPanel.columnWidths = new int[] { 0, 0 };
			gbl_leftPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_leftPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			gbl_leftPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, Double.MIN_VALUE };
			leftPanel.setLayout(gbl_leftPanel);
			{
				JLabel offerNumberLabel = new JLabel("Offer Number:");
				GridBagConstraints gbc_offerNumberLabel = new GridBagConstraints();
				gbc_offerNumberLabel.insets = new Insets(0, 0, 5, 0);
				gbc_offerNumberLabel.gridx = 0;
				gbc_offerNumberLabel.gridy = 0;
				leftPanel.add(offerNumberLabel, gbc_offerNumberLabel);
			}
			{
				offerNumberTextField = new JTextField("" + offer.getOfferNumber());
				GridBagConstraints gbc_offerNumberTextField = new GridBagConstraints();
				gbc_offerNumberTextField.ipadx = 10;
				gbc_offerNumberTextField.insets = new Insets(0, 0, 5, 5);
				gbc_offerNumberTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_offerNumberTextField.gridx = 0;
				gbc_offerNumberTextField.gridy = 1;
				leftPanel.add(offerNumberTextField, gbc_offerNumberTextField);
				offerNumberTextField.setColumns(10);
				offerNumberTextField.setEnabled(false);
			}
			{
				JLabel dueDateLabel = new JLabel("Due Date:");
				GridBagConstraints gbc_dueDateLabel = new GridBagConstraints();
				gbc_dueDateLabel.insets = new Insets(0, 0, 5, 0);
				gbc_dueDateLabel.gridx = 0;
				gbc_dueDateLabel.gridy = 3;
				leftPanel.add(dueDateLabel, gbc_dueDateLabel);
			}
			{
				dueDateTextField = new JTextField("" + offer.getDueDate());
				GridBagConstraints gbc_dueDateTextField = new GridBagConstraints();
				gbc_dueDateTextField.insets = new Insets(0, 0, 5, 5);
				gbc_dueDateTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_dueDateTextField.gridx = 0;
				gbc_dueDateTextField.gridy = 4;
				leftPanel.add(dueDateTextField, gbc_dueDateTextField);
				dueDateTextField.setColumns(10);
				dueDateTextField.setEnabled(false);
			}
			{
				JLabel totalPriceLabel = new JLabel("Total Price:");
				GridBagConstraints gbc_totalPriceLabel = new GridBagConstraints();
				gbc_totalPriceLabel.insets = new Insets(0, 0, 5, 0);
				gbc_totalPriceLabel.gridx = 0;
				gbc_totalPriceLabel.gridy = 6;
				leftPanel.add(totalPriceLabel, gbc_totalPriceLabel);
			}
			{
				totalPriceTextField = new JTextField("" + offer.getTotalPrice());
				GridBagConstraints gbc_totalPriceTextField = new GridBagConstraints();
				gbc_totalPriceTextField.insets = new Insets(0, 0, 5, 5);
				gbc_totalPriceTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_totalPriceTextField.gridx = 0;
				gbc_totalPriceTextField.gridy = 7;
				leftPanel.add(totalPriceTextField, gbc_totalPriceTextField);
				totalPriceTextField.setColumns(10);
				totalPriceTextField.setEnabled(false);
			}
			{
				JLabel orderDateLabel = new JLabel("Order Date:");
				GridBagConstraints gbc_orderDateLabel = new GridBagConstraints();
				gbc_orderDateLabel.insets = new Insets(0, 0, 5, 0);
				gbc_orderDateLabel.gridx = 0;
				gbc_orderDateLabel.gridy = 9;
				leftPanel.add(orderDateLabel, gbc_orderDateLabel);
			}
			{
				orderDateTextField = new JTextField("" + java.time.LocalDate.now());
				GridBagConstraints gbc_orderDateTextField = new GridBagConstraints();
				gbc_orderDateTextField.insets = new Insets(0, 0, 5, 5);
				gbc_orderDateTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_orderDateTextField.gridx = 0;
				gbc_orderDateTextField.gridy = 10;
				leftPanel.add(orderDateTextField, gbc_orderDateTextField);
				orderDateTextField.setColumns(10);
				orderDateTextField.setEnabled(false);
			}
			{
				JLabel customerNameLabel = new JLabel("Customer Name:");
				GridBagConstraints gbc_customerNameLabel = new GridBagConstraints();
				gbc_customerNameLabel.insets = new Insets(0, 0, 5, 0);
				gbc_customerNameLabel.gridx = 0;
				gbc_customerNameLabel.gridy = 12;
				leftPanel.add(customerNameLabel, gbc_customerNameLabel);
			}
			{
				customerNameTextField = new JComboBox<String>();
				GridBagConstraints gbc_customerNameTextField = new GridBagConstraints();
				gbc_customerNameTextField.insets = new Insets(0, 0, 0, 5);
				gbc_customerNameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_customerNameTextField.gridx = 0;
				gbc_customerNameTextField.gridy = 13;
				leftPanel.add(customerNameTextField, gbc_customerNameTextField);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				productsList = new JList<Product>();
				productsList.setCellRenderer(new ProductListCellRenderer());
				productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(productsList);
			}
			{
				JPanel topPanel = new JPanel();
				scrollPane.setColumnHeaderView(topPanel);
				GridBagLayout gbl_topPanel = new GridBagLayout();
				gbl_topPanel.columnWidths = new int[] { 55, 0, 0, 0, 0 };
				gbl_topPanel.rowHeights = new int[] { 16, 0 };
				gbl_topPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				gbl_topPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
				topPanel.setLayout(gbl_topPanel);
				{
					JLabel productNameLabel = new JLabel("Name |");
					GridBagConstraints gbc_productNameLabel = new GridBagConstraints();
					gbc_productNameLabel.insets = new Insets(0, 0, 0, 5);
					gbc_productNameLabel.gridx = 0;
					gbc_productNameLabel.gridy = 0;
					topPanel.add(productNameLabel, gbc_productNameLabel);
				}
				{
					JLabel productDescriptionLabel = new JLabel("Description |");
					GridBagConstraints gbc_productDescriptionLabel = new GridBagConstraints();
					gbc_productDescriptionLabel.insets = new Insets(0, 0, 0, 5);
					gbc_productDescriptionLabel.gridx = 1;
					gbc_productDescriptionLabel.gridy = 0;
					topPanel.add(productDescriptionLabel, gbc_productDescriptionLabel);
				}
				{
					JLabel productPriceLabel = new JLabel("Price |");
					GridBagConstraints gbc_productPriceLabel = new GridBagConstraints();
					gbc_productPriceLabel.insets = new Insets(0, 0, 0, 5);
					gbc_productPriceLabel.gridx = 2;
					gbc_productPriceLabel.gridy = 0;
					topPanel.add(productPriceLabel, gbc_productPriceLabel);
				}
				{
					JLabel productQuantityLabel = new JLabel("Quantity");
					GridBagConstraints gbc_productQuantityLabel = new GridBagConstraints();
					gbc_productQuantityLabel.gridx = 3;
					gbc_productQuantityLabel.gridy = 0;
					topPanel.add(productQuantityLabel, gbc_productQuantityLabel);
				}
			}
		}

		JPanel rightPanel = new JPanel();
		contentPanel.add(rightPanel, BorderLayout.EAST);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] { 86, 0 };
		gbl_rightPanel.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_rightPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_rightPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		rightPanel.setLayout(gbl_rightPanel);
		{
			{
				JLabel productNameLabel = new JLabel("Product Name:");
				GridBagConstraints gbc_productNameLabel = new GridBagConstraints();
				gbc_productNameLabel.insets = new Insets(0, 0, 5, 0);
				gbc_productNameLabel.gridx = 0;
				gbc_productNameLabel.gridy = 0;
				rightPanel.add(productNameLabel, gbc_productNameLabel);
			}
			{
				productNameComboBox = new JComboBox<String>();
				productNameComboBox.addActionListener(e -> {
					productQuantityTextField.setText("" + hash.get(productNameComboBox.getSelectedItem()));
				});
				GridBagConstraints gbc_productNameComboBox = new GridBagConstraints();
				gbc_productNameComboBox.insets = new Insets(0, 0, 5, 0);
				gbc_productNameComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_productNameComboBox.gridx = 0;
				gbc_productNameComboBox.gridy = 1;
				rightPanel.add(productNameComboBox, gbc_productNameComboBox);
			}
			{
				JLabel productQuantityLabel = new JLabel("Product Quantity:");
				GridBagConstraints gbc_productQuantityLabel = new GridBagConstraints();
				gbc_productQuantityLabel.insets = new Insets(0, 0, 5, 0);
				gbc_productQuantityLabel.gridx = 0;
				gbc_productQuantityLabel.gridy = 3;
				rightPanel.add(productQuantityLabel, gbc_productQuantityLabel);
			}
			{
				productQuantityTextField = new JTextField();
				GridBagConstraints gbc_productQuantityTextField = new GridBagConstraints();
				gbc_productQuantityTextField.insets = new Insets(0, 0, 5, 0);
				gbc_productQuantityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_productQuantityTextField.gridx = 0;
				gbc_productQuantityTextField.gridy = 4;
				rightPanel.add(productQuantityTextField, gbc_productQuantityTextField);
				productQuantityTextField.setColumns(10);
				productQuantityTextField.setEnabled(false);
			}
			{
				JLabel productDescriptionLabel = new JLabel("Product Description:");
				GridBagConstraints gbc_productDescriptionLabel = new GridBagConstraints();
				gbc_productDescriptionLabel.insets = new Insets(0, 0, 5, 0);
				gbc_productDescriptionLabel.gridx = 0;
				gbc_productDescriptionLabel.gridy = 6;
				rightPanel.add(productDescriptionLabel, gbc_productDescriptionLabel);
			}
		}
		{
			productDescriptionTextField = new JTextField();
			GridBagConstraints gbc_productDescriptionTextField = new GridBagConstraints();
			gbc_productDescriptionTextField.insets = new Insets(0, 0, 5, 0);
			gbc_productDescriptionTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_productDescriptionTextField.gridx = 0;
			gbc_productDescriptionTextField.gridy = 7;
			rightPanel.add(productDescriptionTextField, gbc_productDescriptionTextField);
			productDescriptionTextField.setColumns(10);
		}
		{
			JLabel productPriceLabel = new JLabel("Product Price:");
			GridBagConstraints gbc_productPriceLabel = new GridBagConstraints();
			gbc_productPriceLabel.insets = new Insets(0, 0, 5, 0);
			gbc_productPriceLabel.gridx = 0;
			gbc_productPriceLabel.gridy = 9;
			rightPanel.add(productPriceLabel, gbc_productPriceLabel);
		}
		JButton addProductButton = new JButton("Add Product");
		addProductButton.addActionListener(
				e -> addProduct(productNameComboBox.getItemAt(productNameComboBox.getSelectedIndex()),
						Integer.parseInt(productQuantityTextField.getText()), productDescriptionTextField.getText(),
						BigDecimal.valueOf(Double.parseDouble(productPriceTextField.getText()))));
		{
			productPriceTextField = new JTextField();
			GridBagConstraints gbc_productPriceTextField = new GridBagConstraints();
			gbc_productPriceTextField.insets = new Insets(0, 0, 5, 0);
			gbc_productPriceTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_productPriceTextField.gridx = 0;
			gbc_productPriceTextField.gridy = 10;
			rightPanel.add(productPriceTextField, gbc_productPriceTextField);
			productPriceTextField.setColumns(10);
		}
		GridBagConstraints gbc_addProductButton = new GridBagConstraints();
		gbc_addProductButton.insets = new Insets(0, 5, 5, 0);
		gbc_addProductButton.ipady = 20;
		gbc_addProductButton.ipadx = 20;
		gbc_addProductButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_addProductButton.gridx = 0;
		gbc_addProductButton.gridy = 12;
		rightPanel.add(addProductButton, gbc_addProductButton);

		JButton removeProductButton = new JButton("Remove Product");
		removeProductButton.addActionListener(e -> removeProduct(productsList.getSelectedValue()));

		GridBagConstraints gbc_removeProductButton = new GridBagConstraints();
		gbc_removeProductButton.ipady = 20;
		gbc_removeProductButton.ipadx = 20;
		gbc_removeProductButton.gridx = 0;
		gbc_removeProductButton.gridy = 13;
		rightPanel.add(removeProductButton, gbc_removeProductButton);

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0 };
			gbl_buttonPane.rowHeights = new int[] { 23, 0 };
			gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> cancel());
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.gridwidth = 2;
				gbc_cancelButton.ipady = 10;
				gbc_cancelButton.ipadx = 10;
				gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
				gbc_cancelButton.gridx = 1;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
			{
				JButton goBackButton = new JButton("Go Back");
				goBackButton.addActionListener(e -> goBack());

				GridBagConstraints gbc_goBackButton = new GridBagConstraints();
				gbc_goBackButton.ipady = 10;
				gbc_goBackButton.ipadx = 10;
				gbc_goBackButton.insets = new Insets(0, 0, 0, 5);
				gbc_goBackButton.gridx = 3;
				gbc_goBackButton.gridy = 0;
				buttonPane.add(goBackButton, gbc_goBackButton);
			}

			JButton confirmButton = new JButton("Confirm");
			confirmButton.addActionListener(e -> {
				try {
					confirmCreation(customerNameTextField.getItemAt(customerNameTextField.getSelectedIndex()),
							Date.valueOf(orderDateTextField.getText()), offer.getDueDate(), offer.getOfferNumber(),
							offer.getTotalPrice(), products);
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			});
			GridBagConstraints gbc_confirmButton = new GridBagConstraints();
			gbc_confirmButton.insets = new Insets(0, 0, 0, 5);
			gbc_confirmButton.ipady = 10;
			gbc_confirmButton.ipadx = 10;
			gbc_confirmButton.gridx = 19;
			gbc_confirmButton.gridy = 0;
			buttonPane.add(confirmButton, gbc_confirmButton);

			Timer timer = new Timer(500, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (products.isEmpty() || customerNameTextField.getSelectedIndex() == -1) {
						confirmButton.setEnabled(false);
					} else {
						confirmButton.setEnabled(true);
					}

					if (productDescriptionTextField.getText().equals("")
							|| productPriceTextField.getText().equals("")) {
						addProductButton.setEnabled(false);
					} else {
						addProductButton.setEnabled(true);
					}

					if (productsList.isSelectionEmpty()) {
						removeProductButton.setEnabled(false);
					} else {
						removeProductButton.setEnabled(true);
					}
				}
			});
			timer.setRepeats(true);
			timer.start();
		}

		new AddedProductsListThread().start();
		new GetProductListWorker().execute();
		new GetCustomerListWorker().execute();
	}

	private void addProduct(String productName, int quantity, String description, BigDecimal price) {
		if (regexChecker()) {
			Product product = new Product(productName, description, price, quantity);
			products.add(product);

			synchronizationController.set();
		}
	}

	private void removeProduct(Product product) {
		products.remove(product);

		synchronizationController.set();
	}

	private void confirmCreation(String customerName, Date orderDate, Date dueDate, int offerNumber,
			BigDecimal totalPrice, List<Product> products) throws DataAccessException {

		if (customerController
				.findCustomerByName(customerNameTextField.getItemAt(customerNameTextField.getSelectedIndex()))
				.getCompanyName() != null) {
			saleOrderController.createSaleOrder(customerName, orderDate, dueDate, offerNumber, totalPrice, products);
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						schedulerController.schedule();
					} catch (DataAccessException e) {
						e.printStackTrace();
					}
				}});
			thread.run();
			synchronizationController.set();
			this.dispose();
		} else {
			customerNameTextField.setBorder(new LineBorder(Color.RED, 2));
		}

	}

	private boolean regexChecker() {
		int res = 0;
		Border defaultBorder = UIManager.getBorder("TextField.border");

		if (RegexEnum.LETTERANDNUMBER.isMatch(productDescriptionTextField.getText())) {
			productDescriptionTextField.setBorder(defaultBorder);
			res++;
		} else {
			productDescriptionTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		if (RegexEnum.PRICE.isMatch(productPriceTextField.getText())) {
			productPriceTextField.setBorder(defaultBorder);
			res++;
		} else {
			productPriceTextField.setBorder(new LineBorder(Color.RED, 2));
		}

		return res > 1;
	}

	private void goBack() {
		this.dispose();
		ChooseOfferForOrderDialog chooseOfferForOrderDialog = new ChooseOfferForOrderDialog(synchronizationController);
		chooseOfferForOrderDialog.setVisible(true);
	}

	private void cancel() {
		this.dispose();
	}

	class AddedProductsListThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronizationController.get();
				fillList();
			}
		}
	}

	private void fillList() {
		DefaultListModel<Product> listRepresentation = new DefaultListModel<>();
		for (Product p : products) {
			listRepresentation.addElement(p);
		}

		productsList.setModel(listRepresentation);
	}

	class GetProductListWorker extends SwingWorker<HashMap<String, Integer>, Void> {
		int input = -1;

		public GetProductListWorker() {
		}

		@Override
		protected HashMap<String, Integer> doInBackground() throws Exception {
			HashMap<String, Integer> modelList = new HashMap<String, Integer>();

			modelList = productController.getAllProductAndQuantity(Integer.parseInt(offerNumberTextField.getText()));

			return modelList;
		}

		protected void done() {
			try {
				hash = get();
				for (Map.Entry<String, Integer> entry : hash.entrySet()) {
					String key = entry.getKey();

					productNameComboBox.addItem(key);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class GetCustomerListWorker extends SwingWorker<List<Customer>, Void> {
		int input = -1;

		public GetCustomerListWorker() {
		}

		@Override
		protected List<Customer> doInBackground() throws Exception {
			List<Customer> modelList = new ArrayList<Customer>();

			modelList = customerController.getAllCustomers();

			return modelList;
		}

		protected void done() {
			try {
				for (Customer customer : get()) {
					customerNameTextField.addItem(customer.getCompanyName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
