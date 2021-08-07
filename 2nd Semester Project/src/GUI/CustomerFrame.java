package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import controller.DataAccessException;
import model.Customer;

@SuppressWarnings("serial")
public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputACustomer;
	JList<Customer> list;
	private CustomerController customerController;
	private SynchronizationController synchronizationController;

	/**
	 * Create the frame.
	 */
	public CustomerFrame(SynchronizationController synchronizationController) {
		try {
			customerController = new CustomerController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}

		setTitle("Customer Menu");
		setMinimumSize(new Dimension(710, 430));
		setBounds(100, 100, 710, 433);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[] { 178, 69, 0 };
		gbl_bottomPanel.rowHeights = new int[] { 21, 0 };
		gbl_bottomPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_bottomPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		bottomPanel.setLayout(gbl_bottomPanel);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(e -> goBack());

		GridBagConstraints gbc_goBackButton = new GridBagConstraints();
		gbc_goBackButton.ipady = 10;
		gbc_goBackButton.ipadx = 10;
		gbc_goBackButton.insets = new Insets(0, 0, 0, 5);
		gbc_goBackButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_goBackButton.gridx = 0;
		gbc_goBackButton.gridy = 0;
		bottomPanel.add(goBackButton, gbc_goBackButton);

		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);

		txtInputACustomer = new HintTextField("Input a customer name to search...");
		txtInputACustomer.addActionListener(e -> search());
		txtInputACustomer.setColumns(10);

		// TODO find a way to search both with id and name -> detect type of input
		JButton searchButton = new JButton(">");
		searchButton.addActionListener(e -> search());

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup().addContainerGap(433, Short.MAX_VALUE)
						.addComponent(txtInputACustomer, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGap(5)));
		gl_topPanel.setVerticalGroup(gl_topPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_topPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE).addComponent(searchButton).addComponent(
						txtInputACustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		topPanel.setLayout(gl_topPanel);

		JPanel leftPanel = new JPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[] { 85, 0 };
		gbl_leftPanel.rowHeights = new int[] { 50, 21, 21, 21, 0 };
		gbl_leftPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_leftPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		leftPanel.setLayout(gbl_leftPanel);

		JButton createCustomerButton = new JButton("Create Customer");
		createCustomerButton.addActionListener(e -> openCreateCustomerDialog());

		GridBagConstraints gbc_createCustomerButton = new GridBagConstraints();
		gbc_createCustomerButton.ipadx = 90;
		gbc_createCustomerButton.ipady = 25;
		gbc_createCustomerButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_createCustomerButton.insets = new Insets(0, 0, 25, 0);
		gbc_createCustomerButton.gridx = 0;
		gbc_createCustomerButton.gridy = 1;
		leftPanel.add(createCustomerButton, gbc_createCustomerButton);

		JButton updateCustomerButton = new JButton("Update Customer");
		updateCustomerButton.addActionListener(e -> openUpdateCustomerDialog(list.getSelectedValue()));

		GridBagConstraints gbc_updateCustomerButton = new GridBagConstraints();
		gbc_updateCustomerButton.ipady = 25;
		gbc_updateCustomerButton.ipadx = 90;
		gbc_updateCustomerButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_updateCustomerButton.insets = new Insets(0, 0, 25, 0);
		gbc_updateCustomerButton.gridx = 0;
		gbc_updateCustomerButton.gridy = 2;
		leftPanel.add(updateCustomerButton, gbc_updateCustomerButton);

		JButton deleteCustomerButton = new JButton("Delete Customer");
		deleteCustomerButton.addActionListener(e -> openDeleteCustomerDialog(list.getSelectedValue().getId()));

		GridBagConstraints gbc_deleteCustomerButton = new GridBagConstraints();
		gbc_deleteCustomerButton.ipady = 25;
		gbc_deleteCustomerButton.ipadx = 90;
		gbc_deleteCustomerButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_deleteCustomerButton.gridx = 0;
		gbc_deleteCustomerButton.gridy = 3;
		leftPanel.add(deleteCustomerButton, gbc_deleteCustomerButton);

		JScrollPane rightScrollPane = new JScrollPane();
		contentPane.add(rightScrollPane, BorderLayout.EAST);

		list = new JList<Customer>();
		list.setMaximumSize(new Dimension(100, 0));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new CustomerListCellRenderer());

		rightScrollPane.setViewportView(list);

		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					updateCustomerButton.setEnabled(true);
					deleteCustomerButton.setEnabled(true);
				} else {
					updateCustomerButton.setEnabled(false);
					deleteCustomerButton.setEnabled(false);
				}
			}
		});
		timer.setRepeats(true);
		timer.start();

		try {
			new CustomerListThread().start();
			new GetCustomerListWorker().execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void search() {
		if (txtInputACustomer.getText().equals("")) {
			new GetCustomerListWorker().execute();
		} else {
			try {
				new GetCustomerListWorker(txtInputACustomer.getText()).execute();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void openCreateCustomerDialog() {
		CreateCustomerDialog createCustomerDialog = new CreateCustomerDialog(synchronizationController);
		createCustomerDialog.setVisible(true);
	}

	private void openUpdateCustomerDialog(Customer customer) {
		UpdateCustomerDialog updateCustomerDialog = new UpdateCustomerDialog(customer, synchronizationController);
		updateCustomerDialog.setVisible(true);
	}

	private void openDeleteCustomerDialog(int customerId) {
		DeleteCustomerDialog deleteCustomerDialog = new DeleteCustomerDialog(customerId, synchronizationController);
		deleteCustomerDialog.setVisible(true);
	}

	private void goBack() {
		this.dispose();
	}

	class CustomerListThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronizationController.get();
				new GetCustomerListWorker().execute();
			}
		}
	}

	class GetCustomerListWorker extends SwingWorker<DefaultListModel<Customer>, Void> {
		String input = null;

		public GetCustomerListWorker() {
		}

		public GetCustomerListWorker(String input) {
			this.input = input;
		}

		@Override
		protected DefaultListModel<Customer> doInBackground() throws Exception {
			DefaultListModel<Customer> listRepresentation = new DefaultListModel<>();
			List<Customer> modelList = new ArrayList<Customer>();
			if (input == null) {
				modelList = customerController.getAllCustomers();
			} else {
				modelList = customerController.findCustomerByNameRegex(input);
			}
			for (Customer c : modelList) {
				listRepresentation.addElement(c);
			}
			return listRepresentation;
		}

		protected void done() {
			try {
				list.setModel(get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
