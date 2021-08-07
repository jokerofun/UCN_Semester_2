package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import model.Employee;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class EmployeeListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
//		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Employee currEmployee = (Employee) value;
		String representation = "";
		if (currEmployee != null) {
			representation = String.format("%s %s %s", currEmployee.getFname(), currEmployee.getMinit(),
					currEmployee.getLname());
		}
		return new JLabel(representation);
	}

}
