package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import ctrl.DataAccessException;
import ctrl.EmployeeCtrl;
import db.DBReset;
import model.Department;
import model.Employee;
import model.Project;
import model.WorksOn;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;

	private EmployeeCtrl empCtrl;
	private EmployeeListTableModel employeeListTableModel;

	private JPanel contentPane;
	private JTextField txtEmpNameSearch;
	private JTable tblEmployees;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtSSN;
	private JTextField txtSalary;
	private JTextField txtMinit;
	private JComboBox<Employee> cmbSupervisor;
	private JComboBox<Department> cmbDepartment;
	private JList<WorksOn> lstProjects;
	private JTextField txtBdate;
	private JTextField txtAddress;
	private JComboBox<String> cmbSex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setMinimumSize(new Dimension(710, 430));
		setTitle("Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 433);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmResetDatabase = new JMenuItem("Reset database...");
		mntmResetDatabase.addActionListener(this::mnuResetDatabaseClicked);
		mnFile.add(mntmResetDatabase);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener((e) -> System.exit(0));
		mnFile.add(mntmExit);
		
		JMenu mnhelp = new JMenu("Help");
		menuBar.add(mnhelp);
		
		JMenuItem mntmAbout = new JMenuItem("About...");
		mntmAbout.addActionListener(this::mnuAboutClicked);
		mnhelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pnlEmployeeList = new JPanel();
		contentPane.add(pnlEmployeeList);
		pnlEmployeeList.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		pnlEmployeeList.add(panel, BorderLayout.NORTH);

		JLabel lblFindByName = new JLabel("Name:");
		panel.add(lblFindByName);

		txtEmpNameSearch = new JTextField();
		txtEmpNameSearch.addActionListener(this::btnFindClicked);
		panel.add(txtEmpNameSearch);
		txtEmpNameSearch.setColumns(10);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(this::btnFindClicked);
		panel.add(btnFind);

		JPanel pnlEmpListBottom = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlEmpListBottom.getLayout();
		flowLayout_1.setAlignment(FlowLayout.TRAILING);
		pnlEmployeeList.add(pnlEmpListBottom, BorderLayout.SOUTH);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this::btnDeleteEmployeeClicked);
		pnlEmpListBottom.add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		pnlEmployeeList.add(scrollPane, BorderLayout.CENTER);

		tblEmployees = new JTable();
		scrollPane.setViewportView(tblEmployees);

		JPanel pnlEmployeeObject = new JPanel();
		contentPane.add(pnlEmployeeObject);
		pnlEmployeeObject.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		pnlEmployeeObject.add(panel_1, BorderLayout.NORTH);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(this::btnClearClicked);
		panel_1.add(btnClear);

		JPanel lblLastName = new JPanel();
		lblLastName.setBorder(new TitledBorder(null, "Employee", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlEmployeeObject.add(lblLastName, BorderLayout.CENTER);
		GridBagLayout gbl_lblLastName = new GridBagLayout();
		gbl_lblLastName.columnWidths = new int[] { 0, 0, 0 };
		gbl_lblLastName.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_lblLastName.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_lblLastName.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		lblLastName.setLayout(gbl_lblLastName);

		JLabel lblFirstName = new JLabel("First name: ");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 0;
		lblLastName.add(lblFirstName, gbc_lblFirstName);

		txtFirstName = new JTextField();
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 0;
		lblLastName.add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblMiddleInit = new JLabel("Middle init: ");
		GridBagConstraints gbc_lblMiddleInit = new GridBagConstraints();
		gbc_lblMiddleInit.anchor = GridBagConstraints.EAST;
		gbc_lblMiddleInit.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiddleInit.gridx = 0;
		gbc_lblMiddleInit.gridy = 1;
		lblLastName.add(lblMiddleInit, gbc_lblMiddleInit);

		txtMinit = new JTextField();
		GridBagConstraints gbc_txtMinit = new GridBagConstraints();
		gbc_txtMinit.insets = new Insets(0, 0, 5, 0);
		gbc_txtMinit.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMinit.gridx = 1;
		gbc_txtMinit.gridy = 1;
		lblLastName.add(txtMinit, gbc_txtMinit);
		txtMinit.setColumns(10);

		JLabel lblNewLabel = new JLabel("Last name: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		lblLastName.add(lblNewLabel, gbc_lblNewLabel);

		txtLastName = new JTextField();
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.insets = new Insets(0, 0, 5, 0);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 1;
		gbc_txtLastName.gridy = 2;
		lblLastName.add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);

		JLabel lblSSN = new JLabel("SSN: ");
		GridBagConstraints gbc_lblSSN = new GridBagConstraints();
		gbc_lblSSN.anchor = GridBagConstraints.EAST;
		gbc_lblSSN.insets = new Insets(0, 0, 5, 5);
		gbc_lblSSN.gridx = 0;
		gbc_lblSSN.gridy = 3;
		lblLastName.add(lblSSN, gbc_lblSSN);

		txtSSN = new JTextField();
		GridBagConstraints gbc_txtSSN = new GridBagConstraints();
		gbc_txtSSN.insets = new Insets(0, 0, 5, 0);
		gbc_txtSSN.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSSN.gridx = 1;
		gbc_txtSSN.gridy = 3;
		lblLastName.add(txtSSN, gbc_txtSSN);
		txtSSN.setColumns(10);

		JLabel lblBirthdate = new JLabel("Birthdate: ");
		GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
		gbc_lblBirthdate.anchor = GridBagConstraints.EAST;
		gbc_lblBirthdate.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthdate.gridx = 0;
		gbc_lblBirthdate.gridy = 4;
		lblLastName.add(lblBirthdate, gbc_lblBirthdate);

		txtBdate = new JTextField();
		txtBdate.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_txtBdate = new GridBagConstraints();
		gbc_txtBdate.insets = new Insets(0, 0, 5, 0);
		gbc_txtBdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBdate.gridx = 1;
		gbc_txtBdate.gridy = 4;
		lblLastName.add(txtBdate, gbc_txtBdate);
		txtBdate.setColumns(10);

		JLabel lblAddress = new JLabel("Address: ");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.EAST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 5;
		lblLastName.add(lblAddress, gbc_lblAddress);

		txtAddress = new JTextField();
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.insets = new Insets(0, 0, 5, 0);
		gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddress.gridx = 1;
		gbc_txtAddress.gridy = 5;
		lblLastName.add(txtAddress, gbc_txtAddress);
		txtAddress.setColumns(10);

		JLabel lblSex = new JLabel("Sex: ");
		GridBagConstraints gbc_lblSex = new GridBagConstraints();
		gbc_lblSex.anchor = GridBagConstraints.EAST;
		gbc_lblSex.insets = new Insets(0, 0, 5, 5);
		gbc_lblSex.gridx = 0;
		gbc_lblSex.gridy = 6;
		lblLastName.add(lblSex, gbc_lblSex);

		cmbSex = new JComboBox();
		GridBagConstraints gbc_cmbSex = new GridBagConstraints();
		gbc_cmbSex.insets = new Insets(0, 0, 5, 0);
		gbc_cmbSex.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbSex.gridx = 1;
		gbc_cmbSex.gridy = 6;
		lblLastName.add(cmbSex, gbc_cmbSex);

		JLabel lblSalary = new JLabel("Salary: ");
		GridBagConstraints gbc_lblSalary = new GridBagConstraints();
		gbc_lblSalary.anchor = GridBagConstraints.EAST;
		gbc_lblSalary.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalary.gridx = 0;
		gbc_lblSalary.gridy = 7;
		lblLastName.add(lblSalary, gbc_lblSalary);

		txtSalary = new JTextField();
		txtSalary.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_txtSalary = new GridBagConstraints();
		gbc_txtSalary.insets = new Insets(0, 0, 5, 0);
		gbc_txtSalary.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSalary.gridx = 1;
		gbc_txtSalary.gridy = 7;
		lblLastName.add(txtSalary, gbc_txtSalary);
		txtSalary.setColumns(10);

		JLabel lblSupervigyor = new JLabel("Supervisor: ");
		GridBagConstraints gbc_lblSupervigyor = new GridBagConstraints();
		gbc_lblSupervigyor.anchor = GridBagConstraints.EAST;
		gbc_lblSupervigyor.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupervigyor.gridx = 0;
		gbc_lblSupervigyor.gridy = 8;
		lblLastName.add(lblSupervigyor, gbc_lblSupervigyor);

		cmbSupervisor = new JComboBox<>();
		GridBagConstraints gbc_cmbSupervisor = new GridBagConstraints();
		gbc_cmbSupervisor.insets = new Insets(0, 0, 5, 0);
		gbc_cmbSupervisor.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbSupervisor.gridx = 1;
		gbc_cmbSupervisor.gridy = 8;
		lblLastName.add(cmbSupervisor, gbc_cmbSupervisor);

		JLabel lblDepartment = new JLabel("Department: ");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.anchor = GridBagConstraints.EAST;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 9;
		lblLastName.add(lblDepartment, gbc_lblDepartment);

		cmbDepartment = new JComboBox<>();
		GridBagConstraints gbc_cmbDepartment = new GridBagConstraints();
		gbc_cmbDepartment.insets = new Insets(0, 0, 5, 0);
		gbc_cmbDepartment.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbDepartment.gridx = 1;
		gbc_cmbDepartment.gridy = 9;
		lblLastName.add(cmbDepartment, gbc_cmbDepartment);

		JPanel pnlEmployeeObjectBottomFunctions = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlEmployeeObjectBottomFunctions.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		pnlEmployeeObject.add(pnlEmployeeObjectBottomFunctions, BorderLayout.SOUTH);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this::btnUpdateEmployeeClicked);
		pnlEmployeeObjectBottomFunctions.add(btnUpdate);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this::btnInsertClicked);
		pnlEmployeeObjectBottomFunctions.add(btnInsert);

		JPanel pnlEmployeeWorksOn = new JPanel();
		contentPane.add(pnlEmployeeWorksOn);
		pnlEmployeeWorksOn.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		pnlEmployeeWorksOn.add(panel_3, BorderLayout.NORTH);

		JPanel pnoWorksOnBottom = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnoWorksOnBottom.getLayout();
		flowLayout_3.setAlignment(FlowLayout.TRAILING);
		pnlEmployeeWorksOn.add(pnoWorksOnBottom, BorderLayout.SOUTH);

		JButton btnParticipateInProject = new JButton("Participate...");
		btnParticipateInProject.addActionListener(this::btnParticipateInProjectClicked);
		pnoWorksOnBottom.add(btnParticipateInProject);

		JButton btnLeaveProject = new JButton("Leave");
		btnLeaveProject.addActionListener(this::btnLeaveProjectClicked);
		pnoWorksOnBottom.add(btnLeaveProject);

		JPanel pnlWorksOn = new JPanel();
		pnlWorksOn.setBorder(new TitledBorder(null, "Works on", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlEmployeeWorksOn.add(pnlWorksOn, BorderLayout.CENTER);
		pnlWorksOn.setLayout(new BorderLayout(0, 0));

		lstProjects = new JList<>();
		pnlWorksOn.add(lstProjects, BorderLayout.CENTER);

		// ---- INIT SECTION -----
		init();

	}

	private void init() {
		// init controller
		try {
			this.empCtrl = new EmployeeCtrl();
		} catch (DataAccessException e) {
			complain("Data store error", "Could not establish connection to the data storage", e);
		}

		// init table model for employee list and associate to the Employee table
		this.employeeListTableModel = new EmployeeListTableModel();
		this.tblEmployees.setModel(employeeListTableModel);

		// listen to selection changes in the Employee table
		this.tblEmployees.getSelectionModel().addListSelectionListener((e) -> tblEmployeesSelectionChanged());

		// set up cell renderers for supervisor, department and projects
		this.cmbSupervisor.setRenderer(new EmployeeListCellRenderer());
		this.cmbDepartment.setRenderer(new DepartmentListCellRenderer());
		this.lstProjects.setCellRenderer(new WorksOnListCellRenderer());

		// fill combo-boxes
		try {
			this.fillSupervisorCmb();
		} catch (DataAccessException e) {
			// e1.printStackTrace();
			complain("Data store error", "Could not fill list of supervisors", e);
		}
		try {
			this.fillDepartmentCmb();
		} catch (DataAccessException e) {
			// e.printStackTrace();
			complain("Data store error", "Could not fill list of departments", e);
		}

		fillSexCmb();
	}

	private void complain(String title, String text, Exception e) {
		JOptionPane.showMessageDialog(this, text + " (" + e.getMessage() + ") ", title, JOptionPane.OK_OPTION);
	}

	private void mnuResetDatabaseClicked(ActionEvent e) {
		if(JOptionPane.showConfirmDialog(null, "Do you *REALLY* want to reset the data in the database?", "Datbase Reset", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
			try {
				DBReset.resetDB();
				txtEmpNameSearch.setText("");
				btnFindClicked(null);
			} catch (DataAccessException e1) {
				//e1.printStackTrace();
				complain("Data access error", "Alas, the database doesn't see resettable", e1);
			}
		}
	}
	
	private void mnuAboutClicked(ActionEvent e) {
		JOptionPane.showMessageDialog(this, 	"Created@UCN\n"
											+ 	"2018-08-30 (v.1.0.0)\n"
											+ 	"Written by: knol\n"
											+ 	"Reviewed by: mhi");
	}
	
	private void fillSupervisorCmb() throws DataAccessException {
		List<Employee> employees = this.empCtrl.findAll();
		Employee currEmployee = getCurrentEmployee();
		if (currEmployee != null) {
			employees.remove(currEmployee);
		}
		DefaultComboBoxModel<Employee> supervisorComboBoxModel = new DefaultComboBoxModel<>();
		for (int i = 0; i < employees.size(); i++) {
			supervisorComboBoxModel.addElement(employees.get(i));
		}
		this.cmbSupervisor.setModel(supervisorComboBoxModel);
		this.cmbSupervisor.setSelectedItem(currEmployee != null ? currEmployee.getSupervisor() : null);
	}

	private void fillDepartmentCmb() throws DataAccessException {
		List<Department> departments = this.empCtrl.findAllDepartments();
		if (departments != null) {
			DefaultComboBoxModel<Department> departmentComboBoxModel = new DefaultComboBoxModel<>(
					departments.toArray(new Department[departments.size()]));
			this.cmbDepartment.setModel(departmentComboBoxModel);
			Employee currEmployee = getCurrentEmployee();
			if (currEmployee != null) {
				this.cmbDepartment.setSelectedItem(currEmployee.getDept());
			}
		}
	}

	private void fillSexCmb() {
		DefaultComboBoxModel<String> sexModel = new DefaultComboBoxModel<>(new String[] { "M", "F" });
		cmbSex.setModel(sexModel);
	}

	private void btnFindClicked(ActionEvent e) {
		String empName = this.txtEmpNameSearch.getText();
		try {
			List<Employee> employees = this.empCtrl.findByName(empName);
			this.employeeListTableModel.setData(employees);
//			for(Employee emp : employees) {
//				System.out.println(emp);
//			}
		} catch (Exception ex) {
			complain("Application error", "Could not find employees by name.", ex);
		}
	}

	private Object tblEmployeesSelectionChanged() {
		Employee currEmployee = getCurrentEmployee();
		displayEmployeeObject(currEmployee);
		return null;
	}

	private Employee getCurrentEmployee() {
		int selectedRow = this.tblEmployees.getSelectedRow();
		Employee currEmployee = null;
		if (selectedRow > -1) {
			currEmployee = this.employeeListTableModel.getEmployeeOfRow(selectedRow);
		}
		return currEmployee;
	}

	private void displayEmployeeObject(Employee currEmployee) {
		String fname = "";
		String lname = "";
		String minit = "";
		String ssn = "";

		LocalDate bdate = LocalDate.now();
		String address = "";
		String sex = "M";

		String salary = "";
		Department department = null;
		Employee supervisor = null;
		if (currEmployee != null) {
			fname = currEmployee.getFname();
			lname = currEmployee.getLname();
			minit = currEmployee.getMinit();
			ssn = currEmployee.getSsn();

			bdate = currEmployee.getBdate();
			address = currEmployee.getAddress();
			sex = currEmployee.getSex();

			// salary = Double.toString(currEmployee.getSalary());
			salary = String.format("%.2f", currEmployee.getSalary());
			department = currEmployee.getDept();
			supervisor = currEmployee.getSupervisor();
		}
		txtFirstName.setText(fname);
		txtMinit.setText(minit);
		txtLastName.setText(lname);
		txtSSN.setText(ssn);

		txtBdate.setText(bdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		txtAddress.setText(address);
		cmbSex.setSelectedItem(sex);

		txtSalary.setText(salary);
		try {
			fillSupervisorCmb();
		} catch (DataAccessException e) {
			// e.printStackTrace();
			complain("Data access error", "Could not fill supervisor list", e);
		}

		try {
			fillDepartmentCmb();
		} catch (DataAccessException e) {
			// e.printStackTrace();
			complain("Data access error", "Could not fill department list", e);
		}

		try {
			showWorksOnProjects();
		} catch (DataAccessException e) {
			// e.printStackTrace();
			complain("Data access error", "Could not fill projects list", e);
		}
	}

	private void btnClearClicked(ActionEvent e) {
		tblEmployees.getSelectionModel().clearSelection();
		displayEmployeeObject(null);
	}

	private void showWorksOnProjects() throws DataAccessException {
		Employee currEmployee = getCurrentEmployee();
		DefaultListModel<WorksOn> model = new DefaultListModel<>();
		if (currEmployee != null) {
			List<WorksOn> data = empCtrl.getWorksOnList(currEmployee);
			for (int i = 0; i < data.size(); i++) {
				model.addElement(data.get(i));
			}
		}
		lstProjects.setModel(model);
	}

	private void btnInsertClicked(ActionEvent e) {
		String fname = txtFirstName.getText();
		String minit = txtMinit.getText();
		String lname = txtLastName.getText();
		String ssn = txtSSN.getText();
		LocalDate bdate = LocalDate.parse(txtBdate.getText());
		String address = txtAddress.getText();
		String sex = (String) cmbSex.getSelectedItem();
		String salaryStr = txtSalary.getText();
		Department department = (Department) cmbDepartment.getSelectedItem();
		Employee supervisor = (Employee) cmbSupervisor.getSelectedItem();
		try {
			double salary = Double.parseDouble(salaryStr);
			Employee employee = new Employee(fname, minit, lname, ssn, bdate, address, sex, salary, supervisor,
					department);
			try {
				employee = empCtrl.insert(employee);
			} catch (DataAccessException dae) {
				// e1.printStackTrace();
				complain("Data access error", "Could not insert new employee", dae);
			}

			// clear search field and update employee list
			// could implement features to select newly creatd employee based in ssn
			txtEmpNameSearch.setText(lname);
			btnFindClicked(null);

		} catch (NumberFormatException nfe) {
			complain("Input error", "Salary must be floating point value (nnn.nn)", nfe);
		}
	}

	private void btnDeleteEmployeeClicked(ActionEvent e) {
		complain("Not implemented yet", "Be the one who implements a functionality for this button!",
				new UnsupportedOperationException("Unimplemented method"));
	}

	private void btnUpdateEmployeeClicked(ActionEvent e) {
		complain("Not implemented yet", "Be the one who implements a functionality for this button!",
				new UnsupportedOperationException("Unimplemented method"));
	}

	private void btnParticipateInProjectClicked(ActionEvent e) {
		complain("Not implemented yet", "Be the one who implements a functionality for this button!",
				new UnsupportedOperationException("Unimplemented method"));
	}

	private void btnLeaveProjectClicked(ActionEvent e) {
		complain("Not implemented yet", "Be the one who implements a functionality for this button!",
				new UnsupportedOperationException("Unimplemented method"));
	}
}
