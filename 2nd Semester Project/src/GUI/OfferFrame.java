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
import controller.OfferController;
import model.Offer;

@SuppressWarnings("serial")
public class OfferFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputAnOffer;
	JList<Offer> list;
	private OfferController offerController;
	SynchronizationController synchronizationController;

	/**
	 * Create the frame.
	 */
	public OfferFrame(SynchronizationController synchronizationController) {
		try {
			offerController = new OfferController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}

		setTitle("Offer Menu");
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

		txtInputAnOffer = new HintTextField("Input an offer number to search...");
		txtInputAnOffer.addActionListener(e -> search());
		txtInputAnOffer.setColumns(10);

		JButton searchButton = new JButton(">");
		searchButton.addActionListener(e -> search());

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup().addContainerGap(433, Short.MAX_VALUE)
						.addComponent(txtInputAnOffer, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGap(5)));
		gl_topPanel.setVerticalGroup(gl_topPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_topPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE).addComponent(searchButton).addComponent(
						txtInputAnOffer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		topPanel.setLayout(gl_topPanel);

		JPanel leftPanel = new JPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[] { 85, 0 };
		gbl_leftPanel.rowHeights = new int[] { 50, 21, 21, 21, 0, 0, 0 };
		gbl_leftPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_leftPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		leftPanel.setLayout(gbl_leftPanel);

		JButton createOfferButton = new JButton("Create Offer");
		createOfferButton.addActionListener(e -> openCreateOfferDialog());

		GridBagConstraints gbc_createOfferButton = new GridBagConstraints();
		gbc_createOfferButton.ipadx = 90;
		gbc_createOfferButton.ipady = 25;
		gbc_createOfferButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_createOfferButton.insets = new Insets(0, 0, 25, 0);
		gbc_createOfferButton.gridx = 0;
		gbc_createOfferButton.gridy = 1;
		leftPanel.add(createOfferButton, gbc_createOfferButton);

		JButton updateOfferButton = new JButton("Update Offer");
		updateOfferButton.addActionListener(e -> openUpdateOfferDialog(list.getSelectedValue()));

		GridBagConstraints gbc_updateOfferButton = new GridBagConstraints();
		gbc_updateOfferButton.ipady = 25;
		gbc_updateOfferButton.ipadx = 90;
		gbc_updateOfferButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_updateOfferButton.insets = new Insets(0, 0, 25, 0);
		gbc_updateOfferButton.gridx = 0;
		gbc_updateOfferButton.gridy = 2;
		leftPanel.add(updateOfferButton, gbc_updateOfferButton);

		JButton deleteOfferButton = new JButton("Delete Offer");
		deleteOfferButton.addActionListener(e -> openDeleteOfferDialog(list.getSelectedValue().getOfferNumber()));

		GridBagConstraints gbc_deleteOfferButton = new GridBagConstraints();
		gbc_deleteOfferButton.insets = new Insets(0, 0, 5, 0);
		gbc_deleteOfferButton.ipady = 25;
		gbc_deleteOfferButton.ipadx = 90;
		gbc_deleteOfferButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_deleteOfferButton.gridx = 0;
		gbc_deleteOfferButton.gridy = 3;
		leftPanel.add(deleteOfferButton, gbc_deleteOfferButton);

		JButton manageProceduresButton = new JButton("Manage Procedures");
		manageProceduresButton.addActionListener(e -> openProceduresFrame());

		GridBagConstraints gbc_manageProceduresButton = new GridBagConstraints();
		gbc_manageProceduresButton.ipady = 20;
		gbc_manageProceduresButton.ipadx = 75;
		gbc_manageProceduresButton.gridx = 0;
		gbc_manageProceduresButton.gridy = 5;
		leftPanel.add(manageProceduresButton, gbc_manageProceduresButton);

		JScrollPane rightScrollPane = new JScrollPane();
		contentPane.add(rightScrollPane, BorderLayout.EAST);

		list = new JList<Offer>();
		list.setMaximumSize(new Dimension(100, 0));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new OfferListCellRenderer());

		rightScrollPane.setViewportView(list);

		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					updateOfferButton.setEnabled(true);
					deleteOfferButton.setEnabled(true);
				} else {
					updateOfferButton.setEnabled(false);
					deleteOfferButton.setEnabled(false);
				}
			}
		});
		timer.setRepeats(true);
		timer.start();

		try {
			new OfferListThread().start();
			new GetOfferListWorker().execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void search() {
		if (txtInputAnOffer.getText().equals("")) {
			new GetOfferListWorker().execute();
		} else {
			try {
				new GetOfferListWorker(Integer.parseInt(txtInputAnOffer.getText())).execute();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void openCreateOfferDialog() {
		CreateOfferDialog createOfferDialog = new CreateOfferDialog(synchronizationController);
		createOfferDialog.setVisible(true);
	}

	private void openUpdateOfferDialog(Offer offer) {
		UpdateOfferDialog updateOfferDialog = new UpdateOfferDialog(offer, synchronizationController);
		updateOfferDialog.setVisible(true);
	}

	private void openDeleteOfferDialog(int offerNumber) {
		DeleteOfferDialog deleteOfferDialog = new DeleteOfferDialog(offerNumber, synchronizationController);
		deleteOfferDialog.setVisible(true);
	}

	private void openProceduresFrame() {
		ProcedureFrame procedureFrame = new ProcedureFrame(synchronizationController);
		procedureFrame.setVisible(true);
		this.dispose();
	}

	private void goBack() {
		this.dispose();
	}

	class OfferListThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronizationController.get();
				new GetOfferListWorker().execute();
			}
		}
	}

	class GetOfferListWorker extends SwingWorker<DefaultListModel<Offer>, Void> {
		int input = -1;

		public GetOfferListWorker() {
		}

		public GetOfferListWorker(int input) {
			this.input = input;
		}

		@Override
		protected DefaultListModel<Offer> doInBackground() throws Exception {
			DefaultListModel<Offer> listRepresentation = new DefaultListModel<>();
			List<Offer> modelList = new ArrayList<Offer>();
			if (input == -1) {
				modelList = offerController.getAllOffers();
			} else {
				modelList.add(offerController.findOfferByNumber(input));
			}
			for (Offer o : modelList) {
				listRepresentation.addElement(o);
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
