package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Customer;

@SuppressWarnings("serial")
public class CustomerListCellRenderer extends JLabel implements ListCellRenderer<Customer> {

	public CustomerListCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Customer> list, Customer customer, int index,
			boolean isSelected, boolean cellHasFocus) {
		String productInfo = customer.getId() + " - " + customer.getCompanyName() + " - " + customer.getCUI() + " - "
				+ customer.getEmail() + " - " + customer.getPhoneNumber() + " - " + customer.getZipcode() + " - "
				+ customer.getStreet() + " - " + customer.getCity();

		setText(productInfo);

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		return this;
	}
}
