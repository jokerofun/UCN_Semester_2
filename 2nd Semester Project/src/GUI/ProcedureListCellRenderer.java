package GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Procedure;

@SuppressWarnings("serial")
public class ProcedureListCellRenderer extends JLabel implements ListCellRenderer<Procedure> {

	public ProcedureListCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Procedure> list, Procedure procedure, int index,
			boolean isSelected, boolean cellHasFocus) {
		String productInfo = procedure.getCode() + " - " + procedure.getProcedureName() + " - "
				+ procedure.getPricePerHour() + " - " + procedure.getEmployeeTypeRequired();

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
