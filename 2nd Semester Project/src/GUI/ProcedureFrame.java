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
import controller.ProcedureController;
import model.Procedure;

@SuppressWarnings("serial")
public class ProcedureFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputAProcedure;
	JList<Procedure> list;
	private ProcedureController procedureController;
	SynchronizationController synchronizationController;

	/**
	 * Create the frame.
	 */
	public ProcedureFrame(SynchronizationController synchronizationController) {
		try {
			procedureController = new ProcedureController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}

		setTitle("Procedures Menu");
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

		txtInputAProcedure = new HintTextField("Input a procedure name to search...");
		txtInputAProcedure.addActionListener(e -> search());
		txtInputAProcedure.setColumns(10);

		JButton searchButton = new JButton(">");
		searchButton.addActionListener(e -> search());

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup().addContainerGap(433, Short.MAX_VALUE)
						.addComponent(txtInputAProcedure, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGap(5)));
		gl_topPanel.setVerticalGroup(gl_topPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_topPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE).addComponent(searchButton).addComponent(
						txtInputAProcedure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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

		JButton createProcedureButton = new JButton("Create Procedure");
		createProcedureButton.addActionListener(e -> openCreateProcedureDialog());

		GridBagConstraints gbc_createProcedureButton = new GridBagConstraints();
		gbc_createProcedureButton.ipadx = 90;
		gbc_createProcedureButton.ipady = 25;
		gbc_createProcedureButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_createProcedureButton.insets = new Insets(0, 0, 25, 0);
		gbc_createProcedureButton.gridx = 0;
		gbc_createProcedureButton.gridy = 1;
		leftPanel.add(createProcedureButton, gbc_createProcedureButton);

		JButton updateProcedureButton = new JButton("Update Procedure");
		updateProcedureButton.addActionListener(e -> openUpdateProcedureDialog(list.getSelectedValue()));

		GridBagConstraints gbc_updateProcedureButton = new GridBagConstraints();
		gbc_updateProcedureButton.ipady = 25;
		gbc_updateProcedureButton.ipadx = 90;
		gbc_updateProcedureButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_updateProcedureButton.insets = new Insets(0, 0, 25, 0);
		gbc_updateProcedureButton.gridx = 0;
		gbc_updateProcedureButton.gridy = 2;
		leftPanel.add(updateProcedureButton, gbc_updateProcedureButton);

		JButton deleteProcedureButton = new JButton("Delete Procedure");
		deleteProcedureButton.addActionListener(e -> openDeleteProcedureDialog(list.getSelectedValue().getCode()));

		GridBagConstraints gbc_deleteProcedureButton = new GridBagConstraints();
		gbc_deleteProcedureButton.insets = new Insets(0, 0, 5, 0);
		gbc_deleteProcedureButton.ipady = 25;
		gbc_deleteProcedureButton.ipadx = 90;
		gbc_deleteProcedureButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_deleteProcedureButton.gridx = 0;
		gbc_deleteProcedureButton.gridy = 3;
		leftPanel.add(deleteProcedureButton, gbc_deleteProcedureButton);

		JScrollPane rightScrollPane = new JScrollPane();
		contentPane.add(rightScrollPane, BorderLayout.EAST);

		list = new JList<Procedure>();
		list.setMaximumSize(new Dimension(100, 0));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new ProcedureListCellRenderer());

		rightScrollPane.setViewportView(list);

		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					updateProcedureButton.setEnabled(true);
					deleteProcedureButton.setEnabled(true);
				} else {
					updateProcedureButton.setEnabled(false);
					deleteProcedureButton.setEnabled(false);
				}
			}
		});
		timer.setRepeats(true);
		timer.start();

		try {
			new ProcedureListThread().start();
			new GetProcedureListWorker().execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void search() {
		if (txtInputAProcedure.getText().equals("")) {
			new GetProcedureListWorker().execute();
		} else {
			try {
				new GetProcedureListWorker(txtInputAProcedure.getText()).execute();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void openCreateProcedureDialog() {
		CreateProcedureDialog createProcedureDialog = new CreateProcedureDialog(synchronizationController);
		createProcedureDialog.setVisible(true);
	}

	private void openUpdateProcedureDialog(Procedure procedure) {
		UpdateProcedureDialog updateProcedureDialog = new UpdateProcedureDialog(procedure, synchronizationController);
		updateProcedureDialog.setVisible(true);
	}

	private void openDeleteProcedureDialog(int code) {
		DeleteProcedureDialog deleteProcedureDialog = new DeleteProcedureDialog(code, synchronizationController);
		deleteProcedureDialog.setVisible(true);
	}

	private void goBack() {
		OfferFrame offerFrame = new OfferFrame(synchronizationController);
		offerFrame.setVisible(true);
		this.dispose();
	}

	class ProcedureListThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronizationController.get();
				new GetProcedureListWorker().execute();
			}
		}
	}

	class GetProcedureListWorker extends SwingWorker<DefaultListModel<Procedure>, Void> {
		String input = null;

		public GetProcedureListWorker() {
		}

		public GetProcedureListWorker(String input) {
			this.input = input;
		}

		@Override
		protected DefaultListModel<Procedure> doInBackground() throws Exception {
			DefaultListModel<Procedure> listRepresentation = new DefaultListModel<>();
			List<Procedure> modelList = new ArrayList<Procedure>();
			if (input == null) {
				modelList = procedureController.getAllProcedures();
			} else {
				modelList = procedureController.findProcedureByName(input);
			}
			for (Procedure p : modelList) {
				listRepresentation.addElement(p);
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
