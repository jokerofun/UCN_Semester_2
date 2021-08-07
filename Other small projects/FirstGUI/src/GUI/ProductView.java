package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.ProductController;
import Model.Product;

public class ProductView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtPrice;
	private ProductController productController = new ProductController();
	private Product product;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProductView dialog = new ProductView(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProductView(Product p) {
		setModal(true);
		setTitle("Product View:");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			txtName = new JTextField();
			txtName.setBounds(50, 30, 147, 19);
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}

		txtPrice = new JTextField();
		txtPrice.setBounds(50, 73, 147, 19);
		contentPanel.add(txtPrice);
		txtPrice.setColumns(10);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(13, 76, 46, 13);
		contentPanel.add(lblPrice);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 33, 46, 13);
		contentPanel.add(lblName);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonClicked();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CancelClicked();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		init(p);
	}

	private void init(Product p) {
		this.product = p;

		if (p != null) {
			fillFields();
		}
	}

	private void fillFields() {
		this.txtName.setText("" + product.getName());
		this.txtPrice.setText("" + product.getPrice());
	}

	private void okButtonClicked() {
		try {
			String name = txtName.getText();
			double price = Double.parseDouble(txtPrice.getText());
			if (product != null) {
				productController.updateProduct(product, name, price);
			} else {
				productController.createProduct(name, price);
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Numbers for prices, please ");
		}

	}

	private void CancelClicked() {
		this.dispose();
	}
}
