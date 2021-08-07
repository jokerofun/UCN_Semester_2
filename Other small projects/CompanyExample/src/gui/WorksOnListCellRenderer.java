package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import model.WorksOn;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public class WorksOnListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;
	private DefaultListCellRenderer dlcr = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// return super.getListCellRendererComponent(arg0, arg1, arg2, arg3, arg4);
		WorksOn currWorksOn = (WorksOn) value;
		String representation = "";
		if (currWorksOn != null) {
			representation = String.format("%s (%.2f hours)", currWorksOn.getProject().getName(),
					currWorksOn.getHours());
		}
		JLabel renderer = (JLabel) dlcr.getListCellRendererComponent(list, representation, index, isSelected,
				cellHasFocus);
		return renderer;
		// return super.getListCellRendererComponent(list, representation, index,
		// isSelected, cellHasFocus); // alternative w/o dlcr
	}

}
