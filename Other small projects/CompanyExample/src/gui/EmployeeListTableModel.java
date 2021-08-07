package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Employee;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class EmployeeListTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<Employee> data;
	private static final String[] COL_NAMES = { "First name", "Mid. init", "Last name" };

	public EmployeeListTableModel() {
		setData(null);
	}

	public EmployeeListTableModel(List<Employee> data) {
		setData(data);
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COL_NAMES[column];
	}

	public void setData(List<Employee> data) {
		if (data != null) {
			this.data = data;
		} else {
			this.data = new ArrayList<>(0);
		}
		super.fireTableDataChanged();
	}

	public Employee getEmployeeOfRow(int index) {
		if (index < 0 || index >= data.size()) {
			return null;
		}
		return this.data.get(index);
	}

	@Override
	public Object getValueAt(int row, int column) {
		Employee e = data.get(row);
		switch (column) {
		case 0:
			return e.getFname();
		case 1:
			return e.getMinit();
		case 2:
			return e.getLname();
		default:
			return "UNKNOLWN COL NAME";
		}
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

}
