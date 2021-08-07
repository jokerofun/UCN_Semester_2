package GUI;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


import Controller.WarehouseController;

public class TransferList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Vector<String> columnNames = new Vector<String>();
	Vector<Vector> rowData = new Vector<Vector>();
	private JTable table_1;
	private MyDialogPanel dialogPanel = new MyDialogPanel();
	private JDialog dialog;
	
	private WarehouseController warehouseController;
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { TransferList dialog = new
	 * TransferList(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * /** Create the dialog.
	 */
	public TransferList(String warehouseFrom, String warehouseTo) {
		this.setTitle("Transfer List");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		warehouseController = new WarehouseController();
		warehouseController.fromWarehouse(warehouseFrom);
		warehouseController.toWarehouse(warehouseTo);
		warehouseController.newTransferList();
		columnNames.add("Barcode");
		columnNames.add("Description");
		columnNames.add("Amount");

		{
			JPanel northPanel = new JPanel();
			contentPanel.add(northPanel, BorderLayout.NORTH);
			GridBagLayout gbl_northPanel = new GridBagLayout();
			gbl_northPanel.columnWidths = new int[] { 35, 83, 174, 71, 0 };
			gbl_northPanel.rowHeights = new int[] { 13, 0 };
			gbl_northPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_northPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			northPanel.setLayout(gbl_northPanel);
			{
				JLabel lblFrom = new JLabel("From: " + warehouseFrom);
				GridBagConstraints gbc_lblFrom = new GridBagConstraints();
				gbc_lblFrom.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblFrom.insets = new Insets(0, 0, 0, 5);
				gbc_lblFrom.gridx = 1;
				gbc_lblFrom.gridy = 0;
				northPanel.add(lblFrom, gbc_lblFrom);
			}
			{
				JLabel lblTo = new JLabel("To: " + warehouseTo);
				GridBagConstraints gbc_lblTo = new GridBagConstraints();
				gbc_lblTo.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblTo.gridx = 3;
				gbc_lblTo.gridy = 0;
				northPanel.add(lblTo, gbc_lblTo);
			}
		}
		{
			JPanel southPanel = new JPanel();
			contentPanel.add(southPanel, BorderLayout.SOUTH);
			southPanel.setLayout(new MigLayout("", "[63px][87px][][47px][][][][][]", "[21px]"));
			southPanel.setLayout(new MigLayout("", "[1px][][][][][][][]", "[1px]"));

			{
				JButton cancelButton = new JButton("Cancel");
				southPanel.add(cancelButton, "cell 0 0");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}

			JButton btnAddProduct = new JButton("Add Product");
			btnAddProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addProduct();
				}
			});
			southPanel.add(btnAddProduct, "cell 2 0");
			{
				JButton okButton = new JButton("OK");
				southPanel.add(okButton, "cell 8 0");
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.WEST);
			{
				table_1 = new JTable(rowData, columnNames);
				scrollPane.setViewportView(table_1);
			}
		}
	}

	private void cancel() {
		System.out.println(warehouseController.deletTransferList(warehouseController.getIndexTransferList()));
		this.dispose();
	}

	private void addProduct() {
		if (dialog == null) {
			Window win = SwingUtilities.getWindowAncestor(this);
			if (win != null) {

				dialog = new JDialog(win, "Add Product", ModalityType.APPLICATION_MODAL);
				dialog.setTitle("Add Product");
				// dialogPanel.setLayout(null);
				// dialog.setBounds(100, 100, 450, 300);
				dialog.getContentPane().add(dialogPanel);
				dialog.pack();
				dialog.setLocationRelativeTo(null);
			}
		}
		dialog.setSize(500,300);
		dialog.setVisible(true);
	}

	////////////////////////////////////////// Dialog///////////////////////////////////////////

	class MyDialogPanel extends JPanel {
		private JPanel contentPanel = new JPanel();
		private JTextField barcodeTextField = new JTextField();
		private JTextField amountTextField = new JTextField();
		private JLabel lblAddProduct = new JLabel();
		private JLabel lblBarcode = new JLabel();
		private JLabel lblAmount = new JLabel();
		private JPanel buttonPane = new JPanel();
		private JButton okButton = new JButton();
		private JButton cancelButton = new JButton();

		public MyDialogPanel() {
			setBounds(100, 100, 450, 300);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setLayout(null);
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			getContentPane().setLayout(new BorderLayout());

			{
				lblAddProduct = new JLabel("Add product");
				lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
				lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblAddProduct.setBounds(10, 10, 416, 13);
				contentPanel.add(lblAddProduct);
			}
			{
				lblBarcode = new JLabel("Barcode:");
				lblBarcode.setBounds(46, 86, 61, 13);
				contentPanel.add(lblBarcode);
			}
			{
				barcodeTextField = new JTextField();
				barcodeTextField.setBounds(142, 83, 224, 19);
				contentPanel.add(barcodeTextField);
				barcodeTextField.setColumns(10);
			}
			{
				lblAmount = new JLabel("Amount");
				lblAmount.setBounds(46, 140, 57, 13);
				contentPanel.add(lblAmount);
			}
			{
				amountTextField = new JTextField();
				amountTextField.setBounds(142, 137, 224, 19);
				contentPanel.add(amountTextField);
				amountTextField.setColumns(10);
			}
			{
				buttonPane = new JPanel();
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setLayout(new BorderLayout(0, 0));
				{
					okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							OkAddProduct();
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton, BorderLayout.EAST);
					// getRootPane().setDefaultButton(okButton);
				}
				{
					cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cancel1();
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton, BorderLayout.WEST);
				}
			}
			add(contentPanel);
			add(lblAddProduct);
			add(lblBarcode);
			add(barcodeTextField);
			add(lblAmount);
			add(amountTextField);
			add(buttonPane);
			add(okButton);
			add(cancelButton);
		}
		private void cancel1() {
			dialog.dispose();
		}
		
		private void OkAddProduct() {
			Vector <String> row = new Vector<String>();
			ArrayList<String> data = new ArrayList<>();
			if((!barcodeTextField.getText().equals(""))&&(!amountTextField.getText().equals("")))
			try{
			if(warehouseController.checkProduct(barcodeTextField.getText())) {
			int i =  Integer.parseInt(amountTextField.getText());
			data = warehouseController.findSellableProduct(barcodeTextField.getText());
			int amountCheck = warehouseController.getAmount();
			if(i < amountCheck && i > 0)
			{
				warehouseController.addProductToTransfer(i);
				row.add(data.get(0));
				row.add(data.get(1));
				row.add(amountTextField.getText());
				rowData.addElement(row);
				table_1.updateUI();
		     }else {
				//TODO Error message wrong amount
			}
			}else {
				//TODO Error message product doesn't exist
			}
			}
			catch(NumberFormatException e) {
			// TODO Error Integer
			}
		}
		
		
	}

}
