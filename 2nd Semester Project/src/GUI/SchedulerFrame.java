package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import controller.DataAccessException;
import controller.ProcedureController;
import controller.SchedulerController;
import model.Employee;
import model.EmployeeProcedure;
import model.Procedure;

@SuppressWarnings("serial")
public class SchedulerFrame extends JFrame {

	private JPanel contentPane;
	private ProcedureController procedureController;
	private SchedulerController schedulerController;
	SynchronizationController synchronizationController;
	JList<EmployeeProcedure> list;
	int rem;

	/**
	 * Create the frame.
	 */
	public SchedulerFrame(SynchronizationController synchronizationController) {
		try {
			procedureController = new ProcedureController();
			try {
				schedulerController = SchedulerController.getSchedulerControllerInstance();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			this.synchronizationController = synchronizationController;
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}

		setTitle("Scheduler");
		setMinimumSize(new Dimension(710, 430));
		setBounds(100, 100, 710, 433);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		list = new JList<EmployeeProcedure>();
		list.setCellRenderer(new EmployeeProcedureListCellRenderer());
		scrollPane.setViewportView(list);

		JPanel rightPanel = new JPanel();
		contentPane.add(rightPanel, BorderLayout.EAST);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] { 86, 0 };
		gbl_rightPanel.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0 };
		gbl_rightPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_rightPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		rightPanel.setLayout(gbl_rightPanel);

		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(e -> finishProcedure());

		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.ipady = 20;
		gbc_doneButton.ipadx = 40;
		gbc_doneButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_doneButton.gridx = 0;
		gbc_doneButton.gridy = 5;
		rightPanel.add(doneButton, gbc_doneButton);

		JPanel leftPanel = new JPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Placeholder");
		leftPanel.add(lblNewLabel);

		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[] { 70, 0 };
		gbl_bottomPanel.rowHeights = new int[] { 20, 0 };
		gbl_bottomPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_bottomPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		bottomPanel.setLayout(gbl_bottomPanel);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(e -> goBack());

		GridBagConstraints gbc_goBackButton = new GridBagConstraints();
		gbc_goBackButton.ipady = 20;
		gbc_goBackButton.ipadx = 20;
		gbc_goBackButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_goBackButton.gridx = 0;
		gbc_goBackButton.gridy = 0;
		bottomPanel.add(goBackButton, gbc_goBackButton);

		try {
			new EmployeeProcedureListThread().start();
			new GetEmployeeProcedureListWorker().execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void finishProcedure() {
		if (list.getSelectedValue().getProcedure() != null) {
			try {
				rem = list.getSelectedValue().getProcedure().getCode();
				try {
					schedulerController.removeDone(list.getSelectedValue().getEmployee());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				procedureController.updateDone(rem);
				synchronizationController.set();
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private void goBack() {
		this.dispose();
	}

	class EmployeeProcedureListThread extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronizationController.get();
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							schedulerController.schedule();
						} catch (DataAccessException e) {
							e.printStackTrace();
						}
					}
				});
				thread.run();
				new GetEmployeeProcedureListWorker().execute();
			}
		}
	}

	class GetEmployeeProcedureListWorker extends SwingWorker<DefaultListModel<EmployeeProcedure>, Void> {

		public GetEmployeeProcedureListWorker() {
		}

		@Override
		protected DefaultListModel<EmployeeProcedure> doInBackground() throws Exception {
			DefaultListModel<EmployeeProcedure> listRepresentation = new DefaultListModel<>();
			HashMap<Employee, Procedure> modelList = new HashMap<Employee, Procedure>();
			modelList = schedulerController.getSchedule();
			for (Map.Entry<Employee, Procedure> entry : modelList.entrySet()) {
				Employee key = entry.getKey();
				System.out.println("getkey: " + key);
				Procedure value = entry.getValue();
				System.out.println("getValue: " + value);

				EmployeeProcedure ep = new EmployeeProcedure(key, value);

				listRepresentation.addElement(ep);
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
