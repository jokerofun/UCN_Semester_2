package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.ProductController;
import Model.Product;

public class UpdateProducts extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<Product> list;
	private DefaultListModel<Product> listRepresentation;
	private ProductController productController = new ProductController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateProducts dialog = new UpdateProducts();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateProducts() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 436, 232);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 10, 416, 195);
			contentPanel.add(scrollPane);

			JList list = new JList();
			listRepresentation = new DefaultListModel<Product>();
			ArrayList<Product> modelList = productController.getAll();
			for (Product element : modelList) {
				listRepresentation.addElement(element);
			}
			list.setModel(listRepresentation);
			scrollPane.setRowHeaderView(list);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 232, 436, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);

			JButton btnUpdatelist = new JButton("UpdateList");
			btnUpdatelist.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateList();
					// showUpdateDetails();
				}
			});

			JButton btnRemove = new JButton("Remove");
			buttonPane.add(btnRemove);
			buttonPane.add(btnUpdatelist);
//			btnRemove.addActionListener(new ActionListener() {
//				removeProduct();
//			});
			{
				JButton okButton = new JButton("Show & Edit");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelClicked();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void updateList() {
		listRepresentation = new DefaultListModel<Product>();
		ArrayList<Product> modelList = productController.getAll();
		for (Product element : modelList) {
			listRepresentation.addElement(element);
		}
		list.setModel(listRepresentation);
	}

	private void showUpdateDetails() {
		Product pp = list.getSelectedValue();
		int index = list.getSelectedIndex();

		ProductView dialog = new ProductView(pp);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);

		listRepresentation.set(index, pp);
	}

	private void removeProduct() {
		int index = list.getSelectedIndex();
		if (index >= 0 && index < listRepresentation.getSize()) {
			listRepresentation.remove(index);
			// TODO remove from the container
		}
	}

	private void cancelClicked() {
		this.dispose();
	}
}
