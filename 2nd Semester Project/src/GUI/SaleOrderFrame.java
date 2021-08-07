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

import controller.DataAccessException;
import controller.SaleOrderController;
import model.SaleOrder;

@SuppressWarnings("serial")
public class SaleOrderFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputAnSaleOrder;
	JList<SaleOrder> list;
	private SaleOrderController saleOrderController;
	private SynchronizationController synchronizationController;

	/**
	 * Create the frame.
	 */
	public SaleOrderFrame(SynchronizationController synchronizationController) {
		try {
			saleOrderController = new SaleOrderController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}

		setTitle("Sale Order Menu");
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

		txtInputAnSaleOrder = new HintTextField("Input a sale order number to search...");
		txtInputAnSaleOrder.addActionListener(e -> search());
		txtInputAnSaleOrder.setColumns(10);

		JButton searchButton = new JButton(">");
		searchButton.addActionListener(e -> search());

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup().addContainerGap(433, Short.MAX_VALUE)
						.addComponent(txtInputAnSaleOrder, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGap(5)));
		gl_topPanel.setVerticalGroup(gl_topPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_topPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE).addComponent(searchButton).addComponent(
						txtInputAnSaleOrder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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

		JButton createSaleOrderButton = new JButton("Create Sale Order");
		createSaleOrderButton.addActionListener(e -> openCreateSaleOrderDialog());

		GridBagConstraints gbc_createSaleOrderButton = new GridBagConstraints();
		gbc_createSaleOrderButton.ipadx = 70;
		gbc_createSaleOrderButton.ipady = 25;
		gbc_createSaleOrderButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_createSaleOrderButton.insets = new Insets(0, 0, 25, 0);
		gbc_createSaleOrderButton.gridx = 0;
		gbc_createSaleOrderButton.gridy = 1;
		leftPanel.add(createSaleOrderButton, gbc_createSaleOrderButton);

		JButton updateSaleOrderButton = new JButton("Update Sale Order");
		updateSaleOrderButton.addActionListener(e -> openUpdateSaleOrderDialog(list.getSelectedValue()));

		GridBagConstraints gbc_updateSaleOrderButton = new GridBagConstraints();
		gbc_updateSaleOrderButton.ipady = 25;
		gbc_updateSaleOrderButton.ipadx = 67;
		gbc_updateSaleOrderButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_updateSaleOrderButton.insets = new Insets(0, 0, 25, 0);
		gbc_updateSaleOrderButton.gridx = 0;
		gbc_updateSaleOrderButton.gridy = 2;
		leftPanel.add(updateSaleOrderButton, gbc_updateSaleOrderButton);

		JButton deleteSaleOrderButton = new JButton("Delete Sale Order");
		deleteSaleOrderButton
				.addActionListener(e -> openDeleteSaleOrderDialog(list.getSelectedValue().getSaleOrderNumber()));

		GridBagConstraints gbc_deleteSaleOrderButton = new GridBagConstraints();
		gbc_deleteSaleOrderButton.ipady = 25;
		gbc_deleteSaleOrderButton.ipadx = 70;
		gbc_deleteSaleOrderButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_deleteSaleOrderButton.gridx = 0;
		gbc_deleteSaleOrderButton.gridy = 3;
		leftPanel.add(deleteSaleOrderButton, gbc_deleteSaleOrderButton);

		JScrollPane rightScrollPane = new JScrollPane();
		contentPane.add(rightScrollPane, BorderLayout.EAST);

		list = new JList<SaleOrder>();
		list.setMaximumSize(new Dimension(100, 0));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new SaleOrderListCellRenderer());

		rightScrollPane.setViewportView(list);

		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					updateSaleOrderButton.setEnabled(true);
					deleteSaleOrderButton.setEnabled(true);
				} else {
					updateSaleOrderButton.setEnabled(false);
					deleteSaleOrderButton.setEnabled(false);
				}
			}
		});
		timer.setRepeats(true);
		timer.start();

		try {
			new SaleOrderListThread().start();
			new GetSaleOrderListWorker().execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void search() {
		if (txtInputAnSaleOrder.getText().equals("")) {
			new GetSaleOrderListWorker().execute();
		} else {
			try {
				new GetSaleOrderListWorker(Integer.parseInt(txtInputAnSaleOrder.getText())).execute();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void openCreateSaleOrderDialog() {
		ChooseOfferForOrderDialog chooseOfferForOrderDialog = new ChooseOfferForOrderDialog(synchronizationController);
		chooseOfferForOrderDialog.setVisible(true);
	}

	private void openUpdateSaleOrderDialog(SaleOrder saleOrder) {
		UpdateSaleOrderDialog updateSaleOrderDialog = new UpdateSaleOrderDialog(saleOrder, synchronizationController);
		updateSaleOrderDialog.setVisible(true);
	}

	private void openDeleteSaleOrderDialog(int saleOrderNumber) {
		DeleteSaleOrderDialog deleteSaleOrderDialog = new DeleteSaleOrderDialog(saleOrderNumber,
				synchronizationController);
		deleteSaleOrderDialog.setVisible(true);
	}

	private void goBack() {
		this.dispose();
	}

	class SaleOrderListThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronizationController.get();
				new GetSaleOrderListWorker().execute();
			}
		}
	}

	class GetSaleOrderListWorker extends SwingWorker<DefaultListModel<SaleOrder>, Void> {
		int input = -1;

		public GetSaleOrderListWorker() {
		}

		public GetSaleOrderListWorker(int input) {
			this.input = input;
		}

		@Override
		protected DefaultListModel<SaleOrder> doInBackground() throws Exception {
			DefaultListModel<SaleOrder> listRepresentation = new DefaultListModel<>();
			List<SaleOrder> modelList = new ArrayList<SaleOrder>();
			if (input == -1) {
				modelList = saleOrderController.getAllSaleOrders();
			} else {
				modelList.add(saleOrderController.findSaleOrderByNumber(input));
			}
			for (SaleOrder so : modelList) {
				listRepresentation.addElement(so);
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
