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
import controller.EmployeeController;
import model.Employee;

@SuppressWarnings("serial")
public class EmployeeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputAnEmployee;
	JList<Employee> list;
	private EmployeeController employeeController;
	private SynchronizationController synchronizationController;

	/**
	 * Create the frame.
	 */
	public EmployeeFrame(SynchronizationController synchronizationController) {
		try {
			employeeController = new EmployeeController();
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}

		setTitle("Employee Menu");
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

		txtInputAnEmployee = new HintTextField("Input an employee name to search...");
		txtInputAnEmployee.addActionListener(e -> search());
		txtInputAnEmployee.setColumns(10);

		// TODO find a way to search both with id and name -> detect type of input
		JButton searchButton = new JButton(">");
		searchButton.addActionListener(e -> search());

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup().addContainerGap(433, Short.MAX_VALUE)
						.addComponent(txtInputAnEmployee, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGap(5)));
		gl_topPanel.setVerticalGroup(gl_topPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_topPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE).addComponent(searchButton).addComponent(
						txtInputAnEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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

		JButton createEmployeeButton = new JButton("Create Employee");
		createEmployeeButton.addActionListener(e -> openCreateEmployeeDialog());

		GridBagConstraints gbc_createEmployeeButton = new GridBagConstraints();
		gbc_createEmployeeButton.ipadx = 90;
		gbc_createEmployeeButton.ipady = 25;
		gbc_createEmployeeButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_createEmployeeButton.insets = new Insets(0, 0, 25, 0);
		gbc_createEmployeeButton.gridx = 0;
		gbc_createEmployeeButton.gridy = 1;
		leftPanel.add(createEmployeeButton, gbc_createEmployeeButton);

		JButton updateEmployeeButton = new JButton("Update Employee");
		updateEmployeeButton.addActionListener(e -> openUpdateEmployeeDialog(list.getSelectedValue()));

		GridBagConstraints gbc_updateEmployeeButton = new GridBagConstraints();
		gbc_updateEmployeeButton.ipady = 25;
		gbc_updateEmployeeButton.ipadx = 90;
		gbc_updateEmployeeButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_updateEmployeeButton.insets = new Insets(0, 0, 25, 0);
		gbc_updateEmployeeButton.gridx = 0;
		gbc_updateEmployeeButton.gridy = 2;
		leftPanel.add(updateEmployeeButton, gbc_updateEmployeeButton);

		JButton deleteEmployeeButton = new JButton("Delete Employee");
		deleteEmployeeButton.addActionListener(e -> openDeleteEmployeeDialog(list.getSelectedValue().getId()));

		GridBagConstraints gbc_deleteEmployeeButton = new GridBagConstraints();
		gbc_deleteEmployeeButton.ipady = 25;
		gbc_deleteEmployeeButton.ipadx = 90;
		gbc_deleteEmployeeButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_deleteEmployeeButton.gridx = 0;
		gbc_deleteEmployeeButton.gridy = 3;
		leftPanel.add(deleteEmployeeButton, gbc_deleteEmployeeButton);

		JButton managePositionsButton = new JButton("Manage Positions");
		managePositionsButton.addActionListener(e -> openPositionsFrame());

		GridBagConstraints gbc_managePositionsButton = new GridBagConstraints();
		gbc_managePositionsButton.ipady = 20;
		gbc_managePositionsButton.ipadx = 75;
		gbc_managePositionsButton.gridx = 0;
		gbc_managePositionsButton.gridy = 5;
		leftPanel.add(managePositionsButton, gbc_managePositionsButton);

		JScrollPane rightScrollPane = new JScrollPane();
		contentPane.add(rightScrollPane, BorderLayout.EAST);

		list = new JList<Employee>();
		list.setMaximumSize(new Dimension(100, 0));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new EmployeeListCellRenderer());

		rightScrollPane.setViewportView(list);

		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!list.isSelectionEmpty()) {
					updateEmployeeButton.setEnabled(true);
					deleteEmployeeButton.setEnabled(true);
				} else {
					updateEmployeeButton.setEnabled(false);
					deleteEmployeeButton.setEnabled(false);
				}
			}
		});
		timer.setRepeats(true);
		timer.start();

		try {
			new EmployeeListThread().start();
			new GetEmployeeListWorker().execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void search() {
		if (txtInputAnEmployee.getText().equals("")) {
			new GetEmployeeListWorker().execute();
		} else {
			try {
				new GetEmployeeListWorker(txtInputAnEmployee.getText()).execute();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void openCreateEmployeeDialog() {
		CreateEmployeeDialog createEmployeeDialog = new CreateEmployeeDialog(synchronizationController);
		createEmployeeDialog.setVisible(true);
	}

	private void openUpdateEmployeeDialog(Employee employee) {
		UpdateEmployeeDialog updateEmployeeDialog = new UpdateEmployeeDialog(employee, synchronizationController);
		updateEmployeeDialog.setVisible(true);
	}

	private void openDeleteEmployeeDialog(int employeeId) {
		DeleteEmployeeDialog deleteEmployeeDialog = new DeleteEmployeeDialog(employeeId, synchronizationController);
		deleteEmployeeDialog.setVisible(true);
	}

	private void openPositionsFrame() {
		PositionFrame positionFrame = new PositionFrame(synchronizationController);
		positionFrame.setVisible(true);
		this.dispose();
	}

	private void goBack() {
		this.dispose();
	}

	class EmployeeListThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronizationController.get();
				new GetEmployeeListWorker().execute();
			}
		}
	}

	class GetEmployeeListWorker extends SwingWorker<DefaultListModel<Employee>, Void> {
		String input = null;

		public GetEmployeeListWorker() {
		}

		public GetEmployeeListWorker(String input) {
			this.input = input;
		}

		@Override
		protected DefaultListModel<Employee> doInBackground() throws Exception {
			DefaultListModel<Employee> listRepresentation = new DefaultListModel<>();
			List<Employee> modelList = new ArrayList<Employee>();
			modelList = employeeController.getAllEmployees();

			for (Employee e : modelList) {
				listRepresentation.addElement(e);
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