package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.WarehouseController;
import Model.Warehouse;
import Model.WarehouseContainer;

public class EditWarehouse extends JFrame {

	private JPanel contentPane;
	private JList<String> list;
	private DefaultListModel<String> listRepresentation;
	private JLabel errorLabel = new JLabel("Hello!");
	private Warehouse warehouse;
	private WarehouseContainer warehouseContainer = WarehouseContainer.getWarehouseContainer();
	private WarehouseController warehouseController = new WarehouseController();
	MyListCellRenderer cellRenderer = new MyListCellRenderer();
	private CreateWarehouseDialogPanel createWarehouseDialogPanel = new CreateWarehouseDialogPanel();
	private JDialog createWarehouseDialog;
	private EditWarehouseDialogPanel editWarehouseDialogPanel = new EditWarehouseDialogPanel();
	private JDialog editWarehouseDialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditWarehouse frame = new EditWarehouse();
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
	public EditWarehouse() {
		this.setTitle("Create/Edit Warehouse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnNewButton_1 = new JButton("Add Warehouse");
		btnNewButton_1.setBackground(new Color(173, 216, 230));
		btnNewButton_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createWarehouseDialog();
			}
		});

		list = new JList();
		list.setBackground(new Color(192, 192, 192));
		list.setFont(new Font("Monospaced", Font.PLAIN, 11));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 5;
		gbc_list.gridwidth = 5;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 4;
		gbc_list.gridy = 2;
		contentPane.add(list, gbc_list);
		// list.setCellRenderer(cellRenderer);
		refresh();

		JButton btnNewButton_2 = new JButton("Edit Warehouse");
		btnNewButton_2.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_2.setBackground(new Color(173, 216, 230));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 3;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listRepresentation.get(0).equals("No warehouses found...")) {
					editWarehouseDialog();
				}
			}
		});
				
				JButton deleteButton = new JButton("Delete Warehouse");
				deleteButton.setBackground(new Color(173, 216, 230));
				GridBagConstraints gbc_deleteButton = new GridBagConstraints();
				gbc_deleteButton.insets = new Insets(0, 0, 5, 5);
				gbc_deleteButton.gridx = 1;
				gbc_deleteButton.gridy = 4;
				contentPane.add(deleteButton, gbc_deleteButton);
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (list.getSelectedIndex() == -1) {
							errorLabel.setText("Please select a warehouse in order to delete it!");
						}else if(!listRepresentation.get(0).equals("No warehouses found...")) {
							deleteDialog();
						}
						
					}
				});
		
				JButton btnNewButton_3 = new JButton("Refresh");
				btnNewButton_3.setBackground(new Color(173, 216, 230));
				btnNewButton_3.setFont(new Font("Monospaced", Font.PLAIN, 11));
				GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
				gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton_3.gridx = 1;
				gbc_btnNewButton_3.gridy = 5;
				contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						refresh();
					}
				});

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton.setBackground(new Color(173, 216, 230));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		errorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridwidth = 7;
		gbc_errorLabel.gridx = 2;
		gbc_errorLabel.gridy = 7;
		contentPane.add(errorLabel, gbc_errorLabel);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
	}

	private void cancel() {
		this.dispose();
		MainMenu mainMenu = new MainMenu();
		mainMenu.setVisible(true);
	}

	private void createWarehouseDialog() {
//		try {
//			CreateWarehouse w = new CreateWarehouse();
//			w.setVisible(true);
//			this.dispose();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// if (createWarehouseDialog == null) {
		// Window win = SwingUtilities.getWindowAncestor(this);
		// if (win != null) {
		createWarehouseDialog = new JDialog();
		createWarehouseDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		createWarehouseDialog.setTitle("Create Warehouse");
		createWarehouseDialog.getContentPane().add(createWarehouseDialogPanel);
		// createWarehouseDialog.setBounds(100, 100, 450, 300);
		// createWarehouseDialog.pack();
		createWarehouseDialog.setLocationRelativeTo(null);

		// }

		// }
		createWarehouseDialogPanel.resetlbls1();
		createWarehouseDialog.setSize(450, 210);
		createWarehouseDialog.setVisible(true);

	}

	private void refresh() {
		listRepresentation = new DefaultListModel<String>();
		ArrayList<String> modelList = warehouseContainer.listWarehouses();
		if (modelList.isEmpty() || modelList == null || modelList.contains("")) {
			listRepresentation.addElement("No warehouses found...");
		} else {
			for (String w : modelList) {
				listRepresentation.addElement(w);
			}

		}
		list.setModel(listRepresentation);
	}

	private void editWarehouseDialog() {
		if (list.getSelectedIndex() == -1) {
			errorLabel.setText("Please select a warehouse in order to edit it!");
		} else {
			// if (editWarehouseDialog == null) {
			// Window win = SwingUtilities.getWindowAncestor(this);
			// if (win != null) {

			// if (!listRepresentation.get(0).equals("No warehouses found...")) {
			warehouse = warehouseContainer.getWarehouses().get(list.getSelectedIndex());
			// }

			editWarehouseDialog = new JDialog();
			editWarehouseDialog.setModalityType(ModalityType.APPLICATION_MODAL);
			editWarehouseDialog.setTitle("Edit Warehouse");
			editWarehouseDialog.getContentPane().add(editWarehouseDialogPanel);
			editWarehouseDialog.pack();
			editWarehouseDialog.setLocationRelativeTo(null);
			// }
			// }
			editWarehouseDialogPanel.resetlbls();
			editWarehouseDialog.setSize(300, 210);
			editWarehouseDialog.setVisible(true);
		}
	}
	
	private void deleteDialog() {
		JDialog deleteDialog = new JDialog();
		JPanel contentPanel = new JPanel();
		deleteDialog.setTitle("Are you sure you want to delete this Warehouse?");
		deleteDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		deleteDialog.setSize(400, 75);
		// deleteDialog.pack();
		deleteDialog.setLocationRelativeTo(null);
		JButton yesButton = new JButton("Yes");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				deleteDialog.dispose();
			}
		});
		JButton noButton = new JButton("No");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDialog.dispose();
			}
		});
		contentPanel.add(yesButton);
		contentPanel.add(noButton);
		deleteDialog.getContentPane().add(contentPanel);
		deleteDialog.setVisible(true);
	}

	private void delete() {
		warehouse = warehouseContainer.getWarehouses().get(list.getSelectedIndex());
		warehouseController.deleteWarehouse(warehouse.getName());
		list.clearSelection();
		refresh();
	}

	////////////////////////////////////// Create Warehouse
	////////////////////////////////////// Dialog///////////////////////////////////////////

	class CreateWarehouseDialogPanel extends JPanel {
		private JPanel contentPanel = new JPanel();
		private JPanel buttonPane = new JPanel();
		//private JLabel lblCreateWarehouse = new JLabel();
		private JLabel lblName = new JLabel();
		private JLabel lblAddress = new JLabel();
		private JLabel lblCapacity = new JLabel();
		private JLabel lblError = new JLabel();
		private JTextField nameTextField = new JTextField();
		private JTextField addressTextField = new JTextField();
		private JTextField capacityTextField = new JTextField();
		private JButton okButton = new JButton();
		private JButton cancelButton = new JButton();

		public CreateWarehouseDialogPanel() {
			// setBounds(100, 100, 450, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBackground(new Color(255, 255, 240));
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
			gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
			gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
			contentPanel.setLayout(gbl_contentPanel);
			/*
			{
				lblCreateWarehouse = new JLabel("Create Warehouse");
				lblCreateWarehouse.setFont(new Font("Viner Hand ITC", Font.BOLD, 15));
				GridBagConstraints gbc_lblCreateWarehouse = new GridBagConstraints();
				gbc_lblCreateWarehouse.gridwidth = 2;
				gbc_lblCreateWarehouse.insets = new Insets(0, 0, 5, 0);
				gbc_lblCreateWarehouse.gridx = 0;
				gbc_lblCreateWarehouse.gridy = 0;
				contentPanel.add(lblCreateWarehouse, gbc_lblCreateWarehouse);
			}
			*/
			{
				lblName = new JLabel("Name:");
				lblName.setFont(new Font("Monospaced", Font.PLAIN, 12));
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.anchor = GridBagConstraints.EAST;
				gbc_lblName.insets = new Insets(0, 0, 5, 5);
				gbc_lblName.gridx = 0;
				gbc_lblName.gridy = 1;
				contentPanel.add(lblName, gbc_lblName);
			}
			{
				nameTextField = new JTextField();
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
				gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameTextField.gridx = 1;
				gbc_nameTextField.gridy = 1;
				contentPanel.add(nameTextField, gbc_nameTextField);
				nameTextField.setColumns(10);
			}
			{
				lblAddress = new JLabel("Address:");
				lblAddress.setFont(new Font("Monospaced", Font.PLAIN, 12));
				GridBagConstraints gbc_lblAddress = new GridBagConstraints();
				gbc_lblAddress.anchor = GridBagConstraints.EAST;
				gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
				gbc_lblAddress.gridx = 0;
				gbc_lblAddress.gridy = 2;
				contentPanel.add(lblAddress, gbc_lblAddress);
			}
			{
				addressTextField = new JTextField();
				GridBagConstraints gbc_addressTextField = new GridBagConstraints();
				gbc_addressTextField.insets = new Insets(0, 0, 5, 0);
				gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_addressTextField.gridx = 1;
				gbc_addressTextField.gridy = 2;
				contentPanel.add(addressTextField, gbc_addressTextField);
				addressTextField.setColumns(10);
			}
			{
				lblCapacity = new JLabel("Capacity:");
				lblCapacity.setFont(new Font("Monospaced", Font.PLAIN, 12));
				GridBagConstraints gbc_lblCapacity = new GridBagConstraints();
				gbc_lblCapacity.anchor = GridBagConstraints.EAST;
				gbc_lblCapacity.insets = new Insets(0, 0, 5, 5);
				gbc_lblCapacity.gridx = 0;
				gbc_lblCapacity.gridy = 3;
				contentPanel.add(lblCapacity, gbc_lblCapacity);
			}
			{
				capacityTextField = new JTextField();
				GridBagConstraints gbc_capacityTextField = new GridBagConstraints();
				gbc_capacityTextField.insets = new Insets(0, 0, 5, 0);
				gbc_capacityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_capacityTextField.gridx = 1;
				gbc_capacityTextField.gridy = 3;
				contentPanel.add(capacityTextField, gbc_capacityTextField);
				capacityTextField.setColumns(10);
			}
			{
				lblError = new JLabel();
				lblError.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 13));
				lblError.setForeground(new Color(255, 0, 0));

				lblError.setHorizontalAlignment(SwingConstants.TRAILING);
				GridBagConstraints gbc_lblError = new GridBagConstraints();
				gbc_lblError.gridx = 1;
				gbc_lblError.gridy = 6;
				contentPanel.add(lblError, gbc_lblError);
			}
			{
				buttonPane = new JPanel();
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				GridBagLayout gbl_buttonPane = new GridBagLayout();
				gbl_buttonPane.columnWidths = new int[] { 316, 47, 63, 0 };
				gbl_buttonPane.rowHeights = new int[] { 21, 0 };
				gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
				gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
				buttonPane.setLayout(gbl_buttonPane);

				cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(173, 216, 230));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel1();
					}
				});
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
				gbc_cancelButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_cancelButton.gridx = 0;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
				{
					okButton = new JButton("OK");
					GridBagConstraints gbc_okButton = new GridBagConstraints();
					gbc_okButton.gridx = 2;
					gbc_okButton.gridy = 0;
					buttonPane.add(okButton, gbc_okButton);
					okButton.setBackground(new Color(173, 216, 230));
					okButton.setActionCommand("OK");
					// getRootPane().setDefaultButton(okButton);
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							createWarehouse();
						}
					});
				}
			}
			add(contentPanel);
			add(buttonPane);
			// add(lblCreateWarehouse);
			// add(lblName);
			// add(lblAddress);
			// add(lblCapacity);
			// add(lblError);
			// add(nameTextField);
			// add(addressTextField);
			// add(capacityTextField);
			// add(okButton);
			// add(cancelButton);
		}

		private void createWarehouse() {
			if (nameTextField.getText().equals("") || addressTextField.getText().equals("")
					|| capacityTextField.getText().equals("")) {
				lblError.setText("Please fill all the fields!");
			} else {
				if (warehouseController.fromWarehouse(nameTextField.getText())) {
					lblError.setText("Warehouse already exists!");
				} else {
					warehouseController.addWarehouse(nameTextField.getText(), addressTextField.getText(),
							capacityTextField.getText());
					cancel1();
					refresh();
				}
			}
		}

		private void cancel1() {
			createWarehouseDialog.dispose();
		}

		public void resetlbls1() {
			nameTextField.setText("");
			addressTextField.setText("");
			capacityTextField.setText("");
			// lbloldName.setText(warehouse.getName());
			// lbloldAddress.setText(warehouse.getAddress());
			// lbloldCapacity.setText(warehouse.getCapacity());

		}
	}

	///////////////////////////// Edit Warehouse
	///////////////////////////// Dialog//////////////////////////////

	class EditWarehouseDialogPanel extends JPanel {

		private JPanel contentPanel = new JPanel();
		private JPanel buttonPane = new JPanel();
		private JLabel lbloldName = new JLabel();
		private JLabel lblnewName = new JLabel();
		private JLabel lbloldAddress = new JLabel();
		private JLabel lblnewAddress = new JLabel();
		private JLabel lbloldCapacity = new JLabel();
		private JLabel lblnewCapacity = new JLabel();
		private JLabel lblError = new JLabel();
		private JTextField nameTextField = new JTextField();
		private JTextField addressTextField = new JTextField();
		private JTextField capacityTextField = new JTextField();
		private JButton okButton = new JButton();
		private JButton cancelButton = new JButton();
		private JButton deleteButton = new JButton();

		public EditWarehouseDialogPanel() {
			// setBounds(100, 100, 450, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
			gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
			gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
			gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			contentPanel.setLayout(gbl_contentPanel);
			{
				lbloldName = new JLabel();
				GridBagConstraints gbc_lbloldName = new GridBagConstraints();
				gbc_lbloldName.insets = new Insets(0, 0, 5, 5);
				gbc_lbloldName.gridx = 1;
				gbc_lbloldName.gridy = 1;
				contentPanel.add(lbloldName, gbc_lbloldName);
			}
			{
				lblnewName = new JLabel("New Name:");
				GridBagConstraints gbc_lblnewName = new GridBagConstraints();
				gbc_lblnewName.anchor = GridBagConstraints.EAST;
				gbc_lblnewName.insets = new Insets(0, 0, 5, 5);
				gbc_lblnewName.gridx = 2;
				gbc_lblnewName.gridy = 1;
				contentPanel.add(lblnewName, gbc_lblnewName);
			}
			{
				nameTextField = new JTextField();
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
				gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameTextField.gridx = 3;
				gbc_nameTextField.gridy = 1;
				contentPanel.add(nameTextField, gbc_nameTextField);
				nameTextField.setColumns(10);
			}
			{
				lbloldAddress = new JLabel();
				GridBagConstraints gbc_lbloldAddress = new GridBagConstraints();
				gbc_lbloldAddress.insets = new Insets(0, 0, 5, 5);
				gbc_lbloldAddress.gridx = 1;
				gbc_lbloldAddress.gridy = 2;
				contentPanel.add(lbloldAddress, gbc_lbloldAddress);
			}
			{
				lblnewAddress = new JLabel("New Address:");
				GridBagConstraints gbc_lblnewAddress = new GridBagConstraints();
				gbc_lblnewAddress.anchor = GridBagConstraints.EAST;
				gbc_lblnewAddress.insets = new Insets(0, 0, 5, 5);
				gbc_lblnewAddress.gridx = 2;
				gbc_lblnewAddress.gridy = 2;
				contentPanel.add(lblnewAddress, gbc_lblnewAddress);
			}
			{
				addressTextField = new JTextField();
				GridBagConstraints gbc_addressTextField = new GridBagConstraints();
				gbc_addressTextField.insets = new Insets(0, 0, 5, 0);
				gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_addressTextField.gridx = 3;
				gbc_addressTextField.gridy = 2;
				contentPanel.add(addressTextField, gbc_addressTextField);
				addressTextField.setColumns(10);
			}
			{
				lbloldCapacity = new JLabel();
				GridBagConstraints gbc_lbloldCapacity = new GridBagConstraints();
				gbc_lbloldCapacity.insets = new Insets(0, 0, 0, 5);
				gbc_lbloldCapacity.gridx = 1;
				gbc_lbloldCapacity.gridy = 3;
				contentPanel.add(lbloldCapacity, gbc_lbloldCapacity);
			}
			{
				lblnewCapacity = new JLabel("New Capacity:");
				GridBagConstraints gbc_lblnewCapacity = new GridBagConstraints();
				gbc_lblnewCapacity.anchor = GridBagConstraints.EAST;
				gbc_lblnewCapacity.insets = new Insets(0, 0, 0, 5);
				gbc_lblnewCapacity.gridx = 2;
				gbc_lblnewCapacity.gridy = 3;
				contentPanel.add(lblnewCapacity, gbc_lblnewCapacity);
			}
			{
				lblError = new JLabel("");
				GridBagConstraints gbc_lblError = new GridBagConstraints();
				gbc_lblError.anchor = GridBagConstraints.CENTER;
				gbc_lblError.insets = new Insets(0, 0, 6, 6);
				gbc_lblError.gridx = 2;
				gbc_lblError.gridy = 4;
				contentPanel.add(lblError, gbc_lblError);
			}
			{
				capacityTextField = new JTextField();
				GridBagConstraints gbc_capacityTextField = new GridBagConstraints();
				gbc_capacityTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_capacityTextField.gridx = 3;
				gbc_capacityTextField.gridy = 3;
				contentPanel.add(capacityTextField, gbc_capacityTextField);
				capacityTextField.setColumns(10);
			}
			{
				buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cancel2();
						}
					});
				}
//				deleteButton = new JButton("Delete Warehouse");
//				buttonPane.add(deleteButton);
//				deleteButton.addActionListener(new ActionListener() {
//
//					public void actionPerformed(ActionEvent e) {
//						deleteDialog();
//					}
//				});

				{
					okButton = new JButton("OK");
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					// getRootPane().setDefaultButton(okButton);
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							editWarehouse();
							// cancel2();

						}
					});
				}

			}
			add(contentPanel);
			add(buttonPane);
		}

		private void editWarehouse() {
			if (!warehouseController.fromWarehouse(nameTextField.getText())) {
				if (!nameTextField.getText().isEmpty()) {
					warehouse.setName(nameTextField.getText());
				}
				if (!addressTextField.getText().isEmpty()) {
					warehouse.setAddress(addressTextField.getText());
				}
				if (!capacityTextField.getText().isEmpty()) {
					warehouse.setCapacity(capacityTextField.getText());
				}
				cancel2();
				refresh();
			} else {
				lblError.setText("Warehouse already exists");
			}

		}

		private void cancel2() {
			editWarehouseDialog.dispose();
			lblError.setText("");
		}

//		private void deleteDialog() {
//			JDialog deleteDialog = new JDialog();
//			JPanel contentPanel = new JPanel();
//			deleteDialog.setTitle("Are you sure you want to delete this Warehouse?");
//			deleteDialog.setModalityType(ModalityType.APPLICATION_MODAL);
//			deleteDialog.setSize(400, 75);
//			// deleteDialog.pack();
//			deleteDialog.setLocationRelativeTo(null);
//			JButton yesButton = new JButton("Yes");
//			yesButton.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					delete();
//					deleteDialog.dispose();
//					editWarehouseDialog.dispose();
//				}
//			});
//			JButton noButton = new JButton("No");
//			noButton.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					deleteDialog.dispose();
//				}
//			});
//			contentPanel.add(yesButton);
//			contentPanel.add(noButton);
//			deleteDialog.getContentPane().add(contentPanel);
//			deleteDialog.setVisible(true);
//		}
//
//		private void delete() {
//			warehouseController.deleteWarehouse(warehouse.getName());
//			list.clearSelection();
//			refresh();
//		}

		public void resetlbls() {
			if (warehouse != null) {
				nameTextField.setText("");
				addressTextField.setText("");
				capacityTextField.setText("");
				// lbloldName.setText(warehouse.getName());
				// lbloldAddress.setText(warehouse.getAddress());
				// lbloldCapacity.setText(warehouse.getCapacity());
			}

		}
	}

}
