package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Employee;

@SuppressWarnings("serial")
public class EmployeeListCellRenderer extends JLabel implements ListCellRenderer<Employee> {

	public EmployeeListCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Employee> list, Employee employee, int index,
			boolean isSelected, boolean cellHasFocus) {
		String productInfo = employee.getId() + " - " + employee.getFirstName() + " - " + employee.getMiddleName()
				+ " - " + employee.getLastName() + " - " + employee.getEmail() + " - " + employee.getPhoneNumber()
				+ " - " + employee.getZipcode() + " - " + employee.getStreet() + " - " + employee.getCity();

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
