package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.WarehouseController;

public class SearchProduct extends JDialog {

	WarehouseController controller = new WarehouseController();
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JComboBox<String> comboBox = new JComboBox<String>(controller.getWarehousesNames());
	private JList<String> list;
	private DefaultListModel<String> listRepresentation;
	private JLabel errorLabel = new JLabel("Hello!");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SearchProduct dialog = new SearchProduct();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SearchProduct() {
		this.setTitle("Search Product");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(29, 28, 167, 157);

		JLabel lblNewLabel = new JLabel("Barcode:");
		lblNewLabel.setBounds(257, 113, 46, 14);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(257, 127, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(358, 126, 66, 23);
		contentPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});

		list = new JList();
		list.setBounds(35, 11, 167, 184);
		contentPanel.add(list);
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setHorizontalAlignment(SwingConstants.LEFT);

		errorLabel.setBounds(212, 180, 212, 37);
		contentPanel.add(errorLabel);

		comboBox.setBounds(257, 82, 86, 22);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
			}
		}
	}

	private void cancel() {
		this.dispose();
	}

	private void search() {
		if (comboBox.getSelectedItem() == null) {
			errorLabel.setText("Please choose a warehouse before searching!");
		} else {
			controller.fromWarehouse((String) comboBox.getSelectedItem());
			listRepresentation = new DefaultListModel<String>();
			if (textField.getText().equals("")) {
				errorLabel.setText("Please fill the box before searching!");
			} else {
				if (controller.findSellableProduct(textField.getText()).size() == 1) {
					errorLabel.setText("No products found!");
				} else {
					ArrayList<String> modelList = controller.findSellableProduct(textField.getText());
					String res = "";
					for (String s : modelList) {
						res += (s + " - ");
					}
					listRepresentation.addElement(res);
					list.setModel(listRepresentation);
					errorLabel.setText("Product found successfully!");
				}
			}

		}

	}
}
