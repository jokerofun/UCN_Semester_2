package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.EmployeeProcedure;

@SuppressWarnings("serial")
public class EmployeeProcedureListCellRenderer extends JLabel implements ListCellRenderer<EmployeeProcedure> {

	public EmployeeProcedureListCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends EmployeeProcedure> list, EmployeeProcedure ep,
			int index, boolean isSelected, boolean cellHasFocus) {

		String productInfo;
		if(ep.getProcedure() == null) {
			productInfo = ep.getEmployee().getFirstName() + " - " + "Nothing";
		}else {
			productInfo = ep.getEmployee().getFirstName() + " - " + ep.getProcedure().getProcedureName();
		}
		

		setText(productInfo);

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		System.out.println(this);
		return this;
	}
}
