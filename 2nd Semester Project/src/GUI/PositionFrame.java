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
import controller.PositionController;
import model.Position;

@SuppressWarnings("serial")
public class PositionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputAPosition;
	JList<Position> list;
	private PositionController positionController;
	SynchronizationController synchronizationController;

	/**
	 * Create the frame.
	 */
	public PositionFrame(SynchronizationController synchronizationController) {
		try {
			positionController = new PositionController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}

		setTitle("Positions Menu");
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

		txtInputAPosition = new HintTextField("Input a position name to search...");
		txtInputAPosition.addActionListener(e -> search());
		txtInputAPosition.setColumns(10);

		JButton searchButton = new JButton(">");
		searchButton.addActionListener(e -> search());

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup().addContainerGap(433, Short.MAX_VALUE)
						.addComponent(txtInputAPosition, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGap(5)));
		gl_topPanel.setVerticalGroup(gl_topPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_topPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE).addComponent(searchButton).addComponent(
						txtInputAPosition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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

		JButton createPositionButton = new JButton("Create Position");
		createPositionButton.addActionListener(e -> openCreatePositionDialog());

		GridBagConstraints gbc_createPositionButton = new GridBagConstraints();
		gbc_createPositionButton.ipadx = 90;
		gbc_createPositionButton.ipady = 25;
		gbc_createPositionButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_createPositionButton.insets = new Insets(0, 0, 25, 0);
		gbc_createPositionButton.gridx = 0;
		gbc_createPositionButton.gridy = 1;
		leftPanel.add(createPositionButton, gbc_createPositionButton);

		JButton updatePositionButton = new JButton("Update Position");
		updatePositionButton.addActionListener(e -> openUpdatePositionDialog(list.getSelectedValue()));

		GridBagConstraints gbc_updatePositionButton = new GridBagConstraints();
		gbc_updatePositionButton.ipady = 25;
		gbc_updatePositionButton.ipadx = 90;
		gbc_updatePositionButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_updatePositionButton.insets = new Insets(0, 0, 25, 0);
		gbc_updatePositionButton.gridx = 0;
		gbc_updatePositionButton.gridy = 2;
		leftPanel.add(updatePositionButton, gbc_updatePositionButton);

		JButton deletePositionButton = new JButton("Delete Position");
		deletePositionButton
				.addActionListener(e -> openDeletePositionDialog(list.getSelectedValue().getPositionName()));

		GridBagConstraints gbc_deletePositionButton = new GridBagConstraints();
		gbc_deletePositionButton.insets = new Insets(0, 0, 5, 0);
		gbc_deletePositionButton.ipady = 25;
		gbc_deletePositionButton.ipadx = 90;
		gbc_deletePositionButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_deletePositionButton.gridx = 0;
		gbc_deletePositionButton.gridy = 3;
		leftPanel.add(deletePositionButton, gbc_deletePositionButton);

		JScrollPane rightScrollPane = new JScrollPane();
		contentPane.add(rightScrollPane, BorderLayout.EAST);

		list = new JList<Position>();
		list.setMaximumSize(new Dimension(100, 0));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new PositionListCellRenderer());

		rightScrollPane.setViewportView(list);

		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					updatePositionButton.setEnabled(true);
					deletePositionButton.setEnabled(true);
				} else {
					updatePositionButton.setEnabled(false);
					deletePositionButton.setEnabled(false);
				}
			}
		});
		timer.setRepeats(true);
		timer.start();

		try {
			new PositionListThread().start();
			new GetPositionListWorker().execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void search() {
		if (txtInputAPosition.getText().equals("")) {
			new GetPositionListWorker().execute();
		} else {
			try {
				new GetPositionListWorker(txtInputAPosition.getText()).execute();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void openCreatePositionDialog() {
		CreatePositionDialog createPositionDialog = new CreatePositionDialog(synchronizationController);
		createPositionDialog.setVisible(true);
	}

	private void openUpdatePositionDialog(Position position) {
		UpdatePositionDialog updatePositionDialog = new UpdatePositionDialog(position, synchronizationController);
		updatePositionDialog.setVisible(true);
	}

	private void openDeletePositionDialog(String positionName) {
		DeletePositionDialog deletePositionDialog = new DeletePositionDialog(positionName, synchronizationController);
		deletePositionDialog.setVisible(true);
	}

	private void goBack() {
		EmployeeFrame employeeFrame = new EmployeeFrame(synchronizationController);
		employeeFrame.setVisible(true);
		this.dispose();
	}

	class PositionListThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronizationController.get();
				new GetPositionListWorker().execute();
			}
		}
	}

	class GetPositionListWorker extends SwingWorker<DefaultListModel<Position>, Void> {
		String input = null;

		public GetPositionListWorker() {
		}

		public GetPositionListWorker(String input) {
			this.input = input;
		}

		@Override
		protected DefaultListModel<Position> doInBackground() throws Exception {
			DefaultListModel<Position> listRepresentation = new DefaultListModel<>();
			List<Position> modelList = new ArrayList<Position>();
			if (input == null) {
				modelList = positionController.getAllPositions();
			} else {
				modelList.add(positionController.findPositionByName(input));
			}
			for (Position p : modelList) {
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
