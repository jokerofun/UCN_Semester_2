package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddProduct extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField barcodeTextField;
	private JTextField amountTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddProduct dialog = new AddProduct();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddProduct() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAddProduct = new JLabel("Add product");
			lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblAddProduct.setBounds(10, 10, 416, 13);
			contentPanel.add(lblAddProduct);
		}
		{
			JLabel lblBarcode = new JLabel("Barcode:");
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
			JLabel lblAmount = new JLabel("Amount");
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
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton, BorderLayout.EAST);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, BorderLayout.WEST);
			}
		}
	}

	private void cancel() {
		this.dispose();
	}

}
