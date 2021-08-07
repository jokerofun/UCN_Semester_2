package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import model.Department;
import model.Employee;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */

public class DepartmentListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
//		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Department currDepartment = (Department) value;
		String representation = "";
		if (currDepartment != null) {
			representation = String.format("[%s] %s (%s)", currDepartment.getDnumber(), currDepartment.getDname(),
					currDepartment.getManager().getLname());
		}
		return new JLabel(representation);
	}
}
